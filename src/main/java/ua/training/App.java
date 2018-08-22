package ua.training;


import ua.training.model.User;
import ua.training.model.enums.Role;
import ua.training.model.enums.Status;

import java.io.UnsupportedEncodingException;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        User user = User.builder()
                .firstName("Doris")
                .middleName("Pert")
                .secondName("Motov")
                .role(Role.USER)
                .password("12343")
                .mobile("0913901232")
                .email("potov@gmail.com")
                .status(Status.ACTIVE)
                .build();
        System.out.println(getUnicodeCodes("Вийти!"));

    }
    private static String getUnicodeCodes(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
            result.append("\\u" + Integer.toHexString(str.charAt(i) | 0x10000).substring(1));

        return result.toString();
    }
}
