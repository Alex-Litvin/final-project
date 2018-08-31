package ua.training.model.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER("", Arrays.asList()),
    ADMIN("/view/admin/admin_menu.jsp", Arrays.asList("/view/login", "/view/universities", "/view/admin/specialities", "/view/admin/specialities_add")),
    GUEST("", Arrays.asList());

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
