package com.records.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.records.common.result.Result;
import com.records.common.result.ResultCodeForToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 *
 * @date 2023/2/12
 */
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        log.error("未登录或者登录过期");
        response.getWriter().println(objectMapper.writeValueAsString(new Result(ResultCodeForToken.TOKEN_EXPIRED, authException.getMessage())));
        response.getWriter().flush();
    }
}
