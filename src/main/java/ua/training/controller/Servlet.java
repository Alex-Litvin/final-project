package ua.training.controller;

import ua.training.controller.command.AddSpecialityCommand;
import ua.training.controller.command.Command;
import ua.training.controller.command.get.Login;
import ua.training.controller.command.get.ShowSpecialitiesCommand;
import ua.training.controller.command.get.UniversitiesCommand;
import ua.training.controller.command.post.LoginConfirm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/login", "/login_confirm", "/universities", "/admin/specialities", "/admin/specialities_add"})
public class Servlet extends HttpServlet {
//    private ControllerHelper controllerHelper = ControllerHelper.getInstance();

    private Map<String, Command> command = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext()
                .setAttribute("logged_email", new HashMap<String, HttpSession>());
        command.put("/login", new Login());
        command.put("/login_confirm", new LoginConfirm());
        command.put("/universities", new UniversitiesCommand());
        command.put("/admin/specialities", new ShowSpecialitiesCommand());
        command.put("/admin/specialities_add", new AddSpecialityCommand());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        boolean loginStatus = session.getAttribute("user") != null;
//        if ("registration".equals(req.getParameter("command")) && !loginStatus) {
//            req.getRequestDispatcher("/view/registration.jsp").forward(req, resp);
//        }
//        if (!loginStatus) {
//            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
//        }else {
//            executeCommand(req, resp);
//        }
        executeCommand(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeCommand(req, resp);
    }

    private void executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();

        System.out.println(path);

        Command servletCommand = command.getOrDefault(path, (r, q) -> "/login");
        String page = servletCommand.execute(req, resp);
        System.out.println("page: - " + page);
        if (page.contains("redirect:")) {
            System.out.println("redirect here to " + path);
            resp.sendRedirect(page.replace("redirect:", ""));
        } else {
            System.out.println("else handleServlet forward /view" + page);
            req.getRequestDispatcher("/view" + page).forward(req, resp);
        }




//        System.out.println(req.getRequestURI());
//        String index = controllerHelper.getCommand(req).execute(req, resp);
//
//        System.out.println("index" + index);
//
//        if (index.contains("redirect:")) {
//            resp.sendRedirect(index.replace("redirect:", ""));
//        } else {
//            req.getRequestDispatcher(index).forward(req, resp);
//        }

    }
}
