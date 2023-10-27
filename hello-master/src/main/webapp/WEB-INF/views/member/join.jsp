<%--
  Created by IntelliJ IDEA.
  User: jinhu
  Date: 2023-10-26
  Time: 오후 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>회원가입 폼</h2>
<form action="/account/join" method="post">
    <label for="memberId">ID:</label><br>
    <input type="text" id="memberId" name="memberId" required><br>
    <label for="memberPassword">PW:</label><br>
    <input type="password" id="memberPassword" name="memberPassword" required><br><br>
    <input type="submit" value="회원가입">
</form>
</body>
</html>
