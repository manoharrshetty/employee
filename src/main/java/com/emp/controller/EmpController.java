package com.emp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Dept;
import com.emp.model.Emp;
import com.emp.model.EmpDml;
import com.emp.model.EmpQuery;
import com.emp.service.EmpService;


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
     * produces JSON response with the following request parameters.
     * Alternatively you can also use  @GetMapping because it is a composed annotation that acts as a shortcut
	 * for @RequestMapping(method = RequestMethod.GET).
     * @param empId
     * @param firstName
     * @param lastName
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


