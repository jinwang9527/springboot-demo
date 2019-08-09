package com.firststringboot.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.warn("<========= TestFilter 测试过滤器初始化中=========>");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String url=request.getRequestURI();
        logger.warn(url);
        logger.warn("<========= TestFilter 测试过滤器初始化完成 =========>");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        logger.warn("TestFilter========= 3");
    }
}
