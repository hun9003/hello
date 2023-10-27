<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> SBook Main feed </title>
</head>
<body>
<h2> SBook Main feed </h2>

<!-- 상단 new post 창 -->
<div>
    <form action="/board/createPost" method="post">
        <textarea name="content" placeholder="Write your new post..."></textarea>
        <button type="submit">Post</button>
    </form>
</div>

<!-- Friends 버튼 -->
<div>
    <a href="/friends">Friends</a>
</div>

<!-- 게시물 목록 -->
<c:forEach items="${posts}" var="post">
    <div>
        <p>${post.author}</p>
        <p>${post.content}</p>
        <p>${post.timestamp}</p>
        <!-- Like와 댓글 기능은 간략하게 생략하였습니다. -->
        <!-- 구현하려면 각 버튼마다 AJAX 기능 추가가 필요합니다. -->
        <button>Like</button>
        <span>${post.likesCount}</span>
        <form action="/board/addComment" method="post">
            <input type="text" name="comment" placeholder="Add a comment...">
            <button type="submit">Comment</button>
        </form>
        <button>Delete Post</button>
    </div>
</c:forEach>
</body>
</html>
