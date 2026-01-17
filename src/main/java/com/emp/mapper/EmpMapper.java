
package com.emp.mapper;

import com.emp.dto.EmpDTO;
import com.emp.model.Emp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmpMapper {

    private final DeptMapper deptMapper;

    public EmpDTO toDTO(Emp emp) {
        if (emp == null) return null;
        EmpDTO dto = new EmpDTO();
        dto.setId(emp.getEmpId());
        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setDeptDTO(deptMapper.toDTO(emp.getDept()));
        dto.setSkills(emp.getSkills());
        dto.setCareerGoal(emp.getCareerGoal());
        dto.setTrainingRecommendation(emp.getTrainingRecommendation());
        dto.setLastModifiedDate(emp.getLastModifiedDate());
        dto.setVersion(emp.getVersion());
        dto.setGender(emp.getGender());
        dto.setBirthDate(emp.getBirthDate());
        dto.setHireDate(emp.getHireDate());
        return dto;
    }

    public Emp toEntity(EmpDTO dto) {
        if (dto == null) return null;
        Emp emp = new Emp();
        emp.setEmpId(dto.getId());
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setDept(deptMapper.toEntity(dto.getDeptDTO()));
        emp.setSkills(dto.getSkills());
        emp.setCareerGoal(dto.getCareerGoal());
        emp.setTrainingRecommendation(dto.getTrainingRecommendation());
        emp.setLastModifiedDate(dto.getLastModifiedDate());
        emp.setVersion(dto.getVersion());
        return emp;
    }
}
