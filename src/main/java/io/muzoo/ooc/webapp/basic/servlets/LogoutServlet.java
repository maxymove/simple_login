package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.AbstractRoutableHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        securityService.logout(request);
        response.sendRedirect("/");
    }

    @Override
    public String getPattern() {
        return "/logout";
    }
}
