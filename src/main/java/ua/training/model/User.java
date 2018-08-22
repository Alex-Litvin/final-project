package ua.training.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.training.model.enums.Role;
import ua.training.model.enums.Status;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private Role role;
    private Status status;
    private String password;
    private String email;
    private String mobile;
    private List<Exam> exams;
    private List<Speciality> specialities;
}
