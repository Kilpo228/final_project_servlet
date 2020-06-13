package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.entity.dto.AdminReceiptDTO;
import ua.testing.model.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class PurchasesPage implements Command {
    @InjectByType
    private ReceiptService receiptService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AdminReceiptDTO> receiptDTOS = receiptService.getAllAdminReceipts(request);

        request.setAttribute("receipts", receiptDTOS);
        request.setAttribute("sum", receiptService.getTotalPriceOfAdminReceipts(request, receiptDTOS));
        request.getRequestDispatcher("/WEB-INF/admin/purchases.jsp").forward(request, response);
    }
}
