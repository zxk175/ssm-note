package com.zxk175.ssm_note.conf.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxk175 on 16/12/7.
 */

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.zxk175.ssm_note.controller" })
public class SpringMvcXml extends WebMvcConfigurerAdapter {

    /**
     * 静态资源处理
     * 当DisptacherServlet接收到了他匹配的请求，但是找不到相应的Controller，
     * 就会把这个请求返回给默认的处理（比如交给tomcat处理）
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(urlBasedViewResolver());
    }

    //@Override
    //public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //    configurer.favorPathExtension(true).useJaf(false)
    //            .favorParameter(true).parameterName("mediaType")
    //            .ignoreAcceptHeader(true).
    //            defaultContentType(MediaType.APPLICATION_JSON).
    //            mediaType("xml", MediaType.APPLICATION_XML).
    //            mediaType("json", MediaType.APPLICATION_JSON);
    //}

    /**
     * 通用视图解析器
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver urlBasedViewResolver() {
        InternalResourceViewResolver jsp = new InternalResourceViewResolver();
        jsp.setViewClass(JstlView.class);
        jsp.setPrefix("/WEB-INF/jsp/");
        jsp.setSuffix(".jsp");
        return jsp;
    }

    /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**/").addResourceLocations("WEB-INF/css/");
        registry.addResourceHandler("/js/**/").addResourceLocations("WEB-INF/js/");
        registry.addResourceHandler("/icon/**/").addResourceLocations("WEB-INF/icon/");
        registry.addResourceHandler("/library/**/").addResourceLocations("WEB-INF/library/");
    }

    /**
     * 限制上传文件的大小
     *
     * @return
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver common = new CommonsMultipartResolver();
        //默认编码
        common.setDefaultEncoding("utf-8");
        //上传文件大小限制为31M，31*1024*1024
        common.setMaxUploadSize(31 * 1024 * 1024);
        //内存中的最大值
        common.setMaxInMemorySize(40960);
        return common;
    }

    /**
     * 字符串消息转换器
     *
     * @return
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return stringHttpMessageConverter;
    }

    /**
     * 避免IE执行AJAX时,返回JSON出现下载文件
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        MediaType html = new MediaType("text", "html", Charset.forName("UTF-8"));
        List<MediaType> medias = new ArrayList<>();
        medias.add(html);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(medias);
        return mappingJackson2HttpMessageConverter;
    }

    /**
     * 注册消息转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
    }
}
