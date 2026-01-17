package com.test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericBox<T> {
    private T content;


}
