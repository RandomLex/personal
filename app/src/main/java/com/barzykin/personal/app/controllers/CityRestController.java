package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.CityRepository;
import com.barzykin.personal.app.repositories.EmployeeRepository;
import com.barzykin.personal.app.repositories.RepositoryFactory;
import com.barzykin.personal.model.City;
import com.barzykin.personal.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.barzykin.personal.app.constants.DbConstants.ID;
import static com.barzykin.personal.app.constants.HttpConstants.CONTENT_TYPE;
import static com.barzykin.personal.app.constants.HttpConstants.ENCODING;

@WebServlet("/api/cities")
public class CityRestController extends HttpServlet {


    private CityRepository cityRepository = RepositoryFactory.getCityRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object result;
        String id = req.getParameter(ID);
        if (id == null) {
            result = cityRepository.findAll();
        } else {
            Optional<City> optionalEmployee = cityRepository.find(Long.parseLong(id));
            if (optionalEmployee.isPresent()) {
                result = optionalEmployee.get();
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        City city = mapper.readValue(json, City.class);
        City savedCity = cityRepository.save(city);
        String employeeAsJson = mapper.writeValueAsString(savedCity);
        PrintWriter writer = resp.getWriter();
        writer.print(employeeAsJson);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        City city = mapper.readValue(json, City.class);
        City savedCity = cityRepository.save(city);
        String employeeAsJson = mapper.writeValueAsString(savedCity);
        PrintWriter writer = resp.getWriter();
        writer.print(employeeAsJson);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        String idAsString = req.getParameter(ID);
        if (idAsString == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Optional<City> deletedCity = cityRepository.remove(Long.parseLong(idAsString));
        if (deletedCity.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(deletedCity.get());
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }
}
