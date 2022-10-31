package com.barzykin.personal.app.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {

    protected ApplicationContext ctx;
    private boolean firstRun = true;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (firstRun) {
            ctx = new AnnotationConfigApplicationContext("com.barzykin.personal.app");
            firstRun = false;
        }
    }
}
