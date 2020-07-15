package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            String username = securityService.getCurrentUsername(request);
            String deletingUsername = request.getParameter("username");
            if (username.equals(deletingUsername)) {
                String error = "Cannot remove your own account";
                request.setAttribute("error", error);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/user.jsp");
                requestDispatcher.include(request, response);
                return;
            }
            userService.removeUser(deletingUsername);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public String getPattern() {
        return "/delete";
    }
}
