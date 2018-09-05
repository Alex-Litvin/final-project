package ua.training.controller.utility;

import java.util.Arrays;

public class Validator {

    private static final String NAME_REG_EX = "^[A-Z][a-z]{1,20}$";
    private static final String EMAIL_REG_EX = "^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\\.)+[a-z]{2,6}$";
    private static final String PASSWORD_REG_EXP = "^[A-Za-z0-9_-]{6,20}$";

    public static boolean checkInputParameters(String...strings) {
        for (String str : strings) {
            if (str.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNames(String... names) {
        return Arrays.stream(names)
                .allMatch(name -> name.matches(NAME_REG_EX));
    }

    public static boolean checkEmail(String email) {
        return email.matches(EMAIL_REG_EX);
    }

    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_REG_EXP);
    }
}
