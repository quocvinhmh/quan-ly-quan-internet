<%--
  Created by IntelliJ IDEA.
  User: VINH-PC
  Date: 13/04/2025
  Time: 11:55 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">

    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/Login" method="post">
    <fieldset>
        <legend>Đăng nhập</legend>

        <input type="hidden" name="account" value="success"/>

        <div class="form-group mb-3">
            <label for="Username">Username</label>
            <input type="text" class="form-control" id="Username" name="username" placeholder="Nhập tên đăng nhập" required>
        </div>

        <div class="form-group mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
        </div>
        <c:if test="${not empty message}">
            <div class="alert alert-danger">${message}</div>
        </c:if>
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </fieldset>
</form>
</body>
</html>
