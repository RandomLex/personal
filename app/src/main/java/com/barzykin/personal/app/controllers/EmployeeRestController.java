package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.EmployeeRepository;
import com.barzykin.personal.app.repositories.EmployeeRepositoryData;
import com.barzykin.personal.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestController {
//    private static final Logger log = LoggerFactory.getLogger(EmployeeRestController.class);
    private final EmployeeRepositoryData employeeRepository;

//    public EmployeeRestController(@Qualifier("employeeRepositoryData") EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }


    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees() {
        log.info("Retrieving employees started");
        List<Employee> employees = employeeRepository.findAll();
        log.info("Employees {}. Zeroing employee: {}  ", employees, employees.get(0));
        return employees;
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.of(employeeRepository.findById(id));
    }


    @PostMapping("/api/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
         return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @SneakyThrows
    @GetMapping("/api/employees-by-name/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name) {
        String decodedName = URLDecoder.decode(name, "UTF-8");
        return employeeRepository.findByName(decodedName);
    }

    @SneakyThrows
    @GetMapping(value = "/api/employees", params = {"from", "to"})
    public List<Employee> getEmployeeBetweenAges(@RequestParam int from, @RequestParam int to) {
        return employeeRepository.findAllByAgeBetween(from, to);
    }

    @SneakyThrows
    @GetMapping(value = "/api/employees", params = {"salary>"})
    public List<Employee> getEmployeeSalaryBiggerThan(@RequestParam(name = "salary>") int salary) {
        return employeeRepository.findAllBySalaryIsGreaterThan(salary);
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
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Employee employee = employeeOpt.get();
            employeeRepository.delete(employee);
            return ResponseEntity.ok(employee);
        }
    }

}
