package cn.lonesome.sms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private String id;
    private String name;
    private String term;
    private String collegeName;
    private String examinationMethod;
    private Double credit;
    private Integer hours;
    private String attribute;
    private Integer year;

    private LocalDateTime examDate;

}
