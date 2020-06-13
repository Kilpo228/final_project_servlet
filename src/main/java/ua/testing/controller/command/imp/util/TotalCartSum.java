package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TotalCartSum implements Command {
    @InjectByType
    private CartService cartService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.
                getWriter().
                write(cartService.getTotalPriceOfCart(
                        request,
                        cartService.getProductsOfCurrentUser(request))
                );
    }
}
