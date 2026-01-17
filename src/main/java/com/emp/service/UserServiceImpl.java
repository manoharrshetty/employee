package com.emp.service;

import com.emp.dto.UserDTO;
import com.emp.dto.UserSearchCriteria;
import com.emp.exception.ResourceNotFoundException;
import com.emp.model.User;
import com.emp.repository.UserRepository;
import com.emp.spec.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }


    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = new User();
        user.setUserName(userDTO.getName());
        user.setUserRole(userDTO.getRole());
        user.setUserPassword(passwordEncoder.encode(userDTO.getPassword()));

        User saved = userRepository.save(user);
        return toDTO(saved);
    }




    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setUserRole(userDTO.getRole());
        user.setUserName(userDTO.getName());
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));


        User updated = userRepository.save(user);
        return toDTO(updated);
    }



    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }



    @Override
    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDTO(user);
    }

    @Override
    public Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable) {

        return userRepository.findAll(UserSpecification.byCriteria(criteria), pageable).map(this::toDTO);
    }
    private UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setName(user.getUserName());
        userDTO.setRole(user.getUserRole());
        return userDTO;

    }


}
