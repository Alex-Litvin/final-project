package ua.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.training.model.enums.Subject;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Long id;
    private Long userId;
    private Long subjectId;
    private Integer mark;
    private List<User> users;
    private List<ExamResult> examResults;
}
