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
public class Teacher {
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private Integer birthYear;
    private String jobTitle;
    private String college;
}
