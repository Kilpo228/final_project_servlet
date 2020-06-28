package ua.testing.model.service;

import ua.testing.model.annotation.InjectByType;
import ua.testing.model.annotation.InjectProperty;
import ua.testing.model.annotation.Service;
import ua.testing.model.dao.CartDAO;
import ua.testing.model.dao.IngredientDAO;
import ua.testing.model.dao.ProductDAO;
import ua.testing.model.entity.Product;
import ua.testing.model.entity.ProductCategory;
import ua.testing.model.entity.dto.AdminProductDTO;
import ua.testing.model.entity.dto.IngredientDTO;
import ua.testing.model.entity.dto.UserProductDTO;
import ua.testing.model.exceptions.IngredientNotFoundException;
import ua.testing.model.exceptions.ProductNotFoundException;
import ua.testing.model.util.CurrencyConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @InjectByType
    private ProductDAO productDAO;

    @InjectByType
    private CartDAO cartDAO;

    @InjectByType
    private IngredientDAO ingredientDAO;

    @InjectProperty("usd.to.uah")
    private String usdToUah;

    @InjectProperty("uah.to.usd")
    private String uahToUsd;

    public String getProductIngredients(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String productName = request.getParameter("product_name");
        Product product;

        if (locale.equals(new Locale("en", "US"))) {
            product = productDAO.findProductByEnName(productName.trim()).
                    orElseThrow(() -> new ProductNotFoundException(productName));
        } else {
            product = productDAO.findProductByRuName(productName.trim()).
                    orElseThrow(() -> new ProductNotFoundException(productName));
        }

        return product.getIngredients().stream().
                map(ingredient -> locale.equals(new Locale("en", "US")) ?
                        ingredient.getEnName() + " "
                        :
                        ingredient.getRuName() + " "
                ).collect(Collectors.joining());
    }

    public List<Product> parseUserQuery(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        List<Product> products = productDAO.findAllAvailableProducts();
        List<Product> result = new ArrayList<>(products);

        Optional.ofNullable(request.getParameter("q")).ifPresent(q -> products.stream().
                filter(product -> !(product.getEnName().equalsIgnoreCase(q) ||
                        product.getEnName().toLowerCase().contains(q.toLowerCase()))
                ).
                filter(product -> !(product.getRuName().equalsIgnoreCase(q) ||
                        product.getRuName().toLowerCase().contains(q.toLowerCase()))
                ).
                forEach(result::remove)
        );

        Optional.ofNullable(request.getParameter("min")).ifPresent(min -> products.stream().
                filter(product -> product.getPrice() < CurrencyConverter.dollarsToCents(min)).
                forEach(result::remove)
        );

        Optional.ofNullable(request.getParameter("max")).ifPresent(max -> products.stream().
                filter(product -> product.getPrice() > CurrencyConverter.dollarsToCents(max)).
                forEach(result::remove)
        );

        Optional.ofNullable(request.getParameterValues("category")).ifPresent(category -> {
                    List<Product> sorted = new ArrayList<>(products);

                    Arrays.stream(category).
                            forEach(value ->
                                    products.stream().
                                            filter(product -> product.getCategory().name().equalsIgnoreCase(value)).
                                            forEach(sorted::remove)
                            );

                    sorted.forEach(result::remove);
                }
        );

        Optional.ofNullable(request.getParameter("sort")).ifPresent(sort -> {
            if (sort.equals("price_smaller")) {
                result.sort(Comparator.comparing(Product::getPrice));
            } else if (sort.equals("price_bigger")) {
                result.sort(Comparator.comparing(Product::getPrice).reversed());
            } else if (sort.equals("name") && locale.equals(new Locale("en", "US"))) {
                result.sort(Comparator.comparing(Product::getEnName));
            } else {
                result.sort(Comparator.comparing(Product::getRuName));
            }
        });

        return result;
    }

    public List<AdminProductDTO> parseAdminQuery(HttpServletRequest request) {
        List<AdminProductDTO> products = mapProductToAdminProductDTO(productDAO.findAll(), request);

        Optional.ofNullable(request.getParameter("sort")).ifPresent(sort -> {
            switch (sort) {
                case "en":
                    products.clear();
                    products.addAll(mapProductToAdminProductDTO(productDAO.findAllOrderByEnName(), request));
                    break;
                case "ru":
                    products.clear();
                    products.addAll(mapProductToAdminProductDTO(productDAO.findAllOrderByRuName(), request));
                    break;
                case "amount":
                    products.clear();
                    products.addAll(mapProductToAdminProductDTO(productDAO.findAllOrderByAmount(), request));
                    break;
                case "category":
                    products.clear();
                    products.addAll(mapProductToAdminProductDTO(productDAO.findAllOrderByCategory(), request));
                case "price":
                    products.clear();
                    products.addAll(mapProductToAdminProductDTO(productDAO.findAllOrderByPrice(), request));
                    break;
            }
        });

        return products;
    }

    public List<UserProductDTO> pageProducts(HttpServletRequest request, List<Product> products, int elementsPerOnePage) {
        return products.stream().
                map(product -> new UserProductDTO(
                        product.getCategory(),
                        request.
                                getSession().
                                getAttribute("locale").
                                equals(new Locale("en", "US")) ?
                                product.getEnName() : product.getRuName(),
                        CurrencyConverter.centsToDollarsWithLocale(
                                product.getPrice(),
                                usdToUah,
                                request
                        ).toString(),
                        new ArrayList<>(product.getIngredients())
                )).
                skip((Integer.valueOf(Optional.ofNullable(request.getParameter("page")).
                        orElse("1")) - 1) * elementsPerOnePage).
                limit(elementsPerOnePage).
                collect(Collectors.toList());
    }

    public int getTotalPageCount(List<Product> products, int elementsPerOnePage) {
        return products.size() >= elementsPerOnePage ? products.size() / elementsPerOnePage + 1 : 1;
    }

    public void decreaseAmountOfProductsByCartItems(HttpServletRequest request) {
        cartDAO.findAllItemsByUsername((String) request.getSession().getAttribute("username")).
                forEach(item -> productDAO.decreaseAmount(item.getEnProductName(), item.getAmount()));
    }

    public String getProductPrice(HttpServletRequest request, String productName) {
        return CurrencyConverter.centsToDollarsWithLocale(
                productDAO.findProductByName(productName).orElseThrow(() ->
                        new ProductNotFoundException(productName)).getPrice(),
                usdToUah,
                request).toString();
    }

    public String getProductAmount(String productName) {
        return String.valueOf(productDAO.findProductByName(productName).orElseThrow(() ->
                new ProductNotFoundException(productName)).getAmount());
    }

    public void updateAmountAndPrice(HttpServletRequest request, String price, String amount, String productName) {
        productDAO.updateAmountAndPrice(
                CurrencyConverter.dollarsToCentsWithLocale(
                        price,
                        uahToUsd,
                        request),
                Integer.parseInt(amount),
                productName);
    }

    public String saveNewProduct(
            String enName,
            String ruName,
            String category,
            String price,
            String[] ingredients,
            HttpServletRequest request) {
        boolean ingredientUnavailable = Arrays.stream(Optional.ofNullable(ingredients).orElse(new String[0])).
                map(ingredient ->
                        ingredientDAO.findByName(ingredient).orElseThrow(() ->
                                new IngredientNotFoundException(ingredient))).
                anyMatch(ingredient -> ingredient.getAmount() < 1);

        if (ingredientUnavailable) {
            return "ingredient";
        }

        if (enName.matches("^[a-zA-Z]+$") &&
                ruName.matches("^[а-яА-Я]+$") &&
                price.matches("[0-9]+")) {
            productDAO.save(new Product(
                    ProductCategory.valueOf(category),
                    enName,
                    ruName,
                    100,
                    CurrencyConverter.dollarsToCentsWithLocale(price, uahToUsd, request),
                    Arrays.stream(Optional.ofNullable(ingredients).orElse(new String[0])).
                            map(ingredient ->
                                    ingredientDAO.findByName(ingredient).orElseThrow(() ->
                                            new IngredientNotFoundException(ingredient))).
                            collect(Collectors.toList())
            ));

            Arrays.stream(Optional.ofNullable(ingredients).orElse(new String[0])).
                    forEach(ingredient -> ingredientDAO.decreaseAmountByName(ingredient));

            return "ok";
        } else {
            return "name";
        }
    }

    public List<IngredientDTO> findAllIngredientsWithAmountGreaterThanZero(HttpServletRequest request) {
        return ingredientDAO.findAllWithAmountGreaterThanZero().stream().
                map(ingredient ->
                        new IngredientDTO(
                                request.
                                        getSession().
                                        getAttribute("locale").
                                        equals(new Locale("en", "US")) ?
                                        ingredient.getEnName() : ingredient.getRuName()
                        )
                ).collect(Collectors.toList());
    }

    private List<AdminProductDTO> mapProductToAdminProductDTO(List<Product> products, HttpServletRequest request) {
        return products.stream().map(product -> new AdminProductDTO(
                product.getEnName(),
                product.getRuName(),
                CurrencyConverter.centsToDollarsWithLocale(product.getPrice(), usdToUah, request).toString(),
                product.getCategory(),
                product.getAmount()
        )).collect(Collectors.toList());
    }
}
