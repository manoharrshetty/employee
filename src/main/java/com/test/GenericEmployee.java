package com.test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericEmployee<T, V> {
    private V departmentData;
    private T employeeData;

}
