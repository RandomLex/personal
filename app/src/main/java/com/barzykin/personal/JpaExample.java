package com.barzykin.personal;

import com.barzykin.demo.Car;
import com.barzykin.demo.complexid.Emp;
import com.barzykin.demo.complexid.EmpId;
import com.barzykin.demo.hierarchy.table.Animal;
import com.barzykin.demo.hierarchy.table.Bird;
import com.barzykin.demo.hierarchy.table.Fish;
import com.barzykin.personal.app.repositories.helpers.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

// ******* Конвертация java.sql.Date в java.util.Date при помощи @Temporal(TemporalType.DATE)

// вставка новых строка в таблицу.
// Создаём объекты, которые планируем вставить
//        Car bmw = new Car();
//        bmw.setModel("BMW");
//        bmw.setReleaseDate(new Date(2015, 7, 15));
//
//        Car lada = new Car();
//        lada.setModel("Lada");
//        lada.setReleaseDate(new Date(2020, 9, 3));
//// При помощи метода persist делаем сохранение строк (вставку) в базу данных.
//        em.persist(bmw);
//        em.persist(lada);

//// Чтение из базы данных (select), если мы знаем идентификатор (id)
//        Car bmw = em.find(Car.class, 1L);
//        Car lada = em.find(Car.class, 2L);


//// Чтение из базы данных (select), если мы знаем значение какого-то поля, в данном случае model
//        TypedQuery<Car> queryBmw = em.createQuery("select car from Car car where car.model='BMW'", Car.class);
//        Car bmw = queryBmw.getSingleResult();
//        System.out.println(bmw);
//
//// Обновляем значение даты у BMW и сохраняем результат в базу (update)
//        bmw.setReleaseDate(Date.from(LocalDate.of(2015, 7, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        Car updatedBMW = em.merge(bmw);
//        System.out.println(bmw);

// Удаление автомобиля (сначала создаём, потом комментируем это и потом удаляем)

//        Car reno = Car.builder()
//                .model("Reno")
//                .releaseDate(Date.from(LocalDate.of(2015, 7, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .build();
//
//        em.persist(reno);

//// Чтение Рено
//        TypedQuery<Car> queryReno = em.createQuery("select car from Car car where car.model='Reno'", Car.class);
//        Car reno = queryReno.getSingleResult();
//        System.out.println(reno);
//
//// Удаление заранее прочитанного Рено
//        em.remove(reno);


// Создание объекта со составным первичным ключом (идентификатором)
//        create table emp (
//                lastname varchar(20),
//                name varchar(20),
//                salary int4
//        );
//        alter table emp add primary key (lastname, name);

        Emp ivanIvanov = Emp.builder()
                .empId(EmpId.builder()
                        .lastname("Ivanov")
                        .name("Ivan")
                        .build())
                .salary(1000)
                .build();

        em.persist(ivanIvanov);



// ***************** Иерархии классов
// Одна таблица  (Single table)


// Вставка новых животных
//        Bird penguin = Bird.builder()
//                .origin("Penguin")
//                .flyable(false)
//                .growing("nested")
//                .build();
//
//        Bird eagle = Bird.builder()
//                .origin("Eagle")
//                .flyable(true)
//                .growing("nested")
//                .build();
//
//        Fish shark = Fish.builder()
//                .origin("Shark")
//                .skeleton("Cartilaginous")
//                .poisoned(false)
//                .build();
//
//        em.persist(penguin);
//        em.persist(eagle);
//        em.persist(shark);

//        System.out.println("-------Птицы---------");
//        TypedQuery<Bird> birdTypedQuery = em.createQuery("from Bird ", Bird.class);
//        List<Bird> resultList = birdTypedQuery.getResultList();
//        for (Bird bird : resultList) {
//            System.out.println(bird);
//        }
//
//        System.out.println("-------Рыбы---------");
//        TypedQuery<Fish> fishTypedQuery = em.createQuery("from Fish ", Fish.class);
//        fishTypedQuery.getResultList()
//                .forEach(System.out::println);
//
//        System.out.println("-------Все животные---------");
//        TypedQuery<Animal> animalTypedQuery = em.createQuery("from Animal ", Animal.class);
//        animalTypedQuery.getResultStream()
//                .forEach(animal -> System.out.println(animal));

//        City city = em.find(City.class, 3L);
//        CityDto cityDto = CityDto.builder()
//                .name(city.getName())
//                .divisions(new HashSet<>(city.getDivisions().stream()
//                        .map(division -> new DivisionDto(division.getName()))
//                        .collect(Collectors.toSet())))
//                .build();
//        System.out.println("!!! " + cityDto);

//        Employee employee = em.find(Employee.class, 11L);
//        System.out.println("!!! " + employee);
//
//        TypedQuery<Employee> query = em.createQuery("from Employee ", Employee.class);
//        List<Employee> employees = query.getResultList();
//        System.out.println("---------");
//        for (Employee emp : employees) {
//            System.out.println(emp);
//        }


        tx.commit();

        em.close();


    }
}
