<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hóa đơn</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">🧾 Hoá đơn máy vừa tắt</h2>

    <c:forEach var="computer" items="${computers}">
        <table class="table table-bordered table-striped shadow">
            <tr><th>Tên máy</th><td>${computer.name}</td></tr>
            <tr><th>Trạng thái</th><td>${computer.status.moTa}</td></tr>
            <tr><th>Thời gian sử dụng</th><td>${computer.timeUse}</td></tr>
            <tr><th>Tiền phải trả (VNĐ)</th><td><strong>${computer.getDongia()}</strong></td></tr>
        </table>

        <div class="shadow p-3 mb-5 bg-white rounded">
            <a href="${pageContext.request.contextPath}/manager?action=clear&name=${computer.name}"
               class="btn btn-success btn-sm">✅ Xác nhận đã thanh toán</a>

        </div>
    </c:forEach>

</div>
</body>
</html>
