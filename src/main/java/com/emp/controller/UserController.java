package com.emp.controller;

import com.emp.dto.UserDTO;
import com.emp.dto.UserSearchCriteria;
import com.emp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee/user")
public class UserController {
	
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        UserDTO dto = userService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }



    @PostMapping("/search")
    public ResponseEntity<Page<UserDTO>> search(@RequestBody UserSearchCriteria criteria, Pageable pageable) {
        Page<UserDTO> page = userService.search(criteria, pageable);
        return ResponseEntity.ok(page);
    }







    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO newUser) {
        UserDTO created = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        UserDTO result = userService.updateUser(updatedUser);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


