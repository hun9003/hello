<%-- 상단 new post 창 --%>
<form action="/post" method="post">
    <textarea name="content"></textarea>
    <input type="submit" value="Submit"/>
</form>

<%-- Main feed 표시 --%>
<c:forEach var="post" items="${posts}">
    <p>${post.content}</p>
    <button onclick="likePost(${post.postId})">Like</button>
    ${post.likes.size()}
    <%-- Comment 창, 삭제 버튼 등 추가 --%>
</c:forEach>

<%-- Friends 페이지로 이동하는 버튼 --%>
<a href="/friends">Friends</a>
