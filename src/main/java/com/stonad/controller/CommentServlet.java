package com.stonad.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.stonad.dao.CommentDao;
import com.stonad.model.User;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

    private final CommentDao commentDao = new CommentDao();

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

            // Get comment text
            String comment = request.getParameter("comment");

            // Validate comment
            if (comment != null && !comment.trim().isEmpty()) {

                // Get user ID from User object
                int userId = user.getId();

                // Save comment
                commentDao.addComment(
                        artworkId,
                        userId,
                        comment.trim()
                );
            }

            // Go back to artwork details page
            response.sendRedirect(
                    "artwork?id=" + artworkId
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Unable to post comment"
            );
        }
    }
}