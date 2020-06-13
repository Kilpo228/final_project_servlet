package ua.testing.model.service;

import ua.testing.model.annotation.InjectByType;
import ua.testing.model.annotation.InjectProperty;
import ua.testing.model.annotation.Service;
import ua.testing.model.dao.CartDAO;
import ua.testing.model.dao.ReceiptDAO;
import ua.testing.model.entity.Receipt;
import ua.testing.model.entity.dto.AdminReceiptDTO;
import ua.testing.model.entity.dto.UserReceiptDTO;
import ua.testing.model.util.CurrencyConverter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ReceiptService {
    @InjectByType
    private ReceiptDAO receiptDAO;

    @InjectByType
    private CartDAO cartDAO;

    @InjectProperty("usd.to.uah")
    private String usdToUah;

    @InjectProperty("uah.to.usd")
    private String uahToUsd;

    public List<UserReceiptDTO> getAllReceiptOfCurrentUser(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");

        return receiptDAO.findAllReceiptByUsername((String) request.getSession().getAttribute("username")).stream().
                map(receipt -> new UserReceiptDTO(
                        locale.
                                equals(new Locale("en", "US")) ?
                                receipt.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) :
                                receipt.getDateTime().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")),
                        locale.
                                equals(new Locale("en", "US")) ?
                                receipt.getEnProductName() :
                                receipt.getRuProductName(),
                        CurrencyConverter.centsToDollarsWithLocale(receipt.getPrice(), usdToUah, request).toString(),
                        receipt.getAmount()
                )).collect(Collectors.toList());
    }

    public void saveNewReceipt(HttpServletRequest request) {
        cartDAO.findAllItemsByUsername((String) request.getSession().getAttribute("username")).
                forEach(item -> receiptDAO.save(new Receipt(
                        LocalDateTime.now(),
                        item.getEnProductName(),
                        item.getRuProductName(),
                        item.getPrice(),
                        item.getAmount(),
                        item.getUsername()))
                );
    }

    public String getTotalSumOfReceipts(HttpServletRequest request) {
        return CurrencyConverter.centsToDollarsWithLocale(
                receiptDAO.findAllReceiptByUsername((String) request.getSession().getAttribute("username")).stream().
                        mapToLong(receipt -> receipt.getPrice() * receipt.getAmount()).sum(),
                usdToUah,
                request).toString();
    }

    public List<AdminReceiptDTO> getAllAdminReceipts(HttpServletRequest request) {
        return receiptDAO.findAll().stream().map(receipt -> new AdminReceiptDTO(
                request.getSession().getAttribute("locale").equals(new Locale("en", "US")) ?
                        receipt.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) :
                        receipt.getDateTime().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")),
                request.getSession().getAttribute("locale").equals(new Locale("en", "US")) ?
                        receipt.getEnProductName() :
                        receipt.getRuProductName(),
                CurrencyConverter.centsToDollarsWithLocale(receipt.getPrice(), usdToUah, request).toString(),
                receipt.getAmount(),
                receipt.getUsername()
        )).collect(Collectors.toList());
    }

    public String getTotalPriceOfAdminReceipts(HttpServletRequest request, List<AdminReceiptDTO> receipts) {
        return CurrencyConverter.centsToDollarsWithLocale(
                receipts.stream().
                        mapToLong(receipt ->
                                CurrencyConverter.dollarsToCentsWithLocale(
                                        receipt.getPrice(),
                                        uahToUsd,
                                        request) * receipt.getAmount()).
                        sum(),
                usdToUah,
                request).toString();
    }
}
