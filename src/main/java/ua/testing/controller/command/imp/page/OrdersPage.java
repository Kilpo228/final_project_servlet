package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class OrdersPage implements Command {
    @InjectByType
    private ReceiptService receiptService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("receipts", receiptService.getAllReceiptOfCurrentUser(request));
        request.setAttribute("sum", receiptService.getTotalSumOfReceipts(request));
        request.setAttribute("currentUsername", request.getSession().getAttribute("username"));
        request.getRequestDispatcher("/WEB-INF/user/orders.jsp").forward(request, response);
    }
}
