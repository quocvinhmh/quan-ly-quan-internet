<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cập nhật máy tính</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <form action="${pageContext.request.contextPath}/computer?action=update" method="post" class="p-4 border rounded shadow-sm bg-white">
    <h3 class="mb-4">Cập nhật máy tính</h3>

    <!-- Tên máy -->
    <div class="mb-3">
      <label for="name" class="form-label">Tên máy:</label>
      <input type="text" name="name" id="name" class="form-control" value="${computer.name}" required />
    </div>

    <!-- Giá -->
    <div class="mb-3">
      <label for="price" class="form-label">Giá (VND):</label>
      <input type="number" name="price" id="price" class="form-control" value="${computer.price}" required />
    </div>
    <!-- Trạng thái -->
    <div class="mb-3">
      <label for="status" class="form-label">Trạng thái:</label>
      <select name="status" id="status" class="form-select">
        <option value="off" ${computer.status.moTa == 'off' ? 'selected' : ''}>Đã kết thúc</option>
      </select>
    </div>

    <!-- Hiển thị lỗi -->
    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Nút submit -->
    <div class="d-flex justify-content-between mt-4">
      <a href="${pageContext.request.contextPath}/computer?action=showlist" class="btn btn-secondary">⬅ Quay lại</a>
      <button type="submit" class="btn btn-primary">Cập nhật</button>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
