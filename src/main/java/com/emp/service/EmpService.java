package com.emp.service;

import com.emp.dto.EmpDTO;
import com.emp.dto.EmpSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpService {
    EmpDTO getById(Long id);
    List<EmpDTO> getAllEmployees();
    EmpDTO updateEmployee(EmpDTO dto);

   void deleteEmployee(Long id);

    Page<EmpDTO> search(EmpSearchCriteria criteria, Pageable pageable);

    EmpDTO createEmployee(EmpDTO dto);
}
