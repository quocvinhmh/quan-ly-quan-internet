<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách máy tính</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="text-center mb-4">📋 Danh sách máy tính</h2>
  <table class="table table-bordered table-hover table-striped shadow">
    <thead class="table-dark text-center">
    <tr>
      <th>Name</th>
      <th>Status</th>
      <th>Price</th>
      <th>Function</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="computer" items="${computers}">
<c:choose>
  <c:when test="${computer.status.moTa eq 'off'}">
      <tr>
        <td>${computer.name}</td>
        <td>${computer.status.moTa}</td>
        <td>${computer.price}</td>
        <td>
          <div class="d-flex justify-content-center gap-2">
            <c:choose>
              <c:when test="${computer.status.moTa eq 'off'}">
                <a href="${pageContext.request.contextPath}/manager?action=turnon&name=${computer.name}" class="btn btn-success btn-sm"   onclick="return confirm('🔌 Bạn có chắc chắn muốn bật máy này không?')">Bật máy</a>
              </c:when>
            </c:choose>
          </div>
        </td>
      </tr>
  </c:when>
</c:choose>
    </c:forEach>
    </tbody>
  </table>
  <div class="d-flex justify-content-between mt-4">
    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary px-4 rounded-pill">⬅ Quay lại</a>
    <div>
      <a href="${pageContext.request.contextPath}/manager?action=orderComputer" class="btn btn-secondary px-4 rounded-pill">Máy đang sử dụng =></a>
    </div>
  </div>
</div>
</body>
</html>
