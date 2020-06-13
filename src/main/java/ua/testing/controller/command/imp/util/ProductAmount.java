package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductAmount implements Command {
    @InjectByType
    private ProductService productService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(productService.getProductAmount(request.getParameter("product_name")));
    }
}
