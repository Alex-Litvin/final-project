package ua.training.controller.utility;

public class Validator {
    public static boolean checkInputParameters(String...strings) {
        for (String str : strings) {
            if (str.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
