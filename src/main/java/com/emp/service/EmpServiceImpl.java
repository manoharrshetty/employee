package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.mapper.EmpMapper;
import com.emp.model.Emp;
import com.emp.model.EmpQuery;

@Service

public class EmpServiceImpl  implements EmpService {

	private final AppDomainService<Emp,EmpQuery,Integer> employeeService;
	
	/**The below constructor is same as doing what we are doing below.
	 * 
	 * The constructor autowire becomes usefull if we have to initialize something that needs the mapper object on construction of this object.
	 * @Autowired
	 * EmpMapper employeeMapper
	 */
	
	
	@Autowired
	public EmpServiceImpl(EmpMapper empMapper) {
		employeeService =  new AppDomainService<>(empMapper);
		
	}

	
    
	
	@Override
	public List<Emp> findAll() {
		return employeeService.findAll();
	}
	@Transactional
	@Override
	public Emp save(Emp employee) {
		employeeService.save(employee);
		return employeeService.findById(employee.getId());
		
		
	}
	
	@Transactional
	@Override
	public Emp update(Emp employee) {
		employeeService.update(employee,employee.getId());
		return employeeService.findById(employee.getId());
					
	}
	@Transactional
	@Override
	public void delete(Integer id) {
		employeeService.delete(id);
	}
	
	@Override
	public List<Emp> findByQuery(EmpQuery employeeQuery) {
		return employeeService.findByQuery(employeeQuery);
	}
}
