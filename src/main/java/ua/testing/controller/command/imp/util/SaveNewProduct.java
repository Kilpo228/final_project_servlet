package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SaveNewProduct implements Command {
    @InjectByType
    private ProductService productService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(productService.saveNewProduct(
                request.getParameter("en_name"),
                request.getParameter("ru_name"),
                request.getParameter("category"),
                request.getParameter("price"),
                request.getParameterValues("ingredients[]"),
                request));
    }
}
