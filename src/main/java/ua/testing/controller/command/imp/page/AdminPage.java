package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.entity.ProductCategory;
import ua.testing.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminPage implements Command {
    @InjectByType
    private ProductService productService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.parseAdminQuery(request));
        request.setAttribute("categories", ProductCategory.values());
        request.setAttribute("ingredients", productService.findAllIngredientsWithAmountGreaterThanZero(request));
        request.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
    }
}
