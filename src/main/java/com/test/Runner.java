package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Runner {
    public static void main(String[] args) {

        //tryGenerics();
        tryComparable();

    }


    /*
     generics let you write classes, methods, and data structures that work with different data types while maintaining type safety
     (for example, List<String> instead of a raw List).
     The main benefits are fewer runtime errors, no need for explicit casting, and clearer, more reusable code that’s checked at compile time.
     */
    public static void tryGenerics() {

        GenericBox<String> box = new GenericBox<>();
        box.setContent("Hello Generics");
        String content = box.getContent();
        System.out.println(content);

        GenericBox<Integer> intBox = new GenericBox<>();
        intBox.setContent(123);
        Integer intContent = intBox.getContent();
        System.out.println(intContent);
    }

    /*Funda-men-tally, a lambda expression is just a shorter way of writing an implementation of a method for later execution
    A lambda in Java essentially consists of three parts: a parenthesized set of parameters, an arrow, and then a body,
    which can either be a single expression or a block of Java code
     */
    public static void tryLambda() {
        String text = "Hello Lambda";

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 1");
            }
        };

        Runnable r2 = () -> System.out.println("Hello World 2");
        Runnable r3 = () -> {
            System.out.println("Hello World 3");
            System.out.println("Hello World 4" + text + "closed");

        };
        r1.run();
        r2.run();
        r3.run();
    }


   public static void tryComparable() {
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");
        p1.setAge(30);

       Person p2 = new Person();
       p2.setFirstName("Jane");
       p2.setLastName("Smith");
       p2.setAge(25);

       Person p3 = new Person();
       p3.setFirstName("Manohar");
       p3.setLastName("Smith");
       p3.setAge(20);

       int comparison = p1.compareTo(p2);
        if (comparison < 0) {
            System.out.println(p1.getFirstName() + " is younger than " + p2.getFirstName());
        } else if (comparison > 0) {
            System.out.println(p1.getFirstName() + " is older than " + p2.getFirstName());
        } else {
            System.out.println(p1.getFirstName() + " is the same age as " + p2.getFirstName());
        }

        List<Person> peoples = new ArrayList<>();
        peoples.add(p1);
        peoples.add(p2);
        peoples.add(p3);
       Collections.sort(peoples);
        System.out.println(peoples);
    }







}
