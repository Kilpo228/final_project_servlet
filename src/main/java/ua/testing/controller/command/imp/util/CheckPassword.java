package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CheckPassword implements Command {
    @InjectByType
    private UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(userService.checkPassword(request, request.getParameter("current_password")));
    }
}
