package io.muzoo.ooc.webapp.basic.security;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class SecurityService {

    private MySql mySql;
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public String getCurrentUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object usernameObject = session.getAttribute("username");
        return (String) usernameObject;
    }

    public boolean isAuthorized(HttpServletRequest request) {
        String username = getCurrentUsername(request);
        return userService.checkIfUserExists(username);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
    }

    public boolean login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUser(username);
        if (user != null && BCrypt.checkpw(password, userService.getUser(username).getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        } else {
            return false;
        }
//        if (user != null && Objects.equals(user.getPassword(), password)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            return true;
//        } else {
//            return false;
//        }
    }
}
