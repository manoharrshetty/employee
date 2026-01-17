 package com.emp.mapper;

import com.emp.dto.DeptDTO;
import com.emp.model.Dept;
import org.springframework.stereotype.Component;

@Component
public class DeptMapper {

    public DeptDTO toDTO(Dept dept) {
        if (dept == null) return null;
        DeptDTO dto = new DeptDTO();
        dto.setId(dept.getDeptId());
        dto.setLastModifiedDate(dept.getLastModifiedDate());
        dto.setName(dept.getName());
        dto.setVersion(dept.getVersion());
        return dto;
    }

    public Dept toEntity(DeptDTO dto) {
        if (dto == null) return null;
        Dept dept = new Dept();
        dept.setDeptId(dto.getId());
        dept.setName(dto.getName());
        dept.setLastModifiedDate(dto.getLastModifiedDate());
        dept.setVersion(dto.getVersion());
        return dept;
    }
}
