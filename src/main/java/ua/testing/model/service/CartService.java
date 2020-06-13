package ua.testing.model.service;

import ua.testing.model.annotation.InjectByType;
import ua.testing.model.annotation.InjectProperty;
import ua.testing.model.annotation.Service;
import ua.testing.model.dao.CartDAO;
import ua.testing.model.dao.ProductDAO;
import ua.testing.model.entity.Cart;
import ua.testing.model.entity.Product;
import ua.testing.model.entity.dto.CartDTO;
import ua.testing.model.exceptions.CartNotFoundException;
import ua.testing.model.exceptions.ProductNotFoundException;
import ua.testing.model.util.CurrencyConverter;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CartService {
    @InjectByType
    private ProductDAO productDAO;

    @InjectByType
    private CartDAO cartDAO;

    @InjectProperty("usd.to.uah")
    private String usdToUah;

    @InjectProperty("uah.to.usd")
    private String uahToUsd;

    public void addProductToCart(HttpServletRequest request, String productName) {
        String username = (String) request.getSession().getAttribute("username");

        Product product = request.getSession().getAttribute("locale").equals(
                new Locale("en", "US")) ?
                productDAO.findProductByEnName(productName).orElseThrow(() ->
                        new ProductNotFoundException(productName)) :
                productDAO.findProductByRuName(productName).orElseThrow(() ->
                        new ProductNotFoundException(productName));

        cartDAO.save(new Cart(product.getEnName(), product.getRuName(), 1, product.getPrice(), username));
    }

    public List<CartDTO> getProductsOfCurrentUser(HttpServletRequest request) {
        return cartDAO.findAllItemsByUsername((String) request.getSession().getAttribute("username")).stream().
                map(cart -> new CartDTO(request.getSession().getAttribute("locale").
                        equals(new Locale("en", "US")) ?
                        cart.getEnProductName() : cart.getRuProductName(),
                        cart.getAmount(),
                        CurrencyConverter.centsToDollarsWithLocale(cart.getPrice(), usdToUah, request).toString())
                ).collect(Collectors.toList());
    }

    public String getTotalPriceOfCart(HttpServletRequest request, List<CartDTO> products) {
        return CurrencyConverter.centsToDollarsWithLocale(
                products.stream().
                        map(product ->
                                CurrencyConverter.dollarsToCentsWithLocale(new BigDecimal(product.getPrice()).
                                        multiply(new BigDecimal(product.getAmount())).toString(), uahToUsd, request)).
                        mapToLong(Long::longValue).
                        sum(),
                usdToUah,
                request
        ).toString();
    }

    public List<String> getProductLocalizedNamesOfCurrentUser(HttpServletRequest request) {
        return cartDAO.findAllItemsByUsername((String) request.getSession().getAttribute("username")).stream().
                map(request.getSession().getAttribute("locale").equals(new Locale("en", "US")) ?
                        Cart::getEnProductName : Cart::getRuProductName).collect(Collectors.toList());
    }

    public void clearCart(HttpServletRequest request) {
        cartDAO.deleteByUsername((String) request.getSession().getAttribute("username"));
    }

    public void increaseAmountOfItem(HttpServletRequest request, String productName) {
        cartDAO.increaseItemAmount(productName, (String) request.getSession().getAttribute("username"));
    }

    public void decreaseAmountOfItem(HttpServletRequest request, String productName) {
        cartDAO.decreaseItemAmount(productName, (String) request.getSession().getAttribute("username"));
    }

    public String getAmountOfItem(HttpServletRequest request, String productName) {
        return String.valueOf(cartDAO.findAllItemsByUsername((String) request.getSession().getAttribute("username")).
                stream().
                filter(item ->
                        item.getEnProductName().equals(productName) || item.getRuProductName().equals(productName)).
                findFirst().
                orElseThrow(() ->
                        new CartNotFoundException(productName)).
                getAmount());
    }
}
