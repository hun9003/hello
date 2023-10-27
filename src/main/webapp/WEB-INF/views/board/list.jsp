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
    <title>Main feed</title>
    <link rel="stylesheet" href="<c:url value="../../resources/assets/css/main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../resources/assets/css/header.css"/> ">
</head>
<body>
<div id="main">
    <c:import url="/header"/>
    <div class="container">
        <div class="btn-area">
            <a class="write-btn" href="/board/write">글쓰기</a>
        </div>
        <c:forEach items="${list}" var="peed">
            <div class="card">
                <div class="post-title">${peed.memberId}</div>
                <div class="post-content">
                    ${peed.content}
                </div>
                <p>${peed.formatCreatedAt} 에 작성되었습니다.</p>
                <a class="card-item like" href="#">LIKE&nbsp;<span class="like_count">0</span></a>
                <a class="card-item comment" href="#">COMMENT&nbsp;<span class="comment_count">0</span></a>
            </div>
        </c:forEach>
        <!-- 추가 게시물 카드를 여기에 삽입 -->
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
