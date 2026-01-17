package com.emp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeptDTO extends BasicDTO{

    private String name;
}
