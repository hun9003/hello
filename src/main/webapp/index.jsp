<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
Hello World2!
</h1>
<br/>
<c:choose>
    <c:when test="${sessionScope.member != null}">
        <a href="/account/logout">로그아웃</a>
    </c:when>
    <c:otherwise>
        <a href="/account/join">회원가입</a>
        <a href="/account/login">로그인</a>
    </c:otherwise>
</c:choose>
<a href="/board/list">게시판</a>
</body>
</html>