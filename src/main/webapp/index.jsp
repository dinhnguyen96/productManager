<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Manager</title>
    <style>
        #products {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #products td, #products th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #products tr:nth-child(even){background-color: #f2f2f2;}

        #products tr:hover {background-color: #ddd;}

        #products th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<h1>Product Manager</h1>
<table id="products">
    <tr>
        <th>Id</th>
        <th>productCode</th>
        <th>productName</th>
        <th>productPrice</th>
        <th>productProductcer</th>
        <th>Note</th>
        <th>Cập nhật</th>
        <th>Xóa</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><a href="/product?action=detail&id=${product.id}">${product.id}</a></td>
            <td>${product.productCode}</td>
            <td>${product.productName}</td>
            <td><fmt:formatNumber type = "number"
                                  maxFractionDigits = "3" value = "${product.productPrice}" /></td>
            <td>${product.manufacture_id}</td>
            <td>${product.note}</td>
            <td><a href="/product?action=edit&id=${product.id}">Cập nhật</a></td>
            <td><a href="/product?action=remove&id=${product.id}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/product?action=add">Thêm</a>
</body>
</html>