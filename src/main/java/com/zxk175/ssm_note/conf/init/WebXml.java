package com.zxk175.ssm_note.conf.init;

import com.zxk175.ssm_note.conf.spring.SpringBase;
import com.zxk175.ssm_note.conf.spring.SpringDaoXml;
import com.zxk175.ssm_note.conf.spring.SpringMvcXml;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;


/**
 * 项目的入口，作用类似于web.xml
 * <p>
 * Created by zxk175 on 16/12/7.
 */
public class WebXml extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String[] MAPPING_URL = {"/", "*.woff", "*.woff2", "*.ttf", "*.ico"};

    /**
     * 指定被DispatcherServlet处理的url
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return MAPPING_URL;
    }

    /**
     * 配置DispatcherServlet，如果在rootConfig指定了该转发规则就可以忽略
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //SpringMvcXml.class spring Context载入监听器
        return new Class[]{SpringMvcXml.class};
    }

    /**
     * 配置root上下文,如数据源等等的配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //IntrospectorCleanupListener.class spring内存溢出监听器
        return new Class[]{SpringBase.class, SpringDaoXml.class, IntrospectorCleanupListener.class, ContextLoaderListener.class};
    }

    /**
     * 配置servlet过滤器
     *
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{characterEncodingFilter()};
    }

    /**
     * 当registerDispatcherServlet完成时自定义registration
     *
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("defaultHtmlEscape", "true");
        registration.setInitParameter("spring.profiles.active", "default");
    }

    /**
     * 字符集过滤器
     *
     * @return
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        //字符集过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
