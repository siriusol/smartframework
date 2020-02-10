package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet 助手类
 *
 * @author Ther
 * @since 1.0.0
 */
public final class ServletHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

    /**
     * 使每个线程独自拥有一份 ServletHelper 实例
     */
    private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER
             = new ThreadLocal<>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * 初始化
     */
    public static void init(HttpServletRequest request, HttpServletResponse response) {
        SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
    }

    /**
     * 销毁
     */
    public static void destroy() {
        SERVLET_HELPER_HOLDER.remove();
    }

    private static HttpServletRequest getHttpServletRequest() {
        return SERVLET_HELPER_HOLDER.get().request;
    }

    private static HttpServletResponse getHttpServletResponse() {
        return SERVLET_HELPER_HOLDER.get().response;
    }

    private static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    private static ServletContext getServletContext() {
        return getHttpServletRequest().getServletContext();
    }

    /**
     * 将属性放到 HttpRequest 中
     */
    public static void setHttpRequestAttribute(String key, Object value) {
        getHttpServletRequest().setAttribute(key, value);
    }

    /**
     * 从 HttpRequest 中获取属性
     */
    @SuppressWarnings("unchecked")
    public static <T> T getHttpRequestAttribute(String key) {
        return (T) getHttpServletRequest().getAttribute(key);
    }

    /**
     * 从 HttpRequest 中移除属性
     */
    public static void removeHttpRequestAttribute(String key) {
        getHttpServletRequest().removeAttribute(key);
    }

    /**
     * 将属性放到 HttpSession 中
     */
    public static void setSessionAttribute(String key, Object value) {
        getHttpSession().setAttribute(key, value);
    }

    /**
     * 从 HttpSession 中获取属性
     */
    @SuppressWarnings("unchecked")
    public static <T> T getHttpSessionAttribute(String key) {
        return (T) getHttpSession().getAttribute(key);
    }

    /**
     * 从 HttpSession 中移除属性
     */
    public static void removeHttpSessionAttribute(String key) {
        getHttpSession().removeAttribute(key);
    }

    /**
     * 使 HttpSession 失效
     */
    public static void invalidateHttpSession() {
        getHttpSession().invalidate();
    }

    /**
     * 发送重定向响应
     */
    public static void sendRedirect(String location) {
        try {
            getHttpServletResponse().sendRedirect(getHttpServletRequest().getContextPath() + location);
        } catch (IOException e) {
            LOGGER.error("redirect failure", e);
        }
    }
}
