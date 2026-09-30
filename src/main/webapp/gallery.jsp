<%@ page import="java.util.*,com.stonad.model.Artwork,com.stonad.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><title>Explore Art | STONAD</title><link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav>
    <a class="logo" href="index.html">STONAD</a>
    <div>
        <a href="gallery">Explore</a>
        <% User user=(User)session.getAttribute("user"); %>
        <% if(user != null && "ARTIST".equals(user.getRole())) { %>
            <a class="btn" href="upload.html">Upload</a>
        <% } %>
        <% if(user != null) { %>
            <a href="logout">Logout</a>
        <% } else { %>
            <a href="login.html">Login</a>
        <% } %>
    </div>
</nav>

<section class="page-head">
    <p class="eyebrow">DISCOVER</p>
    <h1>Explore artwork</h1>
    <form class="search" action="gallery" method="get">
        <input name="search" value="<%= request.getParameter("search")==null?"":request.getParameter("search") %>" placeholder="Search artwork, artist...">
        <select name="category">
            <option value="">All categories</option>
            <% Map<Integer,String> categories=(Map<Integer,String>)request.getAttribute("categories");
               String selected=request.getParameter("category");
               for(Map.Entry<Integer,String> c:categories.entrySet()) { %>
                <option value="<%=c.getKey()%>" <%=String.valueOf(c.getKey()).equals(selected)?"selected":""%>><%=c.getValue()%></option>
            <% } %>
        </select>
        <button class="btn" type="submit">Search</button>
    </form>
</section>

<section class="grid">
<% List<Artwork> artworks=(List<Artwork>)request.getAttribute("artworks");
   if(artworks.isEmpty()) { %>
   <div class="empty"><h2>No artwork found</h2><p>Try another search or category.</p></div>
<% } else { for(Artwork a:artworks) { %>
    <article class="art-card">
        <a href="artwork?id=<%=a.getId()%>">
            <img src="<%=a.getImagePath()%>" alt="<%=a.getTitle()%>">
        </a>
        <div class="art-info">
            <span><%=a.getCategory()==null?"Art":a.getCategory()%></span>
            <h2><%=a.getTitle()%></h2>
            <p>by <%=a.getArtistName()%></p>
            <a href="artwork?id=<%=a.getId()%>">View artwork →</a>
        </div>
    </article>
<% }} %>
</section>
</body>
</html>
