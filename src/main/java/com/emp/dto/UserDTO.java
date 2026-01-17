package com.emp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO  extends BasicDTO{

    private String name;

    private String password;

    private String role;
}
