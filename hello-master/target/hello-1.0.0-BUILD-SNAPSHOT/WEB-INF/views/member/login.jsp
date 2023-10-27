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
    <title>로그인</title>
</head>
<body>
<h2>로그인 폼</h2>
<form action="/account/login" method="post">
    <label for="memberId">ID:</label><br>
    <input type="text" id="memberId" name="memberId" required><br>
    <label for="memberPassword">PW:</label><br>
    <input type="password" id="memberPassword" name="memberPassword" required><br><br>
    <input type="submit" value="로그인">
</form>
<a href="/account/join">회원가입</a>
<c:if test="${param.status != null and param.status == 'fail'}">
    <script>
        alert("아이디 혹은 비밀번호가 틀립니다.");
    </script>
</c:if>

</body>
</html>