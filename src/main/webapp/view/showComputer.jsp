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

    <div class="mb-3 text-end">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/computer?action=show">➕ Thêm máy tính</a>
    </div>

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
            <tr>
                <td>${computer.name}</td>
                <td>${computer.status.moTa}</td>
                <td>${computer.price}</td>
                <td>
                    <div class="d-flex justify-content-center gap-2">
                        <a href="${pageContext.request.contextPath}/computer?action=delete&username=${computer.name}"
                           onclick="return confirm('Bạn có chắc chắn muốn xoá?')"
                           class="btn btn-sm btn-danger">
                            🗑 Xoá
                        </a>
                        <a href="${pageContext.request.contextPath}/computer?action=edit&name=${computer.name}"
                           class="btn btn-sm btn-primary">
                            ✏ Sửa
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex justify-content-between mt-4">
        <a href="${pageContext.request.contextPath}/admin?action=list" class="btn btn-secondary px-4 rounded-pill">⬅ Quay lại</a>
        <a href="${pageContext.request.contextPath}/service?action=showlist" class="btn btn-info">➡ Danh sách dịch vụ</a>
    </div>
</div>
</body>
</html>
