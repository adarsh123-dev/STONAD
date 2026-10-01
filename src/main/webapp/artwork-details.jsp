<%@ page import="com.stonad.model.Artwork,com.stonad.model.User" %>
<%@ page import="com.stonad.model.Comment" %>
<%@ page import="com.stonad.dao.LikeDao" %>
<%@ page import="com.stonad.dao.CommentDao" %>
<%@ page import="java.util.List" %>

<%
    Artwork a = (Artwork) request.getAttribute("artwork");

    User user = (User) session.getAttribute("user");

    LikeDao likeDao = new LikeDao();
    CommentDao commentDao = new CommentDao();

    int likeCount = likeDao.getLikeCount(a.getId());
    int commentCount = commentDao.getCommentCount(a.getId());

    boolean liked = false;

    if (user != null) {
        liked = likeDao.hasLiked(a.getId(), user.getId());
    }

    List<Comment> comments = commentDao.getComments(a.getId());
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title><%= a.getTitle() %> | STONAD</title>

    <link rel="stylesheet" href="css/style.css">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

</head>

<body>

<nav>

    <a class="logo" href="index.html">
        STONAD
    </a>

    <div>

        <a href="gallery">
            Explore
        </a>

        <% if (user != null) { %>

            <a href="logout">
                Logout
            </a>

        <% } %>

    </div>

</nav>


<main class="details">


    <!-- ================= ARTWORK ================= -->

    <div>

        <img
            src="<%= a.getImagePath() %>"
            alt="<%= a.getTitle() %>"
        >

    </div>


    <!-- ================= ARTWORK INFORMATION ================= -->

    <div>

        <p class="eyebrow">

            <%= a.getCategory() == null
                    ? "ART"
                    : a.getCategory() %>

        </p>


        <h1>

            <%= a.getTitle() %>

        </h1>


        <h3>

            Artist: <%= a.getArtistName() %>

        </h3>


        <p>

            <%= a.getDescription() == null
                    ? ""
                    : a.getDescription() %>

        </p>


        <!-- ================= LIKE & COMMENT ================= -->

        <div class="social-actions">


            <!-- LIKE -->

            <% if (user != null) { %>

                <form action="like"
                      method="post"
                      class="like-form">

                    <input
                        type="hidden"
                        name="artworkId"
                        value="<%= a.getId() %>"
                    >

                    <button
                        type="submit"
                        class="like-button <%= liked ? "liked" : "" %>"
                    >

                        <i class="<%= liked
                                ? "fa-solid"
                                : "fa-regular" %> fa-heart"></i>

                        <span class="count">
                            <%= likeCount %>
                        </span>

                        <span>
                            <%= likeCount == 1
                                    ? "Like"
                                    : "Likes" %>
                        </span>

                    </button>

                </form>

            <% } else { %>

                <div class="like-info">

                    <i class="fa-regular fa-heart"></i>

                    <span class="count">
                        <%= likeCount %>
                    </span>

                    <span>
                        <%= likeCount == 1
                                ? "Like"
                                : "Likes" %>
                    </span>

                </div>

            <% } %>


            <!-- COMMENT -->

            <a href="#comments"
               class="comment-info">

                <i class="fa-regular fa-comment"></i>

                <span class="count">
                    <%= commentCount %>
                </span>

                <span>
                    <%= commentCount == 1
                            ? "Comment"
                            : "Comments" %>
                </span>

            </a>


        </div>


        <!-- ================= COMMENT SECTION ================= -->

        <section
            class="comment-section"
            id="comments"
        >

            <h2>
                Comments
            </h2>


            <% if (user != null) { %>

                <form
                    action="comment"
                    method="post"
                    class="comment-form"
                >

                    <input
                        type="hidden"
                        name="artworkId"
                        value="<%= a.getId() %>"
                    >


                    <textarea
                        name="comment"
                        placeholder="Share your thoughts about this artwork..."
                        required
                    ></textarea>


                    <button type="submit">

                        <i class="fa-regular fa-paper-plane"></i>

                        Post Comment

                    </button>

                </form>

            <% } else { %>

                <p class="login-message">

                    Please login to like or comment on artwork.

                </p>

            <% } %>


            <!-- ================= DISPLAY COMMENTS ================= -->

            <div class="comment-list">

                <% if (comments.isEmpty()) { %>

                    <p class="no-comments">

                        No comments yet.
                        Be the first to comment!

                    </p>

                <% } else { %>

                    <% for (Comment c : comments) { %>

                        <div class="comment-card">

                            <div class="comment-header">

                                <strong>

                                    <i class="fa-regular fa-user"></i>

                                    <%= c.getUsername() %>

                                </strong>

                                <small>

                                    <%= c.getCreatedAt() %>

                                </small>

                            </div>


                            <p>

                                <%= c.getComment() %>

                            </p>

                        </div>

                    <% } %>

                <% } %>

            </div>

        </section>


        <!-- ================= DELETE ================= -->

        <% if (user != null &&
               user.getId() == a.getArtistId()) { %>

            <form
                action="delete-artwork"
                method="post"
            >

                <input
                    type="hidden"
                    name="id"
                    value="<%= a.getId() %>"
                >

                <button
                    class="danger"
                    type="submit"
                >

                    <i class="fa-regular fa-trash-can"></i>

                    Delete Artwork

                </button>

            </form>

        <% } %>


    </div>

</main>

</body>

</html>