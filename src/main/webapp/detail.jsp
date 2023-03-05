<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Product Detail</title>
</head>
<body>
  <p>Product Code : ${productCode}</p>
  <p>Product Name : ${productName}</p>
  <p>Product Price : <fmt:formatNumber type = "number"
                                       maxFractionDigits = "3" value = "${productPrice}" /></p>
  <p>Productcer : ${productcer}</p>
</body>
</html>
