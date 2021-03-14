package com.wyu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Value("${wyu.file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${wyu.file.uploadAvatar}")
    private String uploadAvatar;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadAvatar);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
