package com.vianet.open.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Autowired
    private CrossOriginResponseInterceptor crossOriginResponseInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	    //跨域响应处理拦截器拦截器
	    InterceptorRegistration crossOriginResponseInterceptorRegistration = registry.addInterceptor(crossOriginResponseInterceptor);
	    crossOriginResponseInterceptorRegistration.addPathPatterns("/**");

        super.addInterceptors(registry);
    }

}