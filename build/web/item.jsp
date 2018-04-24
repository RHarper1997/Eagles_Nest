<%@include file = "header.jsp" %>
<%@include file = "user-navigation.jsp" %>
<%@include file = "site-navigation.jsp" %>
<main>
    <p>Home -> Categories -> <c:out value="${item.name}"/></p>
    <img class="pic" src=<c:out value = "${item.imageURL}"/> alt=<c:out value="${item.name}"/>>
    <form action="bookmarks" method="post">
        <h2><c:out value = "${item.name}"/></h2>
        <input type="hidden" name="itemList" value="${item.itemCode}"/>
        <h3><c:out value = "${item.category}"/></h3>
        <c:choose >
            <c:when test="${item.rating == 1}">
                <span>&starf;</span>
            </c:when>
            <c:when test="${item.rating == 2}">
                <span>&starf;&starf;</span>
            </c:when>
            <c:when test="${item.rating == 3}">
                <span>&starf;&starf;&starf;</span>
            </c:when>
            <c:when test="${item.rating == 4}">
                <span>&starf;&starf;&starf;&starf;</span>
            </c:when>
            <c:when test="${item.rating == 5}">
                <span>&starf;&starf;&starf;&starf;&starf;</span>
            </c:when>
        </c:choose>
        <p><c:out value = "${item.description}"/></p>
        <button type="submit" name="action" value="updateProfile">Give Feedback!</button>
        <button type="submit" name="action" value="save">Bookmark</button>
    </form>
        <form action="catalog" method="post">
            <button type="submit">Return to Categories</button>
        </form>
    </form>
</main>
<%@include file = "footer.jsp" %>
