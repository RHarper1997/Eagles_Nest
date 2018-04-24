<%@include file = "header.jsp" %>
<%@include file = "user-navigation.jsp" %>
<%@include file = "site-navigation.jsp" %>
        <main>
            <p>Home -> Categories</p>
            <h2>Categories</h2>
            <ul id="categories">
                <li><strong>History</strong>
                <ul>
                    <li><strong><a href="catalog?itemCode=HIST"><c:out value="${db.get(4).name}"/></a></strong></li>
                    <li><strong><a href="catalog?itemCode=PLAY"><c:out value="${db.get(5).name}"/></a></strong></li>
                    <li><strong><a href="catalog?itemCode=FUNF"><c:out value="${db.get(3).name}"/></a></strong></li>
                </ul>
                </li>
                <li><strong>Notable Players</strong>
                <ul>
                    <li><strong><a href="catalog?itemCode=BD20"><c:out value="${db.get(0).name}"/></a></strong></li>
                    <li><strong><a href="catalog?itemCode=CW11"><c:out value="${db.get(2).name}"/></a></strong></li>
                    <li><strong><a href="catalog?itemCode=BW36"><c:out value="${db.get(1).name}"/></a></strong></li>
                    </form>
                </ul>
                </li>
            </ul>
        </main>
<%@include file = "footer.jsp" %>
        
