package com.barzykin.personal;

import com.barzykin.demo.hqljoin.ProductDto;
import com.barzykin.personal.app.repositories.helpers.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

// Чтение Рено
//        TypedQuery<Car> queryReno = em.createQuery("select car from Car car where car.model='BMW'", Car.class);
//        Car reno = queryReno.getSingleResult();
//        System.out.println(reno);

//// Удаление заранее прочитанного Рено
//        em.remove(reno);

// Чтение Car с заданным Id и возвратом названия автомобиля

//        TypedQuery<String> query = em.createQuery("select car.model from Car car where id = 2", String.class);
//        String model = query.getSingleResult();
//        System.out.println(model);


// Чтение названия автомобиля и даты выпуска в специализированный класс CarDto, имеющий поля name и date

//        TypedQuery<CarDto> query = em.createQuery("select new com.barzykin.demo.CarDto(car.model, car.releaseDate) from Car car where id = 1", CarDto.class);
//        CarDto carDto = query.getSingleResult();
//        System.out.println(carDto);


// ************************ Запросы к нескольким связанным таблицам
//        create table product (
//                id bigserial not null,
//                name varchar(20) not null,
//                price int not null,
//                product_id int8 null
//);
//
//        alter table product rename column product_id to product_type_id;
//
//        insert into product_type (name) values
//                ('Computer'),
//                ('Smartphone');
//
//        select * from product_type;
//
//        insert into product (name, price, product_type_id) values
//                ('Iphone', 1200, 2),
//                ('Xiaomi', 800, 2),
//        ('Samsung', 1100, 2),
//        ('HP', 2500, 1),
//        ('Asus', 2300, 1),
//        ('Xiaomi', 1150, 1)
//        ;

//        TypedQuery<ProductType> productTypes = em.createQuery("select pt from ProductType pt", ProductType.class);
//        productTypes.getResultList().forEach(System.out::println);

//        Возникает N + 1 проблема
//        JPA вызывает запросы
//           -- 1 -- на таблицу product_type
//             -- N -- на таблицу product, подставляя в product_type_id id из каждой строки, вернувшейся из перво

//        select * from product_type
//        select * from product where product_type_id = 1;
//        select * from product where product_type_id = 2;


//      Неявный (implicit) join
//        TypedQuery<ProductType> productTypes = em.createQuery("select distinct p.productType from Product p ", ProductType.class);
//        productTypes.getResultList().forEach(System.out::println);

//        //Явный (explicit) join
//        TypedQuery<ProductType> productTypes = em.createQuery("select distinct pt from ProductType pt join pt.products", ProductType.class);
//        for (ProductType productType : productTypes.getResultList()) {
//            System.out.println(productType);
//        }

        //Явный (explicit) join-fetch
        //Добавление слова fetch лучше, чем использование Fetch.EAGER, потому, что Fetch.EAGER находится в классе Entity
        //и будет использоваться в любом запросе из любой части кода
        //А fetch можно вставить именно в тот запрос, где действительно нужно вытащить все необходимые данные,
        //а другие запросы продолжат работать не вытаскивая ненужные им данные из базы.
//        TypedQuery<ProductType> productTypes = em.createQuery("select distinct pt from ProductType pt join fetch pt.products p where p.price > 1150", ProductType.class);
//        for (ProductType productType : productTypes.getResultList()) {
//            System.out.println(productType);
//        }


//        //Пусть мы хотим вытащить только имена продуктов дороже 1150 долларов
//        TypedQuery<String> products = em.createQuery("select p.name from Product p where p.price > 1150", String.class);
//        for (String productName : products.getResultList()) {
//            System.out.println(productName);
//        }

        //Пусть мы хотим вытащить имена и цены продуктов дороже 1150 долларов
        // Для простой печати полей или неизвестных атрибутов разных классов
        //можно использовать нетипизированный запрос Query, который вернёт нам значения
        //в виде массивов объектов Object[]. Однако такой способ не очень удобен
//        Query query = em.createQuery("select p.name, p.price from Product p where p.price > 1150");
//        List<Object> resultList = query.getResultList();
//        for (Object product : resultList) {
//            for (int i = 0; i < ((Object[]) product).length; i += ((Object[]) product).length) {
//                System.out.println(((Object[]) product)[i] + ": " + ((Object[]) product)[i + 1]);
//            }
//        }

//        //или преобразуя это через StreamAPI
//        Query query = em.createQuery("select p.name, p.price from Product p where p.price > 1150");
//        List<Object> resultList = query.getResultList();
//        resultList.stream()
//                .flatMap(element -> Arrays.stream((Object[]) element))
//                .forEach(System.out::println);


//        //Более удобно доставать несколько полей перекладывая их автоматически в некие дополнительные классы,
//        //которые получили название Data Transfer Objects -- DTO
//        TypedQuery<ProductDto> productDtos = em.createQuery("select new com.barzykin.demo.hqljoin.ProductDto(p.name, p.price) from Product p where p.price > 1150", ProductDto.class);
//        for (ProductDto productDto : productDtos.getResultList()) {
//            System.out.println(productDto);
//        }

//        //Пример запроса через Dto для join с фильтром по цене
//        TypedQuery<ProductWithTypeDto> query = em.createQuery("select distinct new com.barzykin.demo.hqljoin.ProductWithTypeDto(p.name, pt.name, p.price) from ProductType pt join pt.products p where p.price > 1150", ProductWithTypeDto.class);
//        for (ProductWithTypeDto withTypeDto : query.getResultList()) {
//            System.out.println(withTypeDto);
//        }

        // До этого мы все фильтры в запросе указывали непосредственно в JPQL.
        // Однако, в реальной жизни их значения фильтров передают в виде параметров
//        TypedQuery<ProductDto> query = em.createQuery("select new com.barzykin.demo.hqljoin.ProductDto(p.name, p.price) from Product p where p.price > :priceGreaterThan", ProductDto.class);
//        query.setParameter("priceGreaterThan", 1200);
//        for (ProductDto productDto : query.getResultList()) {
//            System.out.println(productDto);
//        }

        //Наиболее часто встречающиеся запросы можно положить прямо в Entity (см. class Product)
        //например, поиск по цене
//        TypedQuery<ProductDto> query = em.createNamedQuery("byPriceGreaterThan", ProductDto.class);
//        query.setParameter("priceGreaterThan", 1150);
//        query.getResultStream().forEach(System.out::println);

        //или поиск по имени
        TypedQuery<ProductDto> query = em.createNamedQuery("byProductName", ProductDto.class);
        query.setParameter("name", "Xiaomi");
        query.getResultStream().forEach(System.out::println);


// Создание объекта со составным первичным ключом (идентификатором)
//        create table emp (
//                lastname varchar(20),
//                name varchar(20),
//                salary int4
//        );
//        alter table emp add primary key (lastname, name);

//        Emp ivanIvanov = Emp.builder()
//                .empId(EmpId.builder()
//                        .lastname("Ivanov")
//                        .name("Ivan")
//                        .build())
//                .salary(1000)
//                .build();
//
//        em.persist(ivanIvanov);

//        Emp ivan = em.find(Emp.class, EmpId.builder()
//                .lastname("Ivanov")
//                .name("Ivan")
//                .build());
//        System.out.println(ivan);


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
