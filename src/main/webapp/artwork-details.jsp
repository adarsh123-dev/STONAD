<%@ page import="com.stonad.model.Artwork,com.stonad.model.User" %>
<% Artwork a=(Artwork)request.getAttribute("artwork"); User user=(User)session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title><%=a.getTitle()%> | STONAD</title><link rel="stylesheet" href="css/style.css"></head>
<body>
<nav><a class="logo" href="index.html">STONAD</a><div><a href="gallery">Explore</a><a href="logout">Logout</a></div></nav>
<main class="details">
    <div><img src="<%=a.getImagePath()%>" alt="<%=a.getTitle()%>"></div>
    <div>
        <p class="eyebrow"><%=a.getCategory()==null?"ART":a.getCategory()%></p>
        <h1><%=a.getTitle()%></h1>
        <h3>Artist: <%=a.getArtistName()%></h3>
        <p><%=a.getDescription()==null?"":a.getDescription()%></p>
        <% if(user != null && user.getId()==a.getArtistId()) { %>
            <form action="delete-artwork" method="post">
                <input type="hidden" name="id" value="<%=a.getId()%>">
                <button class="danger" type="submit">Delete Artwork</button>
            </form>
        <% } %>
    </div>
</main>
</body>
</html>
