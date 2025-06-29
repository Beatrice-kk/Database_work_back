package cn.lonesome.sms.constant;

import lombok.Getter;

/**
 * @author Yale
 * @version 1.0
 */
@Getter
public enum ErrorCode {
    SUCCESS(200, "success"),

    SERVER_ERROR(200500, "系统异常"),
    INVALID_CREDENTIALS(401, "Invalid credentials"),
    SA_TOKEN_ERROR(200222, "鉴权失败"),
    NOT_LOGIN(200100, "未登陆"),
    USER_EXISTED(200101, "用户已存在"),
    USER_NOT_EXISTED(200102, "用户不存在"),
    PERMISSION_DENIED(200103, "权限不足"),
    FILE_ERROR(200103, "文件错误"),

    PARAM_ERROR(200302, "参数有误"),

    ILLEGAL_REQUEST(200303, "非法请求"),
    ;

    private final Integer code;
    private final String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode getByCode(Integer code) {
        for (ErrorCode value : ErrorCode.values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        return SERVER_ERROR;
    }
}
