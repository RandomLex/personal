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
        <th>Имя</th><th>Возраст</th><th>Зарплата</th><th>Должность</th>
    </tr>

    <c:forEach items="${requestScope.employees}" var="employee">
<%--        <c:set var="title" value="${employee.title}" scope="page"/>  --%>
        <tr>
            <td>${employee.name}</td>
            <td>${employee.age}</td>
            <td>${employee.salary}</td>
            <td>${empty employee.title ? '----' : employee.title.name}</td>
<%--            <td><c:out value="${empty employee.title ? '----' : employee.title.name}" /></td>--%>
        </tr>
    </c:forEach>

</table>

</body>

</html>