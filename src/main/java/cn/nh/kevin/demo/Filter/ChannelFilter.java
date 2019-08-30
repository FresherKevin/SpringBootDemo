package cn.nh.kevin.demo.Filter;

import cn.nh.kevin.demo.Exception.DefineException.ChannleException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 标题:
 * 描述:渠道拦截过滤器
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-29 10:57
 */
@Slf4j
@WebFilter(urlPatterns = "/admin/*", filterName = "ChannelFilter")
public class ChannelFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException{
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("到达过滤成功：时间：{}",System.currentTimeMillis());
        String Channel = servletRequest.getParameter("Channel");
        if (!Channel.equals("NUAA")){
            throw new ChannleException("渠道非法","100");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
