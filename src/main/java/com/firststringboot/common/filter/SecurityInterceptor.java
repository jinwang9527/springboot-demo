package com.firststringboot.common.filter;

import com.firststringboot.common.model.ResultModel;
import com.firststringboot.common.responseCode.CodeStratus;
import com.firststringboot.common.utils.auth;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SecurityInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    private List<String> getRole() {
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("role");
        return roles;
    }


    private boolean hasPermission(Object handler,HttpServletResponse response) {
        logger.warn("<==========开始验证权限==========>");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            auth auths = handlerMethod.getMethod().getAnnotation(auth.class);

            if (auths == null) {
                auths = handlerMethod.getMethod().getDeclaringClass().getAnnotation(auth.class);
            }
            if (auths != null)
                logger.warn("<==========需要的权限==========>:" + auths.permissions());
            logger.warn("<==========拥有的权限==========>:" + this.getRole().toString());
            if (auths != null) {
                String[] s = auths.permissions().split(",");
                for (String str : this.getRole()) {
                    for (String o : s) {
                        if (o.equals(str)) {
                            logger.warn("<==========权限验证通过==========>:" + str);
                            return true;
                        }
                    }
                }
            } else {
                logger.warn("<==========请求不需要权限==========>:");
                return true;
            }
        }

        logger.warn("<==========请求权限验证不通过==========>:");
        try {
            JSONObject jsonObject = JSONObject.fromObject(ResultModel.getErrorResultModel(CodeStratus.NOT_AUTH.getCode(),CodeStratus.NOT_AUTH.getMessage()));
            String message = jsonObject.toString();
            response.getWriter().print(message);
            response.setHeader("Content-Type", "application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().flush();
            response.getWriter().close();
        }catch (Exception e){
            logger.warn("<==========关闭response输出流失败！==========>:");
        }
        return false;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return this.hasPermission(handler,response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
