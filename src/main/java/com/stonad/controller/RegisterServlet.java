package com.stonad.controller;

import com.stonad.dao.UserDao;
import com.stonad.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserDao dao = new UserDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (role == null || (!role.equals("ARTIST") && !role.equals("VISITOR"))) {
            role = "VISITOR";
        }

        try {
            dao.register(new User(0, name, email, password, role));
            resp.sendRedirect("login.html?registered=true");
        } catch (Exception e) {
            resp.sendError(500, "Registration failed: " + e.getMessage());
        }
    }
}
