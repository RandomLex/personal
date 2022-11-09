package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController  {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(path = "employees", method = RequestMethod.GET)
    public ModelAndView getAllEmployees() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", employeeRepository.findAll());
        modelAndView.setViewName("employees");
        return modelAndView;
    }
}
