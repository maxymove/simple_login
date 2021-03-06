package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.AbstractRoutableHttpServlet;
import io.muzoo.ooc.webapp.basic.security.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            String username = securityService.getCurrentUsername(request);
            request.setAttribute("currentUser", userService.getUser(username));
            // List of users
            List<User> users = securityService.getUserService().getUsers();
            request.setAttribute("users", users);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/user.jsp");
            requestDispatcher.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public String getPattern() {
        return "/user";
    }
}
