<%@ page import="java.util.List" %>
<%@ page import="com.stonad.model.Artwork" %>

<%
    List<Artwork> artworks =
        (List<Artwork>) request.getAttribute("artworks");
%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>STONAD | Art Showcase</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav>

    <a class="logo" href="home">
        STONAD
    </a>

    <div>

        <a href="gallery">
            Explore
        </a>

        <a class="btn" href="upload.html">
            Upload
        </a>

        <a href="logout">
            Logout
        </a>

    </div>

</nav>


<!-- ================= HERO ================= -->

<section class="hero">

    <div>

        <p class="eyebrow">
            ART • PEOPLE • DISCOVERY
        </p>

        <h1>
            Show your art.<br>
            Discover inspiration.
        </h1>

        <p>
            Discover unique artwork from different artists
            and showcase your own creativity with STONAD.
        </p>

        <div class="actions">

            <a class="btn" href="gallery">
                Explore Artwork
            </a>

            <a class="btn secondary" href="upload.html">
                Showcase Your Art
            </a>

        </div>

    </div>

</section>


<!-- ================= LATEST ARTWORK ================= -->

<section class="latest-art">

    <div class="section-heading">

        <div>

            <p class="eyebrow">
                LATEST COLLECTION
            </p>

            <h2>
                Discover artwork
            </h2>

        </div>

        <a href="gallery">
            View all →
        </a>

    </div>


    <div class="grid">

        <%
            int count = 0;

            for (Artwork artwork : artworks) {

                if (count >= 6) {
                    break;
                }

                count++;
        %>

        <article class="art-card">

            <a href="artwork?id=<%= artwork.getId() %>">

                <img
                    src="<%= artwork.getImagePath() %>"
                    alt="<%= artwork.getTitle() %>"
                >

            </a>


            <div class="art-info">

                <span>
                    <%= artwork.getCategory() == null
                        ? "ART"
                        : artwork.getCategory() %>
                </span>


                <h2>
                    <%= artwork.getTitle() %>
                </h2>


                <p>
                    by <%= artwork.getArtistName() %>
                </p>


                <a href="artwork?id=<%= artwork.getId() %>">
                    View artwork →
                </a>

            </div>

        </article>

        <%
            }
        %>

    </div>

</section>


<!-- ================= HOW IT WORKS ================= -->

<section class="how-section">

    <div class="section-heading">

        <div>

            <p class="eyebrow">
                HOW STONAD WORKS
            </p>

            <h2>
                Create. Showcase. Discover.
            </h2>

        </div>

    </div>


    <div class="features">

        <div>

            <h3>
                01 — Create
            </h3>

            <p>
                Create your artist account and
                build your creative presence.
            </p>

        </div>


        <div>

            <h3>
                02 — Showcase
            </h3>

            <p>
                Upload your artwork and share
                your creativity with others.
            </p>

        </div>


        <div>

            <h3>
                03 — Discover
            </h3>

            <p>
                Explore artwork from different
                artists and discover new ideas.
            </p>

        </div>

    </div>

</section>


<!-- ================= FOOTER ================= -->

<footer>

    <div>

        <strong>
            STONAD
        </strong>

        <p>
            A place to showcase and discover art.
        </p>

    </div>

    <div>

        <a href="gallery">
            Explore
        </a>

        <a href="upload.html">
            Showcase Art
        </a>

    </div>

</footer>


</body>

</html>