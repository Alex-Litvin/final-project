package ua.training;


import ua.training.model.User;
import ua.training.model.enums.Role;
import ua.training.model.enums.Status;
import ua.training.model.service.SubjectService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
        subjectService.getIdsByNames(Arrays.asList("BIOLOGY", "CHEMISTRY", "COMPUTER_SCIENCE")).forEach(System.out::println);

        System.out.println(getUnicodeCodes("Вийти!"));

    }
    private static String getUnicodeCodes(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
            result.append("\\u" + Integer.toHexString(str.charAt(i) | 0x10000).substring(1));

        return result.toString();
    }
}
