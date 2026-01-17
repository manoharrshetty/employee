package com.emp.service;

import com.emp.dto.DeptDTO;
import com.emp.dto.DeptSearchCriteria;
import com.emp.exception.ResourceNotFoundException;
import com.emp.mapper.DeptMapper;
import com.emp.model.Dept;
import com.emp.repository.DeptRepository;
import com.emp.spec.DeptSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

	private final DeptRepository deptRepository;
	private final DeptMapper deptMapper;

	@Transactional
	@Override
	public DeptDTO createDept(DeptDTO deptDTO) {

		Dept dept = new Dept();
		dept.setName(deptDTO.getName());
		Dept saved = deptRepository.save(dept);
		return toDTO(saved);
	}



	@Override
	@Transactional
	public DeptDTO updateDept(DeptDTO deptDTO) {
		Dept dept = deptRepository.findById(deptDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Dept not found"));
	    dept.setName(deptDTO.getName());

		Dept updated = deptRepository.save(dept);
		return toDTO(updated);
	}

	@Transactional
	@Override
	public void deleteDept(Long id) {
		Dept dept = deptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		deptRepository.delete(dept);
	}
	@Override
	public List<DeptDTO> getAllDept() {
		return deptRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}


	@Override
	public DeptDTO getById(Long id) {
		Dept dept = deptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return toDTO(dept);
	}


	@Override
	public Page<DeptDTO> search(DeptSearchCriteria criteria, Pageable pageable) {

		return deptRepository.findAll(DeptSpecification.byCriteria(criteria), pageable).map(this::toDTO);
	}



	private DeptDTO toDTO(Dept dept) {
		return deptMapper.toDTO(dept);
	}
}
