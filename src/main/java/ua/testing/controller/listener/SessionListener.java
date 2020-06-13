package ua.testing.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setMaxInactiveInterval(-1);
        session.setAttribute("username", "");
        session.setAttribute("locale", new Locale("en", "US"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().removeAttribute("username");
        httpSessionEvent.getSession().removeAttribute("role");
        httpSessionEvent.getSession().removeAttribute("locale");
    }
}
