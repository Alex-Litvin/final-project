package ua.training.controller.filter;

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
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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
        System.out.println("requestURI filter: " + requestURI);

        System.out.println("filter " + (session != null) + "- sess " + (email != null) + " - email " + (role != null) + "-role");

        if (session != null && email != null && role != null) {
            System.err.println("AUTHENTICATION SERVLET IN WORK Role = " + role + " email = " + email);

            if (role.getHomePage().equals(requestURI) || role.getAllowedPages().contains(requestURI)) {
                System.out.println("CONTAIN !!!!!");
                filterChain.doFilter(req, resp);
            } else {
                response.sendRedirect(role.getHomePage());
            }
        } else {
            response.sendRedirect(LOGIN);
        }

    }

    @Override
    public void destroy() {

    }
}
