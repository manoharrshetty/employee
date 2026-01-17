package com.emp.repository;

import com.emp.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Long>, JpaSpecificationExecutor<Emp> {

    List<Emp> findByFirstName(String firstName);

    boolean existsByFirstNameAndLastNameAndGenderAndBirthDateAndDeptDeptId(
            String firstName,
            String lastName,
            String gender,
            Date birthDate,
            Long deptId
    );
}

