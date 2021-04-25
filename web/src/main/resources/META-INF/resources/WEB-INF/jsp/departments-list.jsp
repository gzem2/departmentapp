<%@ page language="java" import="org.json.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <title>Список отделов - departmentapp</title>
    <h1><a href="<c:url value="/"/>">departmentapp</a></h1>
    <h2>Список отделов</h2>
</head>

<body>
    <form class="new-department-form" method="get" action="<c:url value="/department/edit"/>">
        <button class="new-department-button">Добавить отдел</button>
    </form>
    <br>

    <c:choose>
        <c:when test="${empty data}">
            <h2>Ничего не найдено</h2>
        </c:when>
        <c:when test="${not empty data}">
            <table>
                <tr>
                    <th>Отдел</th>
                    <th>Средняя зарплата</th>
                </tr>
        
            <c:set value="0" var="count" scope="page"/>
            <c:forEach items="${data}" var="dep">
                <tr>
                    <td>
                        <a href="<c:url value="/department/${dep.id}"/>">${dep.departmentName}</a>
                    </td>
                    <td>${salaries[count]}</td>
                    <c:set value="${count + 1}" var="count" scope="page"/>
                    <td>
                        <form class="edit-department" method="get" action="<c:url value="/department/edit"/>">
                            <input type="hidden" name="id" value="${dep.id}" />
                            <button>Изменить</button>
                        </form>
                    </td>
                    <td>
                        <form class="delete-department" method="get" action="<c:url value="/department/delete/${dep.id}"/>">
                            <button>Удалить</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </c:when>
    </c:choose>
</body>

</html>