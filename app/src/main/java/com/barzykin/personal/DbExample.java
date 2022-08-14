package com.barzykin.personal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbExample {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/personal",
                    "personal_owner",
                    "As45tK");

//            Statement statement = connection.createStatement();
//
            String nameToFind = "Виктор";
//            String nameToFind = "Виктор; delete from employee";
//
//            ResultSet resultSet = statement.executeQuery(
//                    "select id e_id, name e_name, age from employee e where age > " + ageToFind);

            //language=SQL

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id e_id, name e_name, age from employee e where name = ?");


            preparedStatement.setString(1, nameToFind);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                long id = resultSet.getLong("e_id");
                String name = resultSet.getString("e_name");
                int age = resultSet.getInt("age");

                Employee1 employee = new Employee1();
                employee.setId(id);
                employee.setName(name);
                employee.setAge(age);

                System.out.println(employee);
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Employee1 {
        private Long id;
        private String name;
        private int age;
    }
}
