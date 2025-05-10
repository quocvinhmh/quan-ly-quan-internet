<%--
  Created by IntelliJ IDEA.
  User: VINH-PC
  Date: 12/04/2025
  Time: 11:34 CH
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html; charset=UTF-8" language="java" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <style>
    body{
      background: #212529;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
  <a class="navbar-brand" href="#" style="font-size: 30px;  font-weight: bold;">CyberGame</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
    <span class="navbar-toggler-icon"></span>
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


    <div class="d-flex align-items-center text-white">
      <%
        String username = (String) session.getAttribute("user");
        String role = (String) session.getAttribute("role");

        if (username != null) {
      %>
      <span class="me-3">👋 Xin chào, <strong><%= username %></strong></span>

      <% if ("admin".equals(role)) { %>
      <a class="btn btn-warning btn-sm me-2" href="${pageContext.request.contextPath}/view/admin.jsp">Quản trị</a>
      <a class="btn btn-success btn-sm me-2" href="${pageContext.request.contextPath}/view/admin.jsp">Quản lý</a>
      <% } else if ("manager".equals(role)) { %>
      <a class="btn btn-success btn-sm me-2" href="${pageContext.request.contextPath}/view/admin.jsp">Quản lý</a>
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
<section class="py-5 text-center bg-dark text-white">
  <div class="container">
    <h2 class="mb-3">Chào mừng đến với <strong>CYBER GAME VINH</strong></h2>
    <p class="lead">
      Cyber Game Vinh là phòng máy cao cấp tại TP.HCM, mang đến trải nghiệm chơi game mượt mà, cấu hình khủng, không gian hiện đại và dịch vụ chuyên nghiệp.
    </p>
    <p>
      Với hơn 100 máy được trang bị card đồ họa RTX, ghế gaming êm ái, điều hòa mát lạnh và đội ngũ hỗ trợ tận tình – chúng tôi là điểm đến lý tưởng cho mọi game thủ.
    </p>
  </div>
</section>
<footer class="bg-dark text-white pt-5 pb-4 w-100">
  <div class="w-100 px-5">
    <div class="row">

      <div class="col-md-4 mb-4">
        <h5 class="text-uppercase fw-bold">CYBER GAME VINH</h5>
        <p>Phòng máy cấu hình cao, dịch vụ chuyên nghiệp. Trải nghiệm gaming đỉnh cao tại TP.HCM.</p>
      </div>


      <div class="col-md-4 mb-4">
        <h6 class="text-uppercase fw-bold">Giờ mở cửa</h6>
        <ul class="list-unstyled">
          <li>Thứ 2 - Chủ nhật: 8h00 - 2h00</li>
          <li>Hotline: 0901.234.567</li>
          <li>Email: cybervinh@gmail.com</li>
        </ul>
      </div>


      <div class="col-md-4 mb-4">
        <h6 class="text-uppercase fw-bold">Kết nối</h6>
        <a href="#" class="text-white d-block mb-1">📘 Facebook</a>
        <a href="#" class="text-white d-block mb-1">📍 Google Maps</a>
        <a href="#" class="text-white d-block">💬 Zalo Chat</a>
      </div>
    </div>
  </div>

  <!-- Line dưới -->
  <div class="text-center py-3 border-top border-secondary mt-3 w-100">
    © 2025 Cyber Game Vinh. All rights reserved.
  </div>
</footer>

</body>
</html>
