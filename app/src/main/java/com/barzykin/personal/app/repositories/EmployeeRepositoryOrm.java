package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryOrm extends AbstractRepositoryOrm<Employee> implements EmployeeRepository {

    public EmployeeRepositoryOrm() {
        tClass = Employee.class;
    }

}
