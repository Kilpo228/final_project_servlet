package ua.testing.model.service;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.annotation.InjectProperty;
import ua.testing.model.annotation.Service;
import ua.testing.model.dao.CartDAO;
import ua.testing.model.dao.ProductDAO;
import ua.testing.model.dao.UserDAO;
import ua.testing.model.entity.Cart;
import ua.testing.model.entity.RoleType;
import ua.testing.model.entity.User;
import ua.testing.model.exceptions.ProductNotFoundException;
import ua.testing.model.exceptions.UserNotFoundException;
import ua.testing.model.util.CurrencyConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @InjectProperty("usd.to.uah")
    private String usdToUah;

    @InjectProperty("uah.to.usd")
    private String uahToUsd;

    @InjectByType
    private UserDAO userDAO;

    @InjectByType
    private CartDAO cartDAO;

    @InjectByType
    private ProductDAO productDAO;

    public void authenticate(HttpServletResponse response, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

            if (checkContext(request, username)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("exist");
            } else if (BCrypt.checkpw(password, user.getPassword()) && user.getRole().equals(RoleType.USER)) {
                addUserToContext(request, username, RoleType.USER);
                response.getWriter().write("user");
            } else if (BCrypt.checkpw(password, user.getPassword()) && user.getRole().equals(RoleType.ADMIN)) {
                addUserToContext(request, username, RoleType.ADMIN);
                response.getWriter().write("admin");
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (UserNotFoundException | IOException e) {
            LOGGER.error(e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void saveNewUser(String username, String password, HttpServletResponse response) {
        try {
            userDAO.save(new User(RoleType.USER, username, BCrypt.hashpw(password, BCrypt.gensalt()), 0L));
            LOGGER.info("New user was registered with username: " + username);
        } catch (SQLException e) {
            LOGGER.error(e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public String getBalance(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");

        return CurrencyConverter.centsToDollarsWithLocale(
                userDAO.findUserByUsername(username).
                        orElseThrow(() -> new UserNotFoundException(username)).
                        getBalance(),
                usdToUah,
                request
        ).toString();
    }

    public String checkPassword(HttpServletRequest request, String password) {
        return BCrypt.checkpw(password, userDAO.
                findUserByUsername((String) request.getSession().getAttribute("username")).
                orElse(new User()).
                getPassword()) ? "1" : "0";
    }

    public void changePassword(HttpServletRequest request, String newPassword) {
        userDAO.updatePassword(
                BCrypt.hashpw(newPassword, BCrypt.gensalt()),
                (String) request.getSession().getAttribute("username")
        );
    }

    public void changeUsername(HttpServletRequest request, String newUsername) {
        userDAO.updateUsername(newUsername, (String) request.getSession().getAttribute("username"));
    }

    public void replenishBalance(HttpServletRequest request, String amount) {
        userDAO.addCentsToBalance(
                CurrencyConverter.dollarsToCentsWithLocale(amount, uahToUsd, request),
                (String) request.getSession().getAttribute("username")
        );
    }

    public String fulfillCartOfCurrentUser(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        List<Cart> items = cartDAO.findAllItemsByUsername(username);
        long sum = items.stream().
                mapToLong(item -> item.getPrice() * item.getAmount()).
                sum();
        int affectedRows = 0;

        if (!checkAvailabilityOfProductsInCart(items)) {
            return "products";
        }

        try {
            userDAO.getConnection().setAutoCommit(false);

            if (userDAO.deductBalance(sum, username) > 0) {
                affectedRows = userDAO.addCentsToBalance(sum, "admin");
            }

            userDAO.getConnection().commit();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return String.valueOf(affectedRows);
    }

    private boolean checkAvailabilityOfProductsInCart(List<Cart> list) {
        return list.stream().
                allMatch(item -> item.getAmount() <= productDAO.findProductByEnName(item.getEnProductName()).
                        orElseThrow(() -> new ProductNotFoundException(item.getEnProductName())).
                        getAmount());
    }

    @SuppressWarnings("unchecked")
    private boolean checkContext(HttpServletRequest request, String username) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
        return loggedUsers.stream().anyMatch(username::equals);
    }

    @SuppressWarnings("unchecked")
    private void addUserToContext(HttpServletRequest request, String username, RoleType role) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
        HttpSession session = request.getSession();
        loggedUsers.add(username);
        session.setAttribute("username", username);
        session.setAttribute("role", role);
    }
}
