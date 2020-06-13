package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.entity.dto.CartDTO;
import ua.testing.model.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CartPage implements Command {
    @InjectByType
    private CartService cartService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartDTO> products = cartService.getProductsOfCurrentUser(request);

        request.setAttribute("cart", products);
        request.setAttribute("totalSum", cartService.getTotalPriceOfCart(request, products));
        request.setAttribute("currentUsername", request.getSession().getAttribute("username"));
        request.getRequestDispatcher("/WEB-INF/user/cart.jsp").forward(request, response);
    }
}
