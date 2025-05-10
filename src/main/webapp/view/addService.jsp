<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>➕ Thêm dịch vụ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4 text-success fw-bold">➕ Thêm dịch vụ mới</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/service?action=add" method="post"
          class="shadow p-4 bg-white rounded" accept-charset="UTF-8">

        <div class="mb-3">
            <input type="hidden" class="form-control" id="id" name="id" required value="0"/>
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Tên dịch vụ:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="VD: Nước suối" required />
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Giá (VNĐ):</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="VD: 10000" required />
        </div>

        <div class="d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/service?action=list" class="btn btn-secondary">
                ⬅ Quay lại
            </a>
            <button type="submit" class="btn btn-success">
                💾 Lưu dịch vụ
            </button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
