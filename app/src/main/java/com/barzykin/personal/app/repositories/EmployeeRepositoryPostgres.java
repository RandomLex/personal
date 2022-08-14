package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.exception.ApplicationException;
import com.barzykin.personal.model.City;
import com.barzykin.personal.model.Division;
import com.barzykin.personal.model.Employee;
import com.barzykin.personal.model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.barzykin.personal.app.constants.DbConstants.*;
import static com.barzykin.personal.app.constants.DbConstants.AGE;
import static com.barzykin.personal.app.constants.DbConstants.E_ID;
import static com.barzykin.personal.app.constants.DbConstants.E_NAME;
import static com.barzykin.personal.app.constants.DbConstants.SALARY;
import static com.barzykin.personal.app.constants.DbConstants.T_ID;

public class EmployeeRepositoryPostgres implements EmployeeRepository {

    private static final String SELECT_ALL_FROM_EMPLOYEES = "select" +
            " e.id e_id, e.name e_name, salary, age," +
            " d.id d_id, d.name d_name," +
            " c.id c_id, c.name c_name," +
            " t.id t_id, t.name t_name" +
            " from employee e" +
            " join title t on e.title_id = t.id" +
            " join department_employee de on e.id = de.employee_id" +
            " join department d on d.id = de.department_id" +
            " join city c on d.city_id = c.id";
    private final RepositoryDataSource dataSource;

    private static volatile EmployeeRepositoryPostgres instance;

    private EmployeeRepositoryPostgres(RepositoryDataSource dataSource) {
        this.dataSource = dataSource;

    }

    public static EmployeeRepositoryPostgres getInstance(RepositoryDataSource dataSource) {
        if (instance == null) {
            synchronized (EmployeeRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryPostgres(dataSource);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Employee> findAll() {
        Map<Long, Employee> employeeMap = new HashMap<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_FROM_EMPLOYEES);
             ResultSet rs = ps.executeQuery()) {
            Map<Long, Division> divisionMap = new HashMap<>();
            Map<Long, City> cityMap = new HashMap<>();
            Map<Long, Title> titleMap = new HashMap<>();

            while (rs.next()) {
                Long eId = rs.getLong(E_ID);
                Long dId = rs.getLong(D_ID);
                Long tId = rs.getLong(T_ID);
                Long cId = rs.getLong(C_ID);

                titleMap.putIfAbsent(tId, new Title()
                        .withId(tId)
                        .withName(rs.getString(T_NAME)));

                cityMap.putIfAbsent(cId, new City()
                        .withId(cId)
                        .withName(rs.getString(C_NAME)));

                divisionMap.putIfAbsent(dId, new Division()
                        .withId(dId)
                        .withName(rs.getString(D_NAME))
                        .withCity(cityMap.get(cId)));

                employeeMap.putIfAbsent(eId, new Employee()
                        .withId(eId)
                        .withName(rs.getString(E_NAME))
                        .withSalary(rs.getInt(SALARY))
                        .withAge(rs.getInt(AGE))
                        .withTitle(titleMap.get(tId))
                        .addDivision(divisionMap.get(dId)));

                employeeMap.computeIfPresent(eId, (id, emp) -> emp.addDivision(divisionMap.get(dId)));
            }

        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Optional<Employee> find(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public Optional<Employee> remove(long id) {
        return Optional.empty();
    }
}
