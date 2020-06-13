package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.entity.RoleType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RescueUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoleType role = (RoleType) request.getSession().getAttribute("role");

        if (role.equals(RoleType.ANON)) {
            response.getWriter().write("/app");
        } else if (role.equals(RoleType.USER)) {
            response.getWriter().write("/app/user");
        } else {
            response.getWriter().write("/app/admin");
        }
    }
}
