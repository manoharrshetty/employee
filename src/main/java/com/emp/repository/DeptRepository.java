package com.emp.repository;

import com.emp.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface DeptRepository extends JpaRepository<Dept, Long>, JpaSpecificationExecutor<Dept> {
    List<Dept> findByName(String name);
}
