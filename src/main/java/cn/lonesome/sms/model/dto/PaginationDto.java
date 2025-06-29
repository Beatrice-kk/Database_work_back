package cn.lonesome.sms.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@Builder
public class PaginationDto<T> {
    private ArrayList<T> data;
    private Integer size;
    private Integer page;
    private Integer total;
}