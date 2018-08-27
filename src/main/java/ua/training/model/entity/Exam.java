package ua.training.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Long id;
    private Long userId;
    private Long subjectId;
    private String title;
    private Integer mark;
    private List<User> users;
    private List<ExamResult> examResults;
}
