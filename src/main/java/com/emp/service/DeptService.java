package com.emp.service;

import com.emp.dto.DeptDTO;
import com.emp.dto.DeptSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeptService {
    DeptDTO getById(Long id);
    List<DeptDTO> getAllDept();
    DeptDTO updateDept(DeptDTO dto);

   void deleteDept(Long id);

    Page<DeptDTO> search(DeptSearchCriteria criteria, Pageable pageable);

    DeptDTO createDept(DeptDTO dto);
}
