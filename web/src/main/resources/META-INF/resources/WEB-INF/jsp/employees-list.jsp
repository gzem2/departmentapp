<%@ page language="java" import="org.json.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE HTML>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <title>Список сотрудников - ${title} - departmentapp</title>
    <h1><a href="<c:url value="/"/>">departmentapp</a></h1>
    <h2>Список сотрудников - ${title}</h2> 
</head>

<body>
    <form action="<c:url value="/employees"/>">
        <label class="find-label">Найти сотрудников по дате рождения:</label>
        <c:choose>
            <c:when test="${empty searchDate}">
                <input class="date-picker" type="date" name="birthday" value="1990-01-01" min="1956-01-01" max="2003-01-01"/>
            </c:when>
            <c:when test="${not empty searchDate}">
                <input class="date-picker" type="date" name="birthday" value="${searchDate}" min="1956-01-01" max="2003-01-01"/>
            </c:when>
        </c:choose>
        <button>Искать</button>
    </form>
    <br>

    <form action="<c:url value="/employees"/>">
        <label class="find-label">Найти сотрудников родившихся в период:</label>
        <c:choose>
            <c:when test="${empty searchFrom}">
                <input class="date-picker" type="date" name="birthdayFrom" value="1990-01-01" min="1956-01-01" max="2003-01-01"/>
            </c:when>
            <c:when test="${not empty searchFrom}">
                <input class="date-picker" type="date" name="birthdayFrom" value="${searchFrom}" min="1956-01-01" max="2003-01-01"/>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${empty searchTo}">
                <input class="date-picker" type="date" name="birthdayTo" value="1990-01-01" min="1956-01-01" max="2003-01-01"/>
            </c:when>
            <c:when test="${not empty searchTo}">
                <input class="date-picker" type="date" name="birthdayTo" value="${searchTo}" min="1956-01-01" max="2003-01-01"/>
            </c:when>
        </c:choose>
        <button>Искать</button>
    </form>
    <br>

    <c:choose>
        <c:when test="${empty departmentId}">
            <form class="new-employee-form" method="get" action="<c:url value="/employee/edit"/>">
                <button class="new-employee-button">Добавить сотрудника</button>
            </form>
        </c:when>
        <c:when test="${not empty departmentId}">
            <form class="new-employee-form" method="get" action="<c:url value="/employee/edit"/>">
                <input type="hidden" name="department" value="${departmentId}" /> 
                <button class="new-employee-button">Добавить сотрудника</button>
            </form>
        </c:when>
    </c:choose>
    <br>

    <c:choose>
        <c:when test="${empty data}">
            <h2>Ничего не найдено</h2>
        </c:when>
        <c:when test="${not empty data}">
            <table>
                <tr>
                    <th>Ф.И.О.</th>
                    <th>Отдел</th>
                    <th>Дата рождения</th>
                    <th>Зарплата</th>
                </tr>
        
            <c:set value="0" var="count" scope="page"/>
            <c:forEach items="${data}" var="obj">
                <tr>
                    <td>
                        <c:set value="${obj.surname} ${obj.name} ${obj.patronymic}" var="fullname" scope="page"/>
                        <a href="<c:url value="/employee/${obj.id}"/>">${fullname}</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty departmentName}">
                                ${departmentName}
                            </c:when>
                            <c:when test="${not empty departmentList}">
                                ${departmentList[count]}
                                <c:set value="${count + 1}" var="count" scope="page"/>
                            </c:when>
                        </c:choose>
                    </td>
                    <td class="birthday-column">
                        <c:set var="birthday" value="${fn:split(obj.birthday, '-')}" />  
                        <c:set var="birthday" value="${birthday[2]}/${birthday[1]}/${birthday[0]}" />  
                        ${birthday}
                    </td>
                    <td>
                        ${obj.salary}
                    </td>
                    <td>
                        <form class="edit-employee" method="get" action="<c:url value="/employee/edit"/>">
                            <input type="hidden" name="id" value="${obj.id}" />
                            <button>Изменить</button>
                        </form>
                    </td>
                    <td>
                        <form class="delete-employee" method="get" action="<c:url value="/employee/delete/${obj.id}"/>">
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