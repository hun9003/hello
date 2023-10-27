<%--
  Created by IntelliJ IDEA.
  User: jinhu
  Date: 2023-10-26
  Time: 오후 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header-bar">
    <div class="logo" style="cursor: pointer" onclick="location.href = '/'">SBOOK</div>
    <div class="navigation-links">
        <c:choose>
            <c:when test="${sessionScope.member == null}">
                <a href="/account/join">회원가입</a>
                <a href="/account/login">로그인</a>
            </c:when>
            <c:otherwise>
                환영합니다 ${sessionScope.member.memberId} 님
                <a href="/member/list">친구목록</a>
                <a href="/account/logout">로그아웃</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
