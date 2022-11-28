package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.EmployeeRepository;
import com.barzykin.personal.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@Slf4j
public class EmployeeRestController {
//    private static final Logger log = LoggerFactory.getLogger(EmployeeRestController.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeRestController(@Qualifier("employeeRepositoryOrm") EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees() {
        log.info("Retrieving employees started");
        List<Employee> employees = employeeRepository.findAll();
        log.info("Employees {}. Zeroing employee: {}  ", employees, employees.get(0));
        return employees;
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.of(employeeRepository.find(id));
    }


    @PostMapping("/api/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
         return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @PutMapping("/api/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        if (!id.equals(employee.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.of(employeeRepository.remove(id));
    }

}
