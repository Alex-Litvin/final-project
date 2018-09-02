package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.get.*;
import ua.training.controller.command.post.*;

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

@WebServlet(urlPatterns = {"/login", "/login_confirm", "/admin/universities", "/admin/specialities", "/admin/specialities_add",
"/admin/show_specialities", "/admin/speciality_delete", "/admin/exam_mark", "/admin/show_user_exams", "/admin/add_exam_mark",
"/admin/complete_speciality", "/admin/show_specialities_by_university", "/admin/complete_speciality_registration", "/admin/students",
"/user/exam_registration", "/user/add_exam", "/user/universities_specialities", "/user/show_specialities", "/user/speciality_request",
"/user/add_speciality_request", "/user/speciality_rating", "/user/show_rating", "/logout", "/registration", "/registration_confirm"})
public class Servlet extends HttpServlet {

    private Map<String, Command> command = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext()
                .setAttribute("logged_email", new HashMap<String, HttpSession>());
        command.put("/login", new Login());
        command.put("/logout", new LogoutCommand());
        command.put("/login_confirm", new LoginConfirm());
        command.put("/registration", new RegistrationCommand());
        command.put("/registration_confirm", new RegistrationConfirmCommand());
        command.put("/admin/universities", new UniversitiesCommand());
        command.put("/admin/specialities", new ShowSpecialitiesCommand());
        command.put("/admin/specialities_add", new AddSpecialityCommand());
        command.put("/admin/show_specialities", new ShowUniversitySpecialitiesCommand());
        command.put("/admin/speciality_delete", new DeleteSpecialityCommand());
        command.put("/admin/exam_mark", new ShowAllStudentsCommand());
        command.put("/admin/show_user_exams", new ShowStudentExamsCommand());
        command.put("/admin/add_exam_mark", new AddExamMarkCommand());
        command.put("/admin/complete_speciality", new ShowUniversitiesCommand());
        command.put("/admin/show_specialities_by_university", new ShowSpecialitiesByUniversity());
        command.put("/admin/complete_speciality_registration", new CompleteSpecialityRegistrationCommand());
        command.put("/admin/students", new ShowStudentsCommand());
        command.put("/user/exam_registration", new ShowSubjectsCommand());
        command.put("/user/add_exam", new ExamRegistrationCommand());
        command.put("/user/universities_specialities", new ShowUniversitiesCommand());
        command.put("/user/show_specialities", new ShowUniversitySpecialitiesCommand());
        command.put("/user/speciality_request", new ShowAvailableSpeciality());
        command.put("/user/add_speciality_request", new AddSpecialityRequestCommand());
        command.put("/user/speciality_rating", new ShowSpecialityRequestCommand());
        command.put("/user/show_rating", new ShowSpecialityRatingCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }
}
