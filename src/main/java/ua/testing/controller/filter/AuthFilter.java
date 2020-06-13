package ua.testing.controller.filter;

import ua.testing.model.entity.RoleType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().replaceAll(".*/app/", "");
        RoleType role = (RoleType) request.getSession().getAttribute("role");

        role = Optional.ofNullable(role).orElseGet(() -> {
            request.getSession().setAttribute("role", RoleType.ANON);
            return RoleType.ANON;
        });

        if ((path.equals("") || path.equals("login") || path.equals("register") || path.equals("save_new_user"))
                && !role.equals(RoleType.ANON)) {

            redirectTo(request, response, role);

        } else if ((path.equals("user") || path.equals("user/orders") || path.equals("user/cart") ||
                path.equals("user/balance") || path.equals("user/details") || path.equals("user/check_password") ||
                path.equals("user/change_password") || path.equals("user/username_change") ||
                path.equals("user/add_to_cart") || path.equals("user/remove_from_cart") ||
                path.equals("user/increase_item_amount") || path.equals("decrease_item_amount") ||
                path.equals("user/cart_sum") || path.equals("user/fulfill")) && !role.equals(RoleType.USER)) {

            redirectTo(request, response, role);

        } else if ((path.equals("admin") || path.equals("admin/product_price") ||
                path.equals("admin/product_amount") || path.equals("admin/update_product") ||
                path.equals("admin/delete_product") || path.equals("admin/purchases") ||
                path.equals("admin/ingredients") || path.equals("admin/delete_ingredient") ||
                path.equals("admin/ingredient_increase") || path.equals("admin/ingredient_decrease") ||
                path.equals("admin/ingredient_amount") || path.equals("admin/save_new_ingredient") ||
                path.equals("admin/save_new_product")) && !role.equals(RoleType.ADMIN)) {

            redirectTo(request, response, role);

        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, RoleType role)
            throws IOException {
        if (role.equals(RoleType.ANON)) {
            response.sendRedirect(request.getContextPath());
        } else if (role.equals(RoleType.USER)) {
            response.sendRedirect(request.getContextPath() + "/user");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin");
        }
    }
}
