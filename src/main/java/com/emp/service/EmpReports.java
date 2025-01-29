package com.emp.service;

import com.emp.model.Emp;

import java.io.File;
import java.util.List;

public interface EmpReports {

    File generateReport(List<Emp> empList,String fileName) ;

}
