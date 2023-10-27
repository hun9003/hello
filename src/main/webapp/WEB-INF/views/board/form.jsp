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
    <link rel="stylesheet" href="<c:url value="../../resources/assets/css/main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../resources/assets/css/header.css"/> ">
</head>
<body>
<div id="main">
    <c:import url="/header"/>
    <div id="form">
        <h2>피드작성</h2>
        <form action="/board/write" method="post">
            <textarea id="content" name="content" cols="40" rows="6" required></textarea>
            <p>
                <input type="submit" value="업로드">
            </p>
        </form>
    </div>
</div>

<c:if test="${sessionScope.member == null}">
    <script>
        alert("로그인이 필요한 페이지 입니다.");
        location.href = '<c:url value="/account/login"/>';
    </script>
</c:if>
</body>
</html>
