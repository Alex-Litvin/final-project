package ua.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class University {
    private Long id;
    private String title;
    private List<Speciality> specialities;
}
