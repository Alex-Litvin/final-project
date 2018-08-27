package ua.training.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.training.model.entity.enums.EnterSpecialityStatus;
import ua.training.model.entity.enums.Subject;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityResultDto {
    private String firstName;
    private String middleName;
    private String secondName;
    private String specialityTitle;
    private String universityTitle;
    private Integer maxStudentCount;
    private Integer specialityPassmark;
    private List<Exam> userExams;
    private Integer totalUserMark;
    private EnterSpecialityStatus status;
}
