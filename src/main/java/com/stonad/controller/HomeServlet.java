package com.stonad.controller;

import com.stonad.dao.ArtworkDao;
import com.stonad.model.Artwork;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final ArtworkDao artworkDao = new ArtworkDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            // Get all artworks from database
            List<Artwork> artworks = artworkDao.findAll(null, null);

            // Send artworks to JSP
            req.setAttribute("artworks", artworks);

            // Open home.jsp
            req.getRequestDispatcher("/home.jsp")
               .forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Unable to load STONAD home page"
            );
        }
    }
}