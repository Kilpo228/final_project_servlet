package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class NotFoundPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
    }
}
