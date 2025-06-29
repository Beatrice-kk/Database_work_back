package cn.lonesome.sms.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@Builder
public class Admin {
    private Integer id;
    private String password;
    private Role role;

    public enum Role {
        SUPER_ADMIN, COLLEGE_ADMIN, SCHOOL_ADMIN
    }
}
