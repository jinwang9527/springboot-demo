package com.firststringboot.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class CharacterFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(CharacterFilter.class);

    private static final String Character = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.warn("<====== CharacterFilter 请求字符过滤器初始化中 ======> ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(Character);
        servletResponse.setCharacterEncoding(Character);
        logger.warn("<====== CharacterFilter 请求字符过滤器初始化完成，转发下一个过滤器 ======> ");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
