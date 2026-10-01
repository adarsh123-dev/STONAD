package com.stonad.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.stonad.dao.LikeDao;
import com.stonad.model.User;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final LikeDao likeDao = new LikeDao();

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // Get existing session
        HttpSession session = request.getSession(false);

        // Get logged-in User object
        User user = session == null
                ? null
                : (User) session.getAttribute("user");

        // User is not logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {

            // Get artwork ID
            int artworkId = Integer.parseInt(
                    request.getParameter("artworkId")
            );

            // Get user ID from User object
            int userId = user.getId();

            // Check whether user already liked artwork
            boolean alreadyLiked =
                    likeDao.hasLiked(artworkId, userId);

            if (alreadyLiked) {

                // Unlike
                likeDao.removeLike(artworkId, userId);

            } else {

                // Like
                likeDao.addLike(artworkId, userId);
            }

            // Return to artwork details page
            response.sendRedirect(
                    "artwork?id=" + artworkId
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Unable to process like"
            );
        }
    }
}