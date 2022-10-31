package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.EmployeeRepository;
import com.barzykin.personal.app.repositories.RepositoryFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees")
public class EmployeeController extends AbstractController {
    private EmployeeRepository employeeRepository;

    @Override
    public void init(ServletConfig config) throws ServletException  {
        super.init(config);
        config.getServletContext().getAttribute("ctx");
        employeeRepository = ctx.getBean("employeeRepository", EmployeeRepository.class);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", employeeRepository.findAll());
        req.getRequestDispatcher("employees.jsp").forward(req, resp);
    }
}
