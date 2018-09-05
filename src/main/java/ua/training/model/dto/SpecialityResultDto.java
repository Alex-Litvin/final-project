package ua.training.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.training.model.entity.Exam;
import ua.training.model.entity.enums.EnterSpecialityStatus;

import java.util.List;

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
