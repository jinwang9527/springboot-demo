package com.firststringboot.common.filter;

import com.firststringboot.common.model.ResultModel;
import com.firststringboot.common.model.Token;
import com.firststringboot.common.responseCode.CodeStratus;
import com.firststringboot.common.utils.JWTUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {


    private static final Logger logger = Logger.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.warn("<====== TokenInterceptor 开始解析请求头中token =======>");
        String tokenString = request.getHeader("token");
        if (tokenString == null) return responseMessage(response);
        Token token = JWTUtil.unsign(tokenString,Token.class);
        if (token == null) return responseMessage(response);
        request.setAttribute("token",token);
        logger.warn("<====== TokenInterceptor 开始解析请求头中token成功 =======>");
        return true;
    }

    private boolean responseMessage(HttpServletResponse response) {
        JSONObject jsonObject = JSONObject.fromObject(ResultModel.getErrorResultModel(CodeStratus.INVALID_TOKEN.getCode(), CodeStratus.INVALID_TOKEN.getMessage()));
        String message = jsonObject.toString();
        try {
            response.setHeader("Content-Type", "application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(message);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            logger.error("关闭Response输出流异常!!!", e);
        }
        logger.warn("<====== TokenInterceptor 解析请求头中token失败 =======>");
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
