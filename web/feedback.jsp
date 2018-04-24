<%-- 
    Document   : feedback
    Created on : Feb 9, 2018, 2:03:25 PM
    Author     : regin
--%>

<%@include file = "header.jsp" %>
<%@include file = "user-navigation.jsp" %>
<%@include file = "site-navigation.jsp" %>
<main>

    <h2>Give Feedback for</h2>
    <img class="pic" src=<c:out value = "${theItem.getItem().imageURL}"/> alt=<c:out value = "${theItem.getItem().name}"/>>
    <h2><c:out value = "${theItem.getItem().name}"/></h2>
    <h3><c:out value = "${theItem.getItem().category}"/></h3>
    <p><c:out value = "${theItem.getItem().description}"/></p>
    <label for="<c:out value = "${theItem.getItem().itemCode}"/>">Rating:</label>
    <form action="bookmarks" method="post">
        <input type="hidden" name="itemList" value="<c:out value = "${theItem.getItem().itemCode}"/>"/>
        <input type="text" name="<c:out value = "${theItem.getItem().itemCode}"/>" value="<c:out value = "${theItem.rating}"/>"/>
        <button type="submit" name="action" value="updateRating">Confirm Rating</button>
    </form>
    <form action="bookmarks" method="post">
        <input type="hidden" name="itemList" value="<c:out value = "${theItem.getItem().itemCode}"/>"/>
        <input type="hidden" name="<c:out value = "${theItem.getItem().itemCode}"/>" value="<c:out value = "${theItem.ownIt}"/>"/>
        <c:if test="${theItem.getItem().category eq "Notable Players"}">
            <button type="submit" name="action" value="updateFlag" >I own this jersey! &checkmark;</button>
        </c:if>
        <c:if test="${theItem.getItem().category eq "History"}">
            <button type="submit" name="action" value="updateFlag" >I read this article! &checkmark;</button>
        </c:if>
    </form>
</main>
<%@include file = "footer.jsp" %>