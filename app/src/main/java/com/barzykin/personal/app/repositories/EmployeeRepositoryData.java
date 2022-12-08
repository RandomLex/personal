package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// CRUD -> Create, Read, Update, Delete
public interface EmployeeRepositoryData extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);
//
    List<Employee> findAllByAgeBetween(int from, int to);

    List<Employee> findAllBySalaryIsGreaterThan(int salary);

}
