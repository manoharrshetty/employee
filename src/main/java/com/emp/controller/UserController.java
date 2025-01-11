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

import com.emp.model.Users;
import com.emp.model.UsersQuery;
import com.emp.service.UsersService;


@RestController
public class UserController {
	
    @Autowired
    private UsersService usersService;
    
   
    @RequestMapping(value = "/employee/users",   params = { "usersId"},produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Users> findByUsersId(@RequestParam(value="usersId")  Integer usersId) {
    	UsersQuery query = new UsersQuery();
    	query.setId(usersId);
    	return usersService.findByQuery(query);
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
    @RequestMapping(value = "/employee/users",  produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Users> findByQuery(@ModelAttribute("usersQuery") Optional<UsersQuery> 	usersQuery) {
    	return usersService.findByQuery(usersQuery.orElse(new UsersQuery()));
	}
    
	
    
    
    

    
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employee/users")
    public Users save(@RequestBody Users newUsers) {
    	return usersService.save(newUsers);
    }
    
    
  
    @PutMapping("/employee/users")
    public Users put(@RequestBody Users users) {
    	return usersService.update(users);
    }
    
    @DeleteMapping(value = "/employee/users",   params = { "usersId"})
    public void delete(@RequestParam(value="usersId")  Integer userId) {
    	 usersService.delete(userId);
    	 
    }
}


