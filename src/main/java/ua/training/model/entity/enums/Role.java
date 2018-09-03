package ua.training.model.entity.enums;

import ua.training.controller.utility.Page;

import java.util.Arrays;
import java.util.List;

public enum Role implements Page {
    USER(VIEW_USER_USER_MENU_JSP, Arrays.asList(USER_EXAM_REGISTRATION, USER_ADD_EXAM, USER_UNIVERSITIES_SPECIALITIES, USER_SHOW_SPECIALITIES,
            USER_SPECIALITY_REQUEST, USER_ADD_SPECIALITY_REQUEST, USER_SPECIALITY_RATING, USER_SHOW_RATING, LOGOUT, REGISTRATION)),

    ADMIN(VIEW_ADMIN_ADMIN_MENU_JSP, Arrays.asList(LOGIN, ADMIN_UNIVERSITIES, ADMIN_SPECIALITIES, ADMIN_SPECIALITIES_ADD,
            ADMIN_SHOW_SPECIALITIES, ADMIN_SPECIALITY_DELETE, ADMIN_EXAM_MARK, ADMIN_SHOW_USER_EXAMS, ADMIN_ADD_EXAM_MARK,
            ADMIN_COMPLETE_SPECIALITY, ADMIN_SHOW_SPECIALITIES_BY_UNIVERSITY, ADMIN_COMPLETE_SPECIALITY_REGISTRATION, ADMIN_STUDENTS,
            LOGOUT, REGISTRATION)),

    GUEST(HOME, Arrays.asList(REGISTRATION, LOGIN));

    private String homePage;
    private List<String> allowedPages;

    Role(String homePage, List<String> allowedPages) {
        this.homePage = homePage;
        this.allowedPages = allowedPages;
    }

    public String getHomePage() {
        return homePage;
    }

    public List<String> getAllowedPages() {
        return allowedPages;
    }
}
