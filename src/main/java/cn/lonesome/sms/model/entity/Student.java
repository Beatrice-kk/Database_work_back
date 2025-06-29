package cn.lonesome.sms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String password;
    private String className;
    private String gender;
    private Double credits;
    private Integer birthYear;
    private String hometown;
}
