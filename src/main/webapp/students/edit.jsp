<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27/9/2022
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>From Edit</h1>
<a href="/students">Back Menu</a>
<c:if test='${requestScope["message"]!=null}'>
    <span style="color:palegreen">${requestScope["message"]}</span>
</c:if>
<form method="post">
    <label>Name</label>
    <input type="text" name="name" value="${requestScope["student"].getName()}"/><br>
    <label>AGE</label>
    <input type="text" name="age" value="${requestScope["student"].getAge()}"/><br>
    <button type="submit">Edit</button>
</form>
</body>
</html>
