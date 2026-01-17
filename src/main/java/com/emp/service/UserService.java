package com.emp.service;

import com.emp.dto.UserDTO;
import com.emp.dto.UserSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO getById(Long id);
    List<UserDTO> getAllUser();
    UserDTO updateUser(UserDTO dto);

    void deleteUser(Long id);

    Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable);

    UserDTO createUser(UserDTO dto);
    }
