package com.records.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.records.common.result.Result;
import com.records.common.result.ResultCodeForToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;

/**
 * 自定义返回结果：没有权限访问时
 *
 * @date 2023/2/12
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(new Result<>(ResultCodeForToken.ILLEGAL_AUTHENTICATION, e.getMessage())));
        response.getWriter().flush();
    }

}
