<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="vi">
<head>

  <meta charset="UTF-8">
  <title>Thêm quản lý</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="card shadow-lg">
    <div class="card-header bg-success text-white text-center fw-bold fs-4">
      ➕ Thêm quản lý mới
    </div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/admin?action=add" method="post" accept-charset="UTF-8">
        <div class="mb-3">
          <label for="name" class="form-label">👤 Họ và tên</label>
          <input type="text" class="form-control" id="name" name="name" placeholder="Nhập họ tên đầy đủ" required>
        </div>

        <div class="mb-3">
          <label for="username" class="form-label">🔑 Tên đăng nhập</label>
          <input type="text" class="form-control" id="username" name="username" placeholder="ví dụ: vinh123" required>
        </div>

        <div class="mb-3">
          <label for="password" class="form-label">🔑 Mật khẩu đăng nhập</label>
          <input type="text" class="form-control" id="password" name="password" placeholder="ví dụ: abc123" required>
        </div>

        <div class="mb-3">
          <label for="role" class="form-label">🛡 Quyền hạn</label>
          <select class="form-select" name="role" id="role" required>
            <option value="manager">Quản lý</option>
            <option value="user">Người dùng</option>
            <option value="staff">Nhân viên</option>
          </select>
        </div>
        <c:if test="${not empty error}">
          <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="d-flex justify-content-between mt-4">
          <a href="${pageContext.request.contextPath}/admin?action=list" class="btn btn-secondary px-4 rounded-pill">⬅ Quay lại</a>
          <div>
            <button type="submit" class="btn btn-secondary px-4 rounded-pill">Thêm</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
