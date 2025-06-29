package cn.lonesome.sms.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@Builder
public class Major {
    private Integer id;
    private String name;
    private String collegeName;
}
