<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Danh sách Quản lý</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <h2 class="text-center mb-4 text-primary fw-bold">📋 Danh sách Quản lý</h2>

  <table class="table table-bordered table-hover shadow-sm bg-white rounded">
    <thead class="table-dark text-center">
    <tr>
      <th>ID</th>
      <th>Họ tên</th>
      <th>Tên đăng nhập</th>
      <th>Quyền hạn</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="m" items="${manages}">
      <tr class="text-center">
        <td>${m.id}</td>
        <td>${m.name}</td>
        <td>${m.username}</td>
        <td>
                    <span class="badge
                        <c:choose>
                            <c:when test="${m.role == 'admin'}">bg-danger</c:when>
                            <c:when test="${m.role == 'manager'}">bg-success</c:when>
                            <c:otherwise>bg-secondary</c:otherwise>
                        </c:choose>
                    ">
                        ${m.role}
                    </span>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <div class="text-center mt-4">
    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">⬅ Quay lại trang chủ</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
