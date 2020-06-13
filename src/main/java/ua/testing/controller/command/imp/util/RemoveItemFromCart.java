package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.CartDAO;
import ua.testing.model.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RemoveItemFromCart implements Command {
    @InjectByType
    private CartDAO cartDAO;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        cartDAO.deleteItem(new Cart(
                request.getParameter("product_name"),
                request.getParameter("product_name"),
                (String) request.getSession().getAttribute("username"))
        );
    }
}
