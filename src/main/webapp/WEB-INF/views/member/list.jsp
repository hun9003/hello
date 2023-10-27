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
    <title>회원리스트</title>
    <link rel="stylesheet" href="<c:url value="../../resources/assets/css/main.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../resources/assets/css/header.css"/> ">
</head>
<body>
<div id="main">
    <c:import url="/header"/>
    <div id="container" style="padding: 20px">
        <c:forEach items="${list}" var="member">
            <div class="card">
                <p>${member.memberId}</p>
                <c:choose>
                    <c:when test="${member.follow == true}">
                        <a class="card-item" style="color: cornflowerblue" href="#" onclick="follow('${member.memberId}')">FOLLOWING</a>
                    </c:when>
                    <c:otherwise>
                        <a class="card-item" href="#" onclick="follow('${member.memberId}')">FOLLOW</a>
                    </c:otherwise>
                </c:choose>

            </div>
        </c:forEach>
    </div>

</div>
<script>
    function follow(followId) {
        // 새로운 폼 엘리먼트 생성
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = '/member/follow';

        // followId를 위한 hidden input 필드 생성
        var followIdInput = document.createElement('input');
        followIdInput.type = 'hidden';
        followIdInput.name = 'followId';
        followIdInput.value = followId;

        // followId input 필드를 폼에 추가
        form.appendChild(followIdInput);

        // 폼을 body에 추가
        document.body.appendChild(form);

        // 폼 제출
        form.submit();
    }
</script>
</body>
</html>
