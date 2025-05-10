<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cập nhật dịch vụ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4 text-primary fw-bold">✏ Cập nhật dịch vụ</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/service?action=update" method="post"
          class="shadow p-4 bg-white rounded" accept-charset="UTF-8">
        <!-- Hidden ID -->
        <input type="hidden" name="id" value="${service.id}" />

        <div class="mb-3">
            <label for="name" class="form-label">Tên dịch vụ:</label>
            <input type="text" class="form-control" id="name" name="name" value="${service.name}" required />
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Giá (VNĐ):</label>
            <input type="number" class="form-control" id="price" name="price"
                   step="0.01" min="0" value="${service.price}" required />
        </div>

        <div class="d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/service?action=list" class="btn btn-secondary">
                ⬅ Quay lại
            </a>
            <button type="submit" class="btn btn-primary">💾 Lưu thay đổi</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
