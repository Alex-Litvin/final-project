package ua.training;


import ua.training.model.Speciality;
import ua.training.model.User;
import ua.training.model.enums.Role;
import ua.training.model.enums.Status;
import ua.training.model.enums.Subject;
import ua.training.model.service.ExamService;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.SubjectService;
import ua.training.model.service.implementation.ServiceFactoryImpl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SubjectService subjectService = ServiceFactoryImpl.getInstance().getSubjectService();
        ExamService examService = ServiceFactoryImpl.getInstance().getExamService();
//        System.out.println(examService.countExamsByUserId(27L));
        SpecialityService specialityService = ServiceFactoryImpl.getInstance().getSpecialityService();


        List<Speciality> allSpecialitiesWithSubjects = specialityService.findAllSpecialitiesWithSubjectsByUniversityIds(Arrays.asList(6l, 8l));

        List<Long> subjectIds = Arrays.asList(1l, 2l, 3l);

        Set<Subject> userSubject = new HashSet<>();
        for (Long subjectId : subjectIds) {
            userSubject.add(subjectService.getSubjectById(subjectId));
        }
        userSubject.forEach(System.out::println);


    }
    private static String getUnicodeCodes(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
            result.append("\\u" + Integer.toHexString(str.charAt(i) | 0x10000).substring(1));

        return result.toString();
    }
}
