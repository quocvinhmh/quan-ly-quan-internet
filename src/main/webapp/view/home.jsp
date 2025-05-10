<%--
  Created by IntelliJ IDEA.
  User: VINH-PC
  Date: 12/04/2025
  Time: 11:34 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<html>
<head>
    <meta charset="UTF-8">

    <title>Title</title>
    <style>
        html, body {
            height: 100%;
            margin: 0;
        }

        .bg-fullscreen {
            height: 100vh;
            background-image: url('https://pcmarket.vn/media/news/1203_vavab9.png'); /* thay bằng ảnh của bạn */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            position: relative;
        }

        .overlay-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
            text-align: center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <a class="navbar-brand" href="#" style="font-size: 30px; font-weight: bold;">CyberGame</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon">ok</span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/view/home.jsp">Trang chủ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/view/introduce.jsp">Giới thiệu</a>
            </li>
        </ul>

        <!-- Góc phải hiển thị tên người dùng và phân quyền -->
        <div class="d-flex align-items-center text-white">
            <%
                String username = (String) session.getAttribute("user");
                String role = (String) session.getAttribute("role");

                if (username != null) {
            %>
            <span class="me-3">👋 Xin chào, <strong><%= username %></strong></span>

            <% if ("admin".equals(role)) { %>
            <a class="btn btn-warning btn-sm me-2" href="${pageContext.request.contextPath}/admin?action=list">Quản trị</a>
            <a class="btn btn-success btn-sm me-2" href="${pageContext.request.contextPath}/manager?action=listComputer">Quản lý</a>
            <% } else if ("manager".equals(role)) { %>
            <a class="btn btn-success btn-sm me-2" href="${pageContext.request.contextPath}/manager?action=listComputer">Quản lý</a>
            <% } %>

            <a class="btn btn-outline-light btn-sm" href="${pageContext.request.contextPath}/Logout">Đăng xuất</a>
            <%
            } else {
            %>
            <a class="btn btn-light btn-sm" href="${pageContext.request.contextPath}/Login?account=Login">Đăng nhập</a>
            <%
                }
            %>
        </div>
    </div>
</nav>
<div class="bg-fullscreen">
    <div class="overlay-content container">
        <h1 class="display-1 fw-bold">Chào mừng bạn đến website!</h1>
    </div>
</div>
</body>
</html>
