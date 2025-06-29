package cn.lonesome.sms.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@Builder
public class Class {
    private Integer id;
    private String name;
    private Integer count;
    private String majorName;
    private String teacherName;
}
