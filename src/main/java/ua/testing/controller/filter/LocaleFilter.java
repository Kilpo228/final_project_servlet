package ua.testing.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getRequestURI().equals("/app/en")) {
            request.getSession().setAttribute("locale", new Locale("en", "US"));
        } else if (request.getRequestURI().equals("/app/ru")) {
            request.getSession().setAttribute("locale", new Locale("ru", "RU"));
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
