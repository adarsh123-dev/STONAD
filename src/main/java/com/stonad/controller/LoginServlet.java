package com.stonad.controller;

import com.stonad.dao.UserDao;
import com.stonad.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDao dao = new UserDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            User user = dao.login(req.getParameter("email"), req.getParameter("password"));

            if (user == null) {
                resp.sendRedirect("login.html?error=invalid");
                return;
            }

            req.getSession().setAttribute("user", user);
            resp.sendRedirect("gallery");
        } catch (Exception e) {
            resp.sendError(500, "Login failed: " + e.getMessage());
        }
    }
}
