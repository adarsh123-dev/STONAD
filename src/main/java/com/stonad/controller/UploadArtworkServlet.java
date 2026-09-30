package com.stonad.controller;

import com.stonad.dao.ArtworkDao;
import com.stonad.model.Artwork;
import com.stonad.model.User;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.file.*;

@WebServlet("/upload-artwork")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class UploadArtworkServlet extends HttpServlet {
    private final ArtworkDao dao = new ArtworkDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");

        if (user == null || !"ARTIST".equals(user.getRole())) {
            resp.sendError(403, "Only artists can upload artwork.");
            return;
        }

        try {
            Part image = req.getPart("image");
            if (image == null || image.getSize() == 0) {
                resp.sendError(400, "Please select an image.");
                return;
            }

            String original = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            String safeName = System.currentTimeMillis() + "_" + original.replaceAll("[^a-zA-Z0-9._-]", "_");

            Path uploadDir = Paths.get(getServletContext().getRealPath("/uploads"));
            Files.createDirectories(uploadDir);
            image.write(uploadDir.resolve(safeName).toString());

            Artwork artwork = new Artwork();
            artwork.setArtistId(user.getId());
            artwork.setTitle(req.getParameter("title"));
            artwork.setDescription(req.getParameter("description"));
            artwork.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
            artwork.setImagePath("uploads/" + safeName);

            dao.create(artwork);
            resp.sendRedirect("gallery");
        } catch (Exception e) {
            resp.sendError(500, "Artwork upload failed: " + e.getMessage());
        }
    }
}
