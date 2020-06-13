package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.entity.RoleType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

@Controller
public class Logout implements Command {
    @Override
    @SuppressWarnings("unchecked")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        loggedUsers.remove(username);
        session.setAttribute("role", RoleType.ANON);
        session.setAttribute("username", "");
        response.addCookie(new Cookie("status", "logout"));
        response.sendRedirect("/app");
    }
}
