package cn.lonesome.sms.handler;

import cn.dev33.satoken.exception.SaTokenException;
import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import cn.lonesome.sms.model.dto.AjaxResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yale
 * @version 1.0
 */
@ControllerAdvice
@Order(1)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SaTokenException.class)
    @ResponseBody
    public AjaxResp<Object> saTokenException(SaTokenException e) {
        ErrorCode ec = ErrorCode.SA_TOKEN_ERROR;
        log.info("错误码: {}, 错误信息: {}, 错误描述: {}", ec.getCode(), ec.getMsg(), e.getMessage());
        return AjaxResp.fail(new CustomException(ec));
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResp<Object> customException(CustomException e) {
        ErrorCode ec = ErrorCode.getByCode(e.getCode());
        log.info("错误码: {}, 错误信息: {}, 错误描述: {}", ec.getCode(), ec.getMsg(), e.getMessage());
        return AjaxResp.fail(ec);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResp<Object> handleGlobalException(Exception e) {
        ErrorCode ec = ErrorCode.SERVER_ERROR;
        e.printStackTrace();
        log.error("错误码: {}, 错误信息: {}, 错误描述: {}", ec.getCode(), ec.getMsg(), e.getMessage());
        return AjaxResp.fail(ec);
    }
}