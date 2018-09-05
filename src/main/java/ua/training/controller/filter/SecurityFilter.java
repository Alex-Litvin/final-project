package ua.training.controller.filter;

import org.apache.log4j.Logger;
import ua.training.controller.command.ServletUtil;
import ua.training.controller.utility.Page;
import ua.training.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/user/*"})
public class SecurityFilter implements Filter, Page {

    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Security filter: init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        ServletUtil servletUtil = new ServletUtil();

        Role role = servletUtil.getSessionRole(request);
        String email = servletUtil.getSessionEmail(request);
        String requestURI = request.getRequestURI();

        log.info("Security filter: session - " + (session != null) + ", email - " + (email != null) + ", role - " + (role != null));

        if (session != null && email != null && role != null) {
            log.info("Security filter: " + role + " " + email);
            if (role.getHomePage().equals(requestURI) || role.getAllowedPages().contains(requestURI)) {
                log.info("Security filter: allowed page");
                filterChain.doFilter(req, resp);
            } else {
                log.info("Security filter: redirect to role's home page");
                response.sendRedirect(role.getHomePage());
            }
        } else {
            log.info("Security filter: redirect to login page");
            response.sendRedirect(LOGIN);
        }

    }

    @Override
    public void destroy() {
        log.info("Security filter: destroy");
    }
}
