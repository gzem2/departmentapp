<%@ page language="java" import="org.json.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE HTML>
<html>

<head>
    <c:set value="${data.surname} ${data.name} ${data.patronymic}" var="fullname" scope="page"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <title>Данные сотрудника: ${fullname} - departmentapp</title>
    <h1><a href="<c:url value="/"/>">departmentapp</a></h1>
    <h2>Данные сотрудника: ${fullname}</h2>
</head>

<body>
<div class="form-container">
<form action="<c:url value="/employee"/>" method="post" id="employee_form"></form>
<table>
    <tr>
        <td>
            ID Сотрудника
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="id" form="employee_form" value="${data.id}" pattern="[0-9]{1,9}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="id" form="employee_form" placeholder="Auto ID" value="" pattern="[0-9]{1,9}"/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>
            Отдел
        </td>
        <td>
            <select name="departmentId" form="employee_form">
                <c:forEach items="${departmentList}" var="dep">
                    <c:choose>
                        <c:when test="${not empty data}">
                            <c:choose>
                                <c:when test="${data.departmentId == dep.id}">
                                    <option value="${dep.id}" selected="selected">${dep.departmentName}</option>
                                </c:when>
                                <c:when test="${data.departmentId != dep.id}">
                                    <option value="${dep.id}">${dep.departmentName}</option>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${empty data}">
                            <c:choose>
                                <c:when test="${not empty departmentId}">
                                    <c:choose>
                                        <c:when test="${departmentId == dep.id}">
                                            <option value="${dep.id}" selected="selected">${dep.departmentName}</option>
                                        </c:when>
                                        <c:when test="${departmentId != dep.id}">
                                            <option value="${dep.id}">${dep.departmentName}</option>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:when test="${empty departmentId}">
                                    <option value="${dep.id}">${dep.departmentName}</option>
                                </c:when>
                            </c:choose>
                        </c:when>
                    </c:choose>        
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            Фамилия
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="surname" form="employee_form" value="${data.surname}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="surname" form="employee_form" value=""/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>
            Имя
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="name" form="employee_form" value="${data.name}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="name" form="employee_form" value=""/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>
            Отчество
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="patronymic" form="employee_form" value="${data.patronymic}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="patronymic" form="employee_form" value=""/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>
            Дата рождения
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input class="date-picker" type="date" name="birthday" form="employee_form" value="${data.birthday}" min="1956-01-01" max="2003-01-01"/>
                </c:when>
                <c:when test="${empty data}">
                    <input class="date-picker" type="date" name="birthday" form="employee_form" min="1956-01-01" max="2003-01-01"/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>
            Зарплата
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="salary" form="employee_form" value="${data.salary}" pattern="[0-9]{1,9}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="salary" form="employee_form" value="" pattern="[0-9]{1,9}"/>
                </c:when>
            </c:choose>
        </td>
    </tr>
</table>
<button class="cancel-button" onclick="javascript:history.back()">Отмена</button>
<button class="submit-button" form="employee_form">Сохранить</button>
</div>
</body>

</html>