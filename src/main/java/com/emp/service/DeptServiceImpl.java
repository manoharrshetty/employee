package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.mapper.DeptMapper;
import com.emp.model.Dept;
import com.emp.model.DeptQuery;

@Service

public class DeptServiceImpl  implements DeptService {

	private final AppDomainService<Dept,DeptQuery,Integer> deptService;
	
	/**The below constructor is same as doing what we are doing below.
	 * 
	 * The constructor autowire becomes usefull if we have to initialize something that needs the mapper object on construction of this object.
	 * @Autowired
	 * DeptMapper deptMapper
	 */
	
	
	@Autowired
	public DeptServiceImpl(DeptMapper deptMapper) {
		deptService =  new AppDomainService<>(deptMapper);
	}

	
    
	
	@Override
	public List<Dept> findAll() {
		return deptService.findAll();
	}
	@Transactional
	@Override
	public Dept save(Dept dept) {
		deptService.save(dept);
		return deptService.findById(dept.getId());

	}
	
	@Transactional
	@Override
	public Dept update(Dept dept) {
		return deptService.update(dept,dept.getId());
			
	}
	@Transactional
	@Override
	public void delete(Integer id) {
		deptService.delete(id);
	}
	
	@Override
	public List<Dept> findByQuery(DeptQuery deptQuery) {
		return deptService.findByQuery(deptQuery);
	}
}
