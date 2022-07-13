package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.EmployeeRepositoryInMemory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", EmployeeRepositoryInMemory.getInstance().findAll());
        req.getRequestDispatcher("employees.jsp").forward(req, resp);
    }
}
