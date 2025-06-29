package cn.lonesome.sms.exception;

import cn.lonesome.sms.constant.ErrorCode;
import lombok.Getter;

/**
 * @author Yale
 * @version 1.0
 */
@Getter
public class CustomException extends RuntimeException{
    private final Integer code;
    private final String msg;

    public CustomException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public CustomException(ErrorCode errorCode, String msg) {
        this.code = errorCode.getCode();
        this.msg = msg;
    }
}
