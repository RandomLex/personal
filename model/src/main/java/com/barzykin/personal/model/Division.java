package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Division extends AbstractEntity {
    private String name;
    private List<Employee> employees;
    private City city;

    public Division withId(Long id) {
        this.id = id;
        return this;
    }

    public Division withName(String name) {
        this.name = name;
        return this;
    }

    public Division withCity(City city) {
        this.city = city;
        return this;
    }

    public Division withEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }
}
