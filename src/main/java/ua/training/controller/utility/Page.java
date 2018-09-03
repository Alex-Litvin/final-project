package ua.training.controller.utility;

public interface Page {
    String REDIRECT = "redirect:";
    String VIEW = "/view";
    String JSP = ".jsp";

    String HOME = "/home";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String LOGIN_CONFIRM = "/login_confirm";
    String REGISTRATION = "/registration";
    String REGISTRATION_CONFIRM = "/registration_confirm";
    String VIEW_USER_USER_MENU_JSP = "/view/user/user_menu.jsp";
    String ADMIN_UNIVERSITIES = "/admin/universities";
    String ADMIN_SPECIALITIES = "/admin/specialities";
    String ADMIN_SPECIALITIES_ADD = "/admin/specialities_add";
    String ADMIN_SHOW_SPECIALITIES = "/admin/show_specialities";
    String ADMIN_SPECIALITY_DELETE = "/admin/speciality_delete";
    String ADMIN_EXAM_MARK = "/admin/exam_mark";
    String ADMIN_SHOW_USER_EXAMS = "/admin/show_user_exams";
    String ADMIN_ADD_EXAM_MARK = "/admin/add_exam_mark";
    String ADMIN_COMPLETE_SPECIALITY = "/admin/complete_speciality";
    String ADMIN_SHOW_SPECIALITIES_BY_UNIVERSITY = "/admin/show_specialities_by_university";
    String ADMIN_COMPLETE_SPECIALITY_REGISTRATION = "/admin/complete_speciality_registration";
    String ADMIN_STUDENTS = "/admin/students";

    String USER_EXAM_REGISTRATION = "/user/exam_registration";
    String USER_ADD_EXAM = "/user/add_exam";
    String USER_UNIVERSITIES_SPECIALITIES = "/user/universities_specialities";
    String USER_SHOW_SPECIALITIES = "/user/show_specialities";
    String USER_SPECIALITY_REQUEST = "/user/speciality_request";
    String USER_ADD_SPECIALITY_REQUEST = "/user/add_speciality_request";
    String USER_SPECIALITY_RATING = "/user/speciality_rating";
    String USER_SHOW_RATING = "/user/show_rating";
    String VIEW_ADMIN_ADMIN_MENU_JSP = "/view/admin/admin_menu.jsp";
}
