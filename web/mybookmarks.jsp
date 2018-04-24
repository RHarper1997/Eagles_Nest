<%@page import="Model.*"%>
<%@include file = "header.jsp" %>
<%@include file = "user-navigation.jsp" %>
<%@include file = "site-navigation.jsp" %>
<main>
    <p>Home -> My Bookmarks</p>
    <h1><c:out value="${theUser.firstName}"/>'s Bookmarks</h1>
    <c:forEach items = "${currentProfile.getItems()}" var = "index">
        <form action="bookmarks" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Bookmark</th>
                        <th>Category</th>
                        <th>My Rating</th>
                            <c:if test="${index.getItem().category eq "Notable Players"}">
                            <th>Own this jersey?</th>
                        </c:if>
                        <c:if test="${index.getItem().category eq "History"}">
                        <th> Read this Article?</th>
                        </c:if>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="catalog?itemCode=${index.getItem().itemCode}"><c:out value="${index.getItem().name}"/></a></td>
            <input type="hidden" name="itemList" value="${index.getItem().itemCode}"/>
            <td><c:out value="${index.getItem().category}"/></td>
            <td><c:out value="${index.rating}"/></td>
            <c:if test="${index.isOwnIt() eq true}">
                <td>&checkmark;</td>
            </c:if>
            <c:if test="${index.isOwnIt() eq false}">
                <td>        </td>
            </c:if>
            <td><button type="submit" name="action" value="updateProfile">Update</button></td>
            <td><button type="submit" name="action" value="deleteItem">Delete</button></td>
            </tr>
            </tbody>
        </table>
    </form>
</c:forEach>
</main>
<%@include file = "footer.jsp" %>

