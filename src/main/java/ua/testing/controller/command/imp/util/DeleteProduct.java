package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DeleteProduct implements Command {
    @InjectByType
    private ProductDAO productDAO;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productDAO.deleteByName(request.getParameter("product_name"));
    }
}
