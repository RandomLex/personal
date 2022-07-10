package com.barzykin.personal.app.controllers;

import com.barzykin.personal.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", List.of(
                new Employee("Alex", 45, null, List.of(), 1000),
                new Employee("Viktor", 34, null, List.of(), 900)));
        req.getRequestDispatcher("employees.jsp").forward(req, resp);
    }
}
