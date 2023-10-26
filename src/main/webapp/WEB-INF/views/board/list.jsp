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
<h2>게시판</h2>
<c:if test="${sessionScope.member == null}">
    <script>
        alert("회원만 이용할 수 있습니다.");
        location.href = '/account/login';
    </script>
</c:if>
</body>
</html>
