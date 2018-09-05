package ua.training.controller.listener;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger log = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("SessionListener: created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashMap<String, HttpSession> logged = (HashMap<String, HttpSession>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("logged_email");
        log.info("SessionListener: find all logged users");

        String userEmail = (String) httpSessionEvent.getSession()
                .getAttribute("userEmail");
        log.info("SessionListener: find userEmail for logout");

        logged.remove(userEmail);
        log.info("SessionListener: remove user from session");

        httpSessionEvent.getSession().setAttribute("logged_email", logged);
    }
}
