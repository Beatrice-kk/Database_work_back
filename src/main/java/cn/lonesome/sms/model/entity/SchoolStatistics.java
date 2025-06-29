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
public class SchoolStatistics {
    private int studentCount;
    private int teacherCount;
    private int courseCount;
    private int enrollmentCount;

}
