package cn.lonesome.sms.model.dto;

import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yale
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class AjaxResp<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <N> AjaxResp<N> success() {
        return new AjaxResp<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), null);
    }

    public static <N> AjaxResp<N> success(N data) {
        return new AjaxResp<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    public static <N> AjaxResp<N> fail(CustomException e) {
        return new AjaxResp<>(e.getCode(), e.getMsg(), null);
    }

    public static <N> AjaxResp<N> fail(ErrorCode e) {
        return new AjaxResp<>(e.getCode(), e.getMsg(), null);
    }
}