package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.EmployeeRepository;
import com.barzykin.personal.app.repositories.RepositoryFactory;
import com.barzykin.personal.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/employees")
public class EmployeeRestController extends HttpServlet {

    private EmployeeRepository employeeRepository = RepositoryFactory.getEmployeeRepository();
//    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
//        XmlMapper xmlMapper = new XmlMapper();
//        String xml = xmlMapper.writeValueAsString(employees);
        String json = mapper.writeValueAsString(employees);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
//        resp.setContentType("text/xml");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        EmployeeRestController controller = new EmployeeRestController();
//        List<Employee> employees = controller.employeeRepository.findAll();
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(employees);
//        System.out.println(json);
//    }
}
