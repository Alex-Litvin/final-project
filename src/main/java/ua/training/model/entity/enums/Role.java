package ua.training.model.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER("/view/user/user_menu.jsp", Arrays.asList("/user/exam_registration", "/user/add_exam", "/user/universities_specialities", "/user/show_specialities",
            "/user/speciality_request", "/user/add_speciality_request", "/user/speciality_rating", "/user/show_rating", "/view/logout", "/view/registration")),

    ADMIN("/view/admin/admin_menu.jsp", Arrays.asList("/login", "/admin/universities", "/admin/specialities", "/admin/specialities_add",
            "/admin/show_specialities", "/admin/speciality_delete", "/admin/exam_mark", "/admin/show_user_exams",
            "/admin/add_exam_mark", "/admin/complete_speciality", "/admin/show_specialities_by_university", "/admin/complete_speciality_registration", "/admin/students", "/view/logout", "/view/registration")),
    GUEST("/view/login", Arrays.asList("/view/registration"));

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
