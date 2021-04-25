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
<table>
    <tr>
        <td>
            ID Сотрудника
        </td>
        <td>
            ${data.id}
        </td>
    </tr>
    <tr>
        <td>
            ID Отдела
        </td>
        <td>
            ${data.departmentId} (<a href="<c:url value="/department/${data.departmentId}"/>">${departmentName}</a>)
        </td>
    </tr>
    <tr>
        <td>
            Фамилия
        </td>
        <td>
            ${data.surname}
        </td>
    </tr>
    <tr>
        <td>
            Имя
        </td>
        <td>
            ${data.name}
        </td>
    </tr>
    <tr>
        <td>
            Отчество
        </td>
        <td>
            ${data.patronymic}
        </td>
    </tr>
    <tr>
        <td>
            Дата рождения
        </td>
        <td>
            <c:set var="birthday" value="${fn:split(data.birthday, '-')}" />  
            <c:set var="birthday" value="${birthday[2]}/${birthday[1]}/${birthday[0]}" />  
            ${birthday}
        </td>
    </tr>
    <tr>
        <td>
            Зарплата
        </td>
        <td>
            ${data.salary}
        </td>
    </tr>
</table>
<button class="cancel-button" onclick="javascript:history.back()">Назад</button>
</div>
</body>

</html>