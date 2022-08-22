<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="ru">
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<h2>Список сотрудников</h2>

<table>
    <tr>
        <th>Имя</th><th>Возраст</th><th>Зарплата</th><th>Должность</th><th>Отдел (Город)</th>
    </tr>

    <c:forEach items="${requestScope.employees}" var="employee">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.age}</td>
            <td>${employee.salary}</td>
            <td>${empty employee.title ? '----' : employee.title.name}</td>
            <c:set value="${employee.divisions}" scope="page" var="divisions"/>
            <c:forEach items="${divisions}" var="division">
                <td><c:out value=" ${empty division ? '----' : division.name} (${(division.city.name)})"/></td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>

</body>

</html>