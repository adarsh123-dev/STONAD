package com.stonad.controller;

import com.stonad.dao.ArtworkDao;
import com.stonad.dao.CategoryDao;
import com.stonad.model.Artwork;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {
    private final ArtworkDao artworkDao = new ArtworkDao();
    private final CategoryDao categoryDao = new CategoryDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String search = req.getParameter("search");
            String category = req.getParameter("category");
            Integer categoryId = (category == null || category.isEmpty()) ? null : Integer.valueOf(category);

            List<Artwork> artworks = artworkDao.findAll(search, categoryId);
            Map<Integer, String> categories = categoryDao.getAll();

            req.setAttribute("artworks", artworks);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/gallery.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(500, "Could not load gallery: " + e.getMessage());
        }
    }
}
