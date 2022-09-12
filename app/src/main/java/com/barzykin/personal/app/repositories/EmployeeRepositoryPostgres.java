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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.barzykin.personal.app.constants.DbConstants.AGE;
import static com.barzykin.personal.app.constants.DbConstants.C_ID;
import static com.barzykin.personal.app.constants.DbConstants.C_NAME;
import static com.barzykin.personal.app.constants.DbConstants.D_ID;
import static com.barzykin.personal.app.constants.DbConstants.D_NAME;
import static com.barzykin.personal.app.constants.DbConstants.E_ID;
import static com.barzykin.personal.app.constants.DbConstants.E_NAME;
import static com.barzykin.personal.app.constants.DbConstants.SALARY;
import static com.barzykin.personal.app.constants.DbConstants.T_ID;
import static com.barzykin.personal.app.constants.DbConstants.T_NAME;

public class EmployeeRepositoryPostgres extends AbstractRepository<Employee> implements EmployeeRepository {
    private static final String SELECT_ALL_FROM_EMPLOYEES = "select" +
            " e.id e_id, e.name e_name, salary, age," +
            " d.id d_id, d.name d_name," +
            " c.id c_id, c.name c_name," +
            " t.id t_id, t.name t_name" +
            " from employee e" +
            " left join title t on e.title_id = t.id" +
            " left join department_employee de on e.id = de.employee_id" +
            " left join department d on d.id = de.department_id" +
            " left join city c on d.city_id = c.id";

    private static final String INSERT_EMPLOYEE = "insert into employee (name, age, salary) values (?, ?, ?) returning id";
    private static final String UPDATE_EMPLOYEE = "update employee e set name = ?, age = ?, salary = ?";
    private static final String DELETE_FROM_EMPLOYEE = "delete from employee e";
    private static final String FILTER_BY_ID = " where e.id = ?";


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
    protected String selectAllRows() {
        return SELECT_ALL_FROM_EMPLOYEES;
    }

    @Override
    protected String selectRowForId() {
        return SELECT_ALL_FROM_EMPLOYEES + FILTER_BY_ID;
    }

    @Override
    protected String insertRow() {
        return INSERT_EMPLOYEE;
    }

    @Override
    protected String updateRow() {
        return UPDATE_EMPLOYEE + FILTER_BY_ID;
    }

    @Override
    protected String deleteRow() {
        return DELETE_FROM_EMPLOYEE + FILTER_BY_ID;
    }

    @Override
    protected void setParametersForInsertSql(Employee model, PreparedStatement ps) throws SQLException {
        ps.setString(1, model.getName());
        ps.setInt(2, model.getAge());
        ps.setInt(3, model.getSalary());
    }

    @Override
    protected void setParametersForUpdateSql(Employee model, PreparedStatement ps) throws SQLException {
        ps.setString(1, model.getName());
        ps.setInt(2, model.getAge());
        ps.setInt(3, model.getSalary());
        ps.setLong(4, model.getId());
    }

    @Override
    protected Map<Long, Employee> resultSetToModel(ResultSet rs)  throws SQLException {
        Map<Long, Employee> employeeMap = new HashMap<>();
        Map<Long, Division> divisionMap = new HashMap<>();
        Map<Long, City> cityMap = new HashMap<>();
        Map<Long, Title> titleMap = new HashMap<>();

        while (rs.next()) {
            Long eId = rs.getLong(E_ID);
            Long dId = rs.getLong(D_ID);
            Long tId = rs.getLong(T_ID);
            Long cId = rs.getLong(C_ID);

            if (tId != 0) {
                titleMap.putIfAbsent(tId, new Title()
                        .withId(tId)
                        .withName(rs.getString(T_NAME)));
            }

            if (cId != 0) {
                cityMap.putIfAbsent(cId, new City()
                        .withId(cId)
                        .withName(rs.getString(C_NAME)));
            }

            if (dId != 0) {
                divisionMap.putIfAbsent(dId, new Division()
                        .withId(dId)
                        .withName(rs.getString(D_NAME))
                        .withCity(cityMap.get(cId)));
            }

            employeeMap.putIfAbsent(eId, new Employee()
                    .withId(eId)
                    .withName(rs.getString(E_NAME))
                    .withSalary(rs.getInt(SALARY))
                    .withAge(rs.getInt(AGE))
                    .withTitle(titleMap.get(tId))
                    .addDivision(divisionMap.get(dId)));

            employeeMap.computeIfPresent(eId, (id, emp) -> emp.addDivision(divisionMap.get(dId)));
        }
        return employeeMap;

    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

}
