package ua.testing.controller;

import ua.testing.controller.command.Command;
import ua.testing.controller.command.imp.page.*;
import ua.testing.controller.command.imp.util.*;
import ua.testing.model.application.Application;
import ua.testing.model.application.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commandMap = new HashMap<>();
    private ApplicationContext context;

    @Override
    public void init() {
        context = Application.run("ua.testing");

        commandMap.put("add_admin", context.getObject(SaveNewAdmin.class));
        commandMap.put("fill_tables", context.getObject(FillTables.class));
        commandMap.put("login", context.getObject(Login.class));
        commandMap.put("logout", context.getObject(Logout.class));
        commandMap.put("", context.getObject(LoginPage.class));
        commandMap.put("register", context.getObject(RegistrationPage.class));
        commandMap.put("save_new_user", context.getObject(SaveNewUser.class));
        commandMap.put("user", context.getObject(UserPage.class));
        commandMap.put("admin", context.getObject(AdminPage.class));
        commandMap.put("rescue_user", context.getObject(RescueUser.class));
        commandMap.put("user/balance", context.getObject(ReadBalance.class));
        commandMap.put("user/cart", context.getObject(CartPage.class));
        commandMap.put("user/orders", context.getObject(OrdersPage.class));
        commandMap.put("user/details", context.getObject(ProductDetails.class));
        commandMap.put("user/check_password", context.getObject(CheckPassword.class));
        commandMap.put("user/change_password", context.getObject(ChangePassword.class));
        commandMap.put("user/change_username", context.getObject(ChangeUsername.class));
        commandMap.put("user/replenish_balance", context.getObject(ReplenishBalance.class));
        commandMap.put("user/add_to_cart", context.getObject(AddItemToCart.class));
        commandMap.put("user/remove_from_cart", context.getObject(RemoveItemFromCart.class));
        commandMap.put("user/increase_item_amount", context.getObject(IncreaseItemAmount.class));
        commandMap.put("user/decrease_item_amount", context.getObject(DecreaseItemAmount.class));
        commandMap.put("user/cart_sum", context.getObject(TotalCartSum.class));
        commandMap.put("user/fulfill", context.getObject(FulfillCart.class));
        commandMap.put("admin/product_price", context.getObject(ProductPrice.class));
        commandMap.put("admin/product_amount", context.getObject(ProductAmount.class));
        commandMap.put("admin/update_product", context.getObject(UpdateProduct.class));
        commandMap.put("admin/delete_product", context.getObject(DeleteProduct.class));
        commandMap.put("admin/purchases", context.getObject(PurchasesPage.class));
        commandMap.put("admin/ingredients", context.getObject(IngredientsPage.class));
        commandMap.put("admin/ingredient_increase", context.getObject(IncreaseIngredientAmount.class));
        commandMap.put("admin/ingredient_decrease", context.getObject(DecreaseIngredientAmount.class));
        commandMap.put("admin/ingredient_amount", context.getObject(AmountOfIngredient.class));
        commandMap.put("admin/save_new_ingredient", context.getObject(SaveNewIngredient.class));
        commandMap.put("admin/delete_ingredient", context.getObject(DeleteIngredient.class));
        commandMap.put("admin/save_new_product", context.getObject(SaveNewProduct.class));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commandMap.getOrDefault(
                request.getRequestURI().replaceAll(".*/app/", ""),
                context.getObject(NotFoundPage.class)).
                execute(request, response);
    }
}
