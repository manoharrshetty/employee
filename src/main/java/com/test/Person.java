package com.test;

import lombok.Data;

@Data
public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private int age;

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

}
