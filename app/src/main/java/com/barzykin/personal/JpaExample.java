package com.barzykin.personal;

import com.barzykin.personal.app.repositories.helpers.EntityManagerHelper;
import com.barzykin.personal.model.City;
import com.barzykin.personal.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaExample {
    public static void main(String[] args) {

        EntityManager em = EntityManagerHelper.getInstance().getEntityManager();

        //Hibernate session style

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        City city = session.find(City.class, 2L);
//        System.out.println(city);
//
//        Employee employee = session.find(Employee.class, 1L);
//        System.out.println(employee);
//
//        tx.commit();
//        session.close();


        //JPA style

        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        City city = em.find(City.class, 3L);
//        System.out.println("!!! " + city);

//        Employee employee = em.find(Employee.class, 11L);
//        System.out.println("!!! " + employee);
//
        TypedQuery<Employee> query = em.createQuery("from Employee ", Employee.class);
        List<Employee> employees = query.getResultList();
        System.out.println("---------");
        for (Employee emp : employees) {
            System.out.println(emp);
        }


        tx.commit();

        em.close();





    }
}
