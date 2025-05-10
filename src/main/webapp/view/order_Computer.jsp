<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
  <title>Quản lý máy tính</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="text-center mb-4">📋 Danh sách máy tính đang dùng</h2>

  <table class="table table-bordered table-hover table-striped shadow">
    <thead class="table-dark text-center">
    <tr>
      <th>Tên máy</th>
      <th>Trạng thái</th>
      <th>Bắt đầu</th>
      <th>Số phút sử dụng</th>
      <th>Tiền (VNĐ)</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="computer" items="${computers}">
      <c:choose>
      <c:when test="${computer.status.moTa eq 'on'}">
      <tr>
        <td>${computer.name}</td>
        <td>${computer.status.moTa}</td>
        <td>${computer.timeUse}</td>
        <td>
          <c:choose>
            <c:when test="${computer.status.moTa == 'on'}">
              ${computer.getRemainingMinutes()} phút
            </c:when>
            <c:otherwise>
              --
            </c:otherwise>
          </c:choose>
        </td>
        <td>${computer.price}</td>
        <td>
          <c:choose>
            <c:when test="${computer.status.moTa == 'off'}">
              <a href="${pageContext.request.contextPath}/manager?action=turnon&name=${computer.name}"
                 onclick="return confirm('Bạn có chắc muốn bật máy này không?')"
                 class="btn btn-success btn-sm">Bật máy</a>
            </c:when>
            <c:when test="${computer.status.moTa == 'on'}">
              <a href="${pageContext.request.contextPath}/manager?action=turnoff&name=${computer.name}"
                 onclick="return confirm('Bạn có chắc muốn tắt máy này không?')"
                 class="btn btn-danger btn-sm">Tắt máy</a>
            </c:when>
          </c:choose>
        </td>
      </tr>
      </c:when>
      </c:choose>
    </c:forEach>
    </tbody>
  </table>
  <div class="d-flex justify-content-between mt-4">
    <a href="${pageContext.request.contextPath}/manager?action=listComputer" class="btn btn-secondary px-4 rounded-pill">⬅ Quay lại</a>
  </div>
</div>
</body>
</html>
