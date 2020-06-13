package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.entity.Product;
import ua.testing.model.service.CartService;
import ua.testing.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserPage implements Command {
    @InjectByType
    private ProductService productService;

    @InjectByType
    private CartService cartService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<Product> products = productService.parseUserQuery(request);

        request.setAttribute("currentUsername", username);
        request.setAttribute("products", productService.pageProducts(request, products, 9));
        request.setAttribute("pages", productService.getTotalPageCount(products, 9));
        request.setAttribute("cart", cartService.getProductLocalizedNamesOfCurrentUser(request));
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);
    }
}
