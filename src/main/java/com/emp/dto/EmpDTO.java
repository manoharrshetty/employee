package com.emp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmpDTO extends BasicDTO {

    private String firstName;
    private String lastName;


    private String gender;


    private Date birthDate;


    private Date hireDate;


    private String skills;


    private String careerGoal;


    private String trainingRecommendation;


    private DeptDTO deptDTO;

}
