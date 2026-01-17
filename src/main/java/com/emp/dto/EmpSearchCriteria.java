package com.emp.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpSearchCriteria {
    private String firstName;
    private String lastName;
    private String gender;
    private String skills;
    private Long deptId;
    private LocalDate hireDateFrom;
    private LocalDate hireDateTo;
}
