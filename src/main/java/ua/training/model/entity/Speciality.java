package ua.training.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.training.model.entity.enums.EnterSpecialityStatus;
import ua.training.model.entity.enums.Subject;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Speciality {
    private Long id;
    private String title;
    private String universityTitle;
    private Integer maxStudentCount;
    private Integer passmark;
    private EnterSpecialityStatus status;
    private List<Subject> requiredSubject;
    private List<User> users;
}

