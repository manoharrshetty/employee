package com.emp.controller;

import com.emp.model.Dept;
import com.emp.model.Emp;
import com.emp.model.EmpDml;
import com.emp.model.EmpQuery;
import com.emp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmpController {
	
    @Autowired
    private EmpService empService;


    @RequestMapping(value = "/employee/emp",   params = { "empId"},produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Emp> findByEmpId(@RequestParam(value="empId")  Integer empId) {
    	EmpQuery query = new EmpQuery();
    	query.setId(empId);
    	return empService.findByQuery(query);
	}

    /**
     *
     * @param employeeQuery
     * @return
     */
    @RequestMapping(value = "/employee/emp",  produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Emp> findByQuery(@ModelAttribute("employeeQuery") Optional<EmpQuery> 	employeeQuery) {
    	return empService.findByQuery(employeeQuery.orElse(new EmpQuery()));
	}
    
	
    
    
    

    
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employee/emp")
    public Emp save(@RequestBody Emp newEmp) {

   	   	return empService.save(newEmp);
    }
    
    /*
     * Remember for put and delete body @RequestBody can exist too !!
     */
  
    @PutMapping("/employee/emp")
    public Emp put(@RequestBody EmpDml updatedEmp) {
    	Emp emp = new Emp();
    	emp.setEmpBasic(updatedEmp.getEmpBasic());
    	Dept dept = new Dept();
    	dept.setId(updatedEmp.getDeptId());
    	emp.setDept(dept);
    	return empService.update(emp);
    }
    
    
    @DeleteMapping(value = "/employee/emp",   params = { "empId"})
    public void delete(@RequestParam(value="empId")  Integer empId) {
    	 empService.delete(empId);
    	 
    }
    
    
}


