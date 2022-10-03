package com.barzykin.personal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Data
@ToString(callSuper = true, exclude = "employees")
@EqualsAndHashCode(callSuper = true, exclude = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Division extends AbstractEntity {
    private String name;
    @ManyToMany(mappedBy = "divisions", cascade = CascadeType.ALL)
    private Set<Employee> employees;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")

    @JsonManagedReference
    private City city;

    @JsonBackReference
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
