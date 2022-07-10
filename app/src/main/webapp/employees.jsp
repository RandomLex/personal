<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="ru">
<head>
    <title>Employees</title>
</head>

<body>
<h2>Список сотрудников</h2>

<table>
    <tr>
        <th>Имя</th><th>Возраст</th><th>Зарплата</th>
    </tr>

    <c:forEach items="${requestScope.employees}" var="employee">
        <tr>
            <td>${employee.name}</td><td>${employee.age}</td><td>${employee.salary}</td>
        </tr>
    </c:forEach>

</table>

</body>

</html>