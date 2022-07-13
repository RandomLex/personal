package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<Employee> findAll();

    Optional<Employee> find(long id);

    Optional<Employee> findByName(String name);

    Employee save(Employee employee);

    Optional<Employee> remove(long id);
}
