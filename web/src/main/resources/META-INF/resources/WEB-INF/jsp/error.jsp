<%@ page language="java" import="org.json.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <title>Ошибка - departmentapp</title>
    <h1><a href="<c:url value="/"/>">departmentapp</a></h1>
    <h2>Ошибка</h2>
</head>

<body>
    <div class="form-container">
        <p>${message}</p>
        <button class="cancel-button" onclick="javascript:history.back()">Назад</button>
    </div>
</body>