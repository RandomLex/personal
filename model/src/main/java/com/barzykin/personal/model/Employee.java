package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * JavaBean
 * or
 * POJO (Plain Old Java Object (Простой старый джава-объект))
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private Title title;
    private List<Division> divisions;
    private int salary;

}
