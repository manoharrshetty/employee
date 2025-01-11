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
import com.emp.model.DeptQuery;
import com.emp.model.Emp;
import com.emp.model.EmpQuery;
import com.emp.service.DeptService;


@RestController
public class DeptController {
	
    @Autowired
    private DeptService deptService;
    
    
    
    
    
    
    @RequestMapping(value = "/employee/dept",   params = { "deptId"},produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Dept> findByDeptId(@RequestParam(value="deptId")  Integer deptId) {
    	DeptQuery query = new DeptQuery();
    	query.setId(deptId);
    	return deptService.findByQuery(query);
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
    @RequestMapping(value = "/employee/dept",  produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Dept> findByQuery(@ModelAttribute("deptQuery") Optional<DeptQuery> 	deptQuery) {
    	return deptService.findByQuery(deptQuery.orElse(new DeptQuery()));
	}
    
	
    
    
    

    
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employee/dept")
    public Dept save(@RequestBody Dept newDept) {
            return deptService.save(newDept);

    }
    
    /*
     * Remember for put and delete body @RequestBody can exist too !!
     */
  
    @PutMapping("/employee/dept")
    public Dept put(@RequestBody Dept dept) {
    	return deptService.update(dept);
    }
    
    
    @DeleteMapping(value = "/employee/dept",   params = { "deptId"})
    public void delete(@RequestParam(value="empId")  Integer deptId) {
    	 deptService.delete(deptId);
    	 
    }
    
    
}


