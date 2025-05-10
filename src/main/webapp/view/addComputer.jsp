<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm máy tính</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <form action="${pageContext.request.contextPath}/computer?action=add" method="post" class="p-4 border rounded shadow-sm bg-white">
        <h3 class="mb-4">Thêm máy tính</h3>
        <div class="mb-3">
            <label for="name" class="form-label">Tên máy:</label>
            <input type="text" name="name" id="name" class="form-control" required />
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Username:</label>
            <input type="text" name="username" id="username" class="form-control" required />
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá (VND):</label>
            <input type="number" name="price" id="price" class="form-control" required />
        </div>
        <input type="hidden" name="status" id="status" value="off" />

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>

        <div class="d-flex justify-content-between mt-4">
            <a href="${pageContext.request.contextPath}/computer?action=showlist" class="btn btn-secondary">⬅ Quay lại</a>
            <button type="submit" class="btn btn-primary">Thêm máy tính</button>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

