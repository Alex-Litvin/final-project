package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.get.*;
import ua.training.controller.command.post.*;
import ua.training.controller.utility.Page;

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

@WebServlet(urlPatterns = {Page.HOME, Page.LOGIN, Page.LOGOUT, Page.LOGIN_CONFIRM, Page.REGISTRATION, Page.ADMIN_UNIVERSITIES, Page.ADMIN_SPECIALITIES, Page.ADMIN_SPECIALITIES_ADD,
        Page.ADMIN_SHOW_SPECIALITIES, Page.ADMIN_SPECIALITY_DELETE, Page.ADMIN_EXAM_MARK, Page.ADMIN_SHOW_USER_EXAMS, Page.ADMIN_ADD_EXAM_MARK,
        Page.ADMIN_COMPLETE_SPECIALITY, Page.ADMIN_SHOW_SPECIALITIES_BY_UNIVERSITY, Page.ADMIN_COMPLETE_SPECIALITY_REGISTRATION, Page.ADMIN_STUDENTS,
        Page.USER_EXAM_REGISTRATION, Page.USER_ADD_EXAM, Page.USER_UNIVERSITIES_SPECIALITIES, Page.USER_SHOW_SPECIALITIES, Page.USER_SPECIALITY_REQUEST,
        Page.USER_ADD_SPECIALITY_REQUEST, Page.USER_SPECIALITY_RATING, Page.USER_SHOW_RATING, Page.REGISTRATION_CONFIRM})
public class Servlet extends HttpServlet implements Page {

    private Map<String, Command> command = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext()
                .setAttribute("logged_email", new HashMap<String, HttpSession>());

        command.put(HOME, new HomeCommand());
        command.put(LOGIN, new Login());
        command.put(LOGOUT, new LogoutCommand());
        command.put(LOGIN_CONFIRM, new LoginConfirm());
        command.put(REGISTRATION, new RegistrationCommand());
        command.put(REGISTRATION_CONFIRM, new RegistrationConfirmCommand());
        command.put(ADMIN_UNIVERSITIES, new UniversitiesCommand());
        command.put(ADMIN_SPECIALITIES, new ShowSpecialitiesCommand());
        command.put(ADMIN_SPECIALITIES_ADD, new AddSpecialityCommand());
        command.put(ADMIN_SHOW_SPECIALITIES, new ShowUniversitySpecialitiesCommand());
        command.put(ADMIN_SPECIALITY_DELETE, new DeleteSpecialityCommand());
        command.put(ADMIN_EXAM_MARK, new ShowAllStudentsCommand());
        command.put(ADMIN_SHOW_USER_EXAMS, new ShowStudentExamsCommand());
        command.put(ADMIN_ADD_EXAM_MARK, new AddExamMarkCommand());
        command.put(ADMIN_COMPLETE_SPECIALITY, new ShowUniversitiesCommand());
        command.put(ADMIN_SHOW_SPECIALITIES_BY_UNIVERSITY, new ShowSpecialitiesByUniversity());
        command.put(ADMIN_COMPLETE_SPECIALITY_REGISTRATION, new CompleteSpecialityRegistrationCommand());
        command.put(ADMIN_STUDENTS, new ShowStudentsCommand());
        command.put(USER_EXAM_REGISTRATION, new ShowSubjectsCommand());
        command.put(USER_ADD_EXAM, new ExamRegistrationCommand());
        command.put(USER_UNIVERSITIES_SPECIALITIES, new ShowUniversitiesCommand());
        command.put(USER_SHOW_SPECIALITIES, new ShowUniversitySpecialitiesCommand());
        command.put(USER_SPECIALITY_REQUEST, new ShowAvailableSpeciality());
        command.put(USER_ADD_SPECIALITY_REQUEST, new AddSpecialityRequestCommand());
        command.put(USER_SPECIALITY_RATING, new ShowSpecialityRequestCommand());
        command.put(USER_SHOW_RATING, new ShowSpecialityRatingCommand());
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

        System.out.println("execute command path: " +path);

        Command servletCommand = command.getOrDefault(path, (r, q) -> HOME);
        String page = servletCommand.execute(req, resp);
        System.out.println("page: - " + page);

        if (page.contains(REDIRECT)) {
            System.out.println("redirect here to " + path);
            resp.sendRedirect(page.replace(REDIRECT, ""));
        } else {
            System.out.println("else handleServlet forward /view" + page);
            req.getRequestDispatcher(VIEW + page).forward(req, resp);
        }
    }
}

