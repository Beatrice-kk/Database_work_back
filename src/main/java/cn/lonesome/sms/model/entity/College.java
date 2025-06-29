package cn.lonesome.sms.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@Builder
public class College {
    private Integer id;
    private String name;
}
