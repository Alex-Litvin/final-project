package ua.training.model;

import ua.training.model.enums.Subject;

import java.time.LocalDateTime;
import java.util.List;

class Exam {
    private Long id;
    private Long userId;
    private Subject subject;
    private LocalDateTime dateTime;
    private Integer mark;
    private List<User> users;
    private List<ExamResult> examResults;
}
