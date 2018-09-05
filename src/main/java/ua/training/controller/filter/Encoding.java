package ua.training.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class Encoding implements Filter {

    private static final org.apache.log4j.Logger log = Logger.getLogger(Encoding.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Encoding: init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        log.info("Encoding: destroy");
    }
}
