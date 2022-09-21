package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Entity
public class Employee extends AbstractEntity {
    private String name;
    private int age;
    @OneToOne
    private Title title;
    @ManyToMany
    @JoinTable(
            name = "department_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Division> divisions;
    private int salary;

    public Employee withId(Long id) {
        this.id = id;
        return this;
    }

    public Employee withName(String name) {
        this.name = name;
        return this;
    }

    public Employee withAge(int age) {
        this.age = age;
        return this;
    }

    public Employee withDivisions(Set<Division> divisions) {
        this.divisions = divisions;
        return this;
    }

    public Employee addDivision(Division division) {
        if (division != null) {
            this.divisions.add(division);
        }
        return this;
    }

    public Employee withSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public Employee withTitle(Title title) {
        this.title = title;
        return this;
    }

}
