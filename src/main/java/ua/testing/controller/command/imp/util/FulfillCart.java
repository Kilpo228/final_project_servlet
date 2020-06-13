package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.CartService;
import ua.testing.model.service.ProductService;
import ua.testing.model.service.ReceiptService;
import ua.testing.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FulfillCart implements Command {
    @InjectByType
    private UserService userService;

    @InjectByType
    private ReceiptService receiptService;

    @InjectByType
    private ProductService productService;

    @InjectByType
    private CartService cartService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String status = userService.fulfillCartOfCurrentUser(request);

        if (!status.equals("products")) {
            receiptService.saveNewReceipt(request);
            productService.decreaseAmountOfProductsByCartItems(request);
            cartService.clearCart(request);
        } else {
            response.getWriter().write(status);
        }
    }
}
