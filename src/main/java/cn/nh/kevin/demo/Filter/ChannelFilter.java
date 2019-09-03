package cn.nh.kevin.demo.Filter;

import cn.nh.kevin.demo.DTO.UserDTO;
import cn.nh.kevin.demo.Exception.DefineException.ChannleException;
import cn.nh.kevin.demo.Exception.ExceptionDeal.FilterErrorReturn;
import cn.nh.kevin.demo.Filter.GetBody.GetBodyHttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 标题:
 * 描述:渠道拦截过滤器
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-29 10:57
 */
@Slf4j
@WebFilter(urlPatterns = "/admin/register", filterName = "ChannelFilter")
public class ChannelFilter implements Filter {

    @Autowired
    private FilterErrorReturn error;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException,ServletException{
        log.info("到达过滤成功：时间：{}",System.currentTimeMillis());
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String Channel = null;
        Channel = req.getHeader("Channel");
        if (!Channel.equals("NUAA")){
            error.dealFilterError(req,resp,new ChannleException("非法渠道，请核实","886"));
            return;
        }
        filterChain.doFilter(req,resp);
        UserDTO userDTO = GetBodyHttpServletRequest.getInstance(req).doChange(UserDTO.class);

    }

    @Override
    public void destroy() {
    }
}
