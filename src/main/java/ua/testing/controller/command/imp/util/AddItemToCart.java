package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddItemToCart implements Command {
    @InjectByType
    private CartService cartService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        cartService.addProductToCart(request, request.getParameter("product_name"));
    }
}
