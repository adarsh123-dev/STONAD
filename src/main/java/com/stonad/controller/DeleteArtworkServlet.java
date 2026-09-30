package com.stonad.controller;

import com.stonad.dao.ArtworkDao;
import com.stonad.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/delete-artwork")
public class DeleteArtworkServlet extends HttpServlet {
    private final ArtworkDao dao = new ArtworkDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");

        if (user == null) {
            resp.sendError(401, "Please login.");
            return;
        }

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id, user.getId());
            resp.sendRedirect("gallery");
        } catch (Exception e) {
            resp.sendError(500, "Delete failed: " + e.getMessage());
        }
    }
}
