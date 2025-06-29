package cn.lonesome.sms.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.model.dto.AjaxResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * @author Yale
 * @version 1.0
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!request.getRequestURI().endsWith("/login") && !StpUtil.isLogin()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(mapper.writeValueAsString(AjaxResp.fail(ErrorCode.NOT_LOGIN)));
                log.info(ErrorCode.NOT_LOGIN.getMsg());
                return false;
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
