package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.mapper.UsersMapper;
import com.emp.model.Users;
import com.emp.model.UsersQuery;


@Service
public class UsersServiceImpl  implements UsersService {
	
	private final AppDomainService<Users,UsersQuery,Integer> employeeService;
	
	/**The below constructor is same as doing what we are doing below.
	 * 
	 * The constructor autowire becomes usefull if we have to initialize something that needs the mapper object on construction of this object.
	 * @Autowired
	 * UsersMapper usersMapper
	 */
	
	
	@Autowired
	public UsersServiceImpl(UsersMapper usersMapper) {
		employeeService =  new AppDomainService<>(usersMapper);
	}

	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<Users> findAll() {
		return employeeService.findAll();
		
	}
	@Transactional
	@Override
	public Users save(Users users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return employeeService.save(users);
		
	}
	
	@Transactional
	@Override
	public Users update(Users users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return employeeService.update(users,users.getId());
		
	}
	
	@Transactional
	@Override
	public void delete(Integer id) {
		employeeService.delete(id);
	}
	
	@Override
	public List<Users> findByQuery(UsersQuery usersQuery) {
		return employeeService.findByQuery(usersQuery);
		
	}
	
	
	
	
}
