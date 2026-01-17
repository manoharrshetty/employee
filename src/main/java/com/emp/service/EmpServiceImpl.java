package com.emp.service;

import com.emp.ai.AiService;
import com.emp.ai.TrainingRecommendation;
import com.emp.dto.EmpDTO;
import com.emp.dto.EmpSearchCriteria;
import com.emp.exception.EmpRelatedException;
import com.emp.exception.ResourceNotFoundException;
import com.emp.mapper.EmpMapper;
import com.emp.model.Dept;
import com.emp.model.Emp;
import com.emp.repository.DeptRepository;
import com.emp.repository.EmpRepository;
import com.emp.spec.EmpSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl  implements EmpService {

	private final EmpRepository empRepository;
	private final DeptRepository deptRepository;
	private final EmpMapper empMapper;
	private final AiService<TrainingRecommendation> aiService;






	@Autowired
	public EmpServiceImpl(AiService<TrainingRecommendation> aiService, EmpRepository empRepository, DeptRepository deptRepository, EmpMapper empMapper) {
		this.aiService = aiService;
		this.empRepository = empRepository;
		this.deptRepository = deptRepository;
		this.empMapper = empMapper;
	}




	@Transactional
	@CachePut(cacheNames = "empById", key = "#result.id")
	@Override
	public EmpDTO createEmployee(EmpDTO empDTO) {


		Dept dept = deptRepository.findById(empDTO.getDeptDTO().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));

		//make sure employee in same department dont have same first name,last name,birthday and gender
		if (empRepository.existsByFirstNameAndLastNameAndGenderAndBirthDateAndDeptDeptId(
				empDTO.getFirstName(), empDTO.getLastName(), empDTO.getGender(),empDTO.getBirthDate(), empDTO.getDeptDTO().getId())) {
			throw new EmpRelatedException("Employee with same name, birth,gender already exists in the department");
		}

		Emp emp = new Emp();
		emp.setDept(dept);
		emp.setSkills(empDTO.getSkills());
		applyTrainingRecommendation(emp,empDTO);
		emp.setCareerGoal(empDTO.getCareerGoal());
		emp.setBirthDate(empDTO.getBirthDate());
		emp.setFirstName(empDTO.getFirstName());
		emp.setLastName(empDTO.getLastName());
		emp.setGender(empDTO.getGender());
		emp.setHireDate(empDTO.getHireDate());
		emp.setSkills(empDTO.getSkills());
		Emp saved = empRepository.save(emp);
		return toDTO(saved);
	}



	@Override
	@Transactional
	@CachePut(cacheNames = "empById", key = "#result.id")
	public EmpDTO updateEmployee(EmpDTO empDTO) {
		Emp emp = empRepository.findById(empDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

		if (empDTO.getDeptDTO() != null && empDTO.getDeptDTO().getId() != null && !empDTO.getDeptDTO().getId().equals(emp.getDept().getDeptId())) {
			//make sure employee in same department dont have same first name,last name,birthday and gender
			if (empRepository.existsByFirstNameAndLastNameAndGenderAndBirthDateAndDeptDeptId(
					empDTO.getFirstName(), empDTO.getLastName(), empDTO.getGender(), empDTO.getBirthDate(), empDTO.getDeptDTO().getId())) {
				throw new EmpRelatedException("Employee with same name, birth,gender already exists in the department");
			}
			Dept dept = deptRepository.findById(empDTO.getDeptDTO().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Department not found"));
			emp.setDept(dept);
		}
		emp.setFirstName(empDTO.getFirstName() != null ? empDTO.getFirstName() : emp.getFirstName());
		emp.setLastName(empDTO.getLastName() != null ? empDTO.getLastName() : emp.getLastName());
		emp.setGender(empDTO.getGender() != null ? empDTO.getGender() : emp.getGender());
		emp.setBirthDate(empDTO.getBirthDate() != null ? empDTO.getBirthDate() : emp.getBirthDate());
		emp.setHireDate(empDTO.getHireDate() != null ? empDTO.getHireDate() : emp.getHireDate());
		emp.setSkills(empDTO.getSkills() != null ? empDTO.getSkills() : emp.getSkills());
		emp.setCareerGoal(empDTO.getCareerGoal() != null ? empDTO.getCareerGoal() : emp.getCareerGoal());
		if (empDTO.getSkills() != null || empDTO.getCareerGoal() != null){
			applyTrainingRecommendation(emp,empDTO);
		}
		Emp updated = empRepository.save(emp);
		return toDTO(updated);
	}

	@Transactional
	@Override
	@CacheEvict(cacheNames = "empById", key = "#id")
	public void deleteEmployee(Long id) {
		Emp emp = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		empRepository.delete(emp);
	}
	@Override
	public List<EmpDTO> getAllEmployees() {
		return empRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Cacheable(cacheNames = "empById", key = "#id")
	@Override
	public EmpDTO getById(Long id) {
		Emp emp = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return toDTO(emp);
	}


	@Override
	public Page<EmpDTO> search(EmpSearchCriteria criteria, Pageable pageable) {

		return empRepository.findAll(EmpSpecification.byCriteria(criteria), pageable).map(this::toDTO);
	}



	private EmpDTO toDTO(Emp emp) {
		return empMapper.toDTO(emp);
	}

	private void applyTrainingRecommendation(Emp emp, EmpDTO empDTO) {
		//if training recommendation is already provided, skip AI call
		if (empDTO.getTrainingRecommendation() == null || empDTO.getTrainingRecommendation().isEmpty()) {
			String prompt = String.format(
					"You are an experienced L&D advisor. Provide a very short bullet summary of training required. " +
							"Return plain text only, up to 3 bullets, each a single short phrase/sentence. Entire output must be <= 300 characters. " +
							"Do not add explanations or headings. Skills: %s. Career goal: %s.",
					empDTO.getSkills() == null ? "" : empDTO.getSkills(),
					empDTO.getCareerGoal() == null ? "" : empDTO.getCareerGoal()
			);
			TrainingRecommendation  trainingRecommendation =
					aiService.getResponse(prompt, TrainingRecommendation.class);
			emp.setTrainingRecommendation(trainingRecommendation.getShortSummary());
		} else {
			emp.setTrainingRecommendation(empDTO.getTrainingRecommendation());
		}
	}
}
