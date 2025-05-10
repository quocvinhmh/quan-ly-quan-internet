<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách dịch vụ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4 text-primary fw-bold">📋 Danh sách dịch vụ</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="mb-3 text-end">
        <a href="${pageContext.request.contextPath}/service?action=showadd" class="btn btn-success">
            ➕ Thêm dịch vụ mới
        </a>
    </div>

    <table class="table table-bordered table-hover shadow-sm bg-white rounded">
        <thead class="table-dark text-center">
        <tr>
            <th>ID</th>
            <th>Tên dịch vụ</th>
            <th>Giá (VNĐ)</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="service" items="${services}">
            <tr class="text-center">
                <td>${service.id}</td>
                <td>${service.name}</td>
                <td>${service.price}</td>
                <td>
                    <div class="d-flex justify-content-center gap-2">
                        <a href="${pageContext.request.contextPath}/service?action=showupdate&id=${service.id}" class="btn btn-sm btn-primary">
                            ✏ Cập nhật
                        </a>
                        <a href="${pageContext.request.contextPath}/service?action=delete&id=${service.id}"
                           onclick="return confirm('Bạn có chắc chắn muốn xoá?')"
                           class="btn btn-sm btn-danger">
                            🗑 Xoá
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-start mt-4">
        <a href="${pageContext.request.contextPath}/admin?action=list" class="btn btn-secondary rounded-pill">
            ⬅ Quay lại trang quản lý
        </a>
    </div>
</div>

</body>
</html>
