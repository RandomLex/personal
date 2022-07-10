package com.barzykin.personal.model;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        Employee ivan = new Employee("Ivan", 35, new Title("Developer"), List.of(), 1000);
        System.out.println(ivan);
    }
}
