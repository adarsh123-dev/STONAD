package com.stonad.controller;

import com.stonad.dao.ArtworkDao;
import com.stonad.model.Artwork;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/artwork")
public class ArtworkDetailsServlet extends HttpServlet {
    private final ArtworkDao dao = new ArtworkDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Artwork artwork = dao.findById(id);
            if (artwork == null) {
                resp.sendError(404, "Artwork not found");
                return;
            }
            req.setAttribute("artwork", artwork);
            req.getRequestDispatcher("/artwork-details.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(500, "Could not load artwork: " + e.getMessage());
        }
    }
}
