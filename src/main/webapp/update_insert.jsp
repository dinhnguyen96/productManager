<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật thông tin sản phẩm</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'add'}">
        <h2>Thêm sản phẩm</h2>
        <c:set var="action" value="${action}"/>
    </c:when>
    <c:otherwise>
        <h2>Cập nhật sản phẩm</h2>
        <c:set var="action" value="${action}"/>
    </c:otherwise>
</c:choose>
<c:url var="url" value="/product?action=${action}"/>
<form action="${url}" method="post">
    <input type="hidden" name="id" value="${id}"><br>
    <label for="productCode">Product Code</label><br>
    <input type="text" id="productCode" name="productCode" value="${productCode}" placeholder="product code"><br>
    <label for="productName">Product Name</label><br>
    <input type="text" id="productName" value="${productName}" name="productName" placeholder="product name"><br>
    <label for="productPrice">Product Price<label><br>
        <fmt:formatNumber type = "number" var = "price"
                          maxFractionDigits = "3" value = "${productPrice}" />
        <input type="text" id="productPrice" value="${price}" name="productPrice"
               placeholder="product price"><br>
        <label for="productcer">Producer</label><br>
        <input type="text" id="productcer" value="${producer}" name="producter" placeholder="productcer"><br>
        <label for="note">Note</label><br>
        <input type="text" id="note" value="${note}" name="note" placeholder="note"><br>
        <input type="submit" value="Submit">
</form>
</body>
</html>
