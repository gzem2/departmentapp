<%@ page language="java" import="org.json.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE HTML>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <title>Данные отдела - departmentapp</title>
    <h1><a href="<c:url value="/"/>">departmentapp</a></h1>
    <h2>Данные отдела</h2>
</head>

<body>
<div class="form-container">
<form action="<c:url value="/department"/>" method="post" id="department_form"></form>
<table>
    <tr>
        <td>
            ID Отдела
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="id" form="department_form" value="${data.id}" pattern="[0-9]{1,9}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="id" form="department_form" placeholder="Auto ID" value="" pattern="[0-9]{1,9}"/>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>
            Название отдела
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty data}">
                    <input type="text" name="departmentName" form="department_form" value="${data.departmentName}"/>
                </c:when>
                <c:when test="${empty data}">
                    <input type="text" name="departmentName" form="department_form" value=""/>
                </c:when>
            </c:choose>
        </td>
    </tr>
</table>
<button class="cancel-button" onclick="javascript:history.back()">Отмена</button>
<button class="submit-button" form="department_form">Сохранить</button>
</div>
</body>

</html>