package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * JavaBean
 * or
 * POJO (Plain Old Java Object (Простой старый джава-объект))
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AbstractEntity {
    private String name;
    private int age;
    private Title title;
    private List<Division> divisions;
    private int salary;

}
