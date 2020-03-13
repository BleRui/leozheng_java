package net.canway.demo.mvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {

    /**
     * 如果返回的是false，那么后续流程都不会执行了，
     * 包括后面的拦截器以及自定义的后端控制
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 执行前提是第一个方法必须要返回true
     * 视图渲染前调用
     * 可以拦截自定义的控制器返回的视图进行加工，如果返回的没有ModelAndView,  那么这里起不到什么实际作用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(1);
    }

    /**
     * 执行前提是第一个方法必须要返回true
     * 视图渲染后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(2);
    }
}
