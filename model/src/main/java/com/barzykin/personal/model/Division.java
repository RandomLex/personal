package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(callSuper = true, exclude = "employees")
@EqualsAndHashCode(callSuper = true, exclude = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Division extends AbstractEntity {
    private String name;
    private Set<Employee> employees;

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

    public Division withEmployees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Division addEmployee(Employee employee) {
        if (employee != null) {
            this.employees.add(employee);
        }
        return this;
    }
}
