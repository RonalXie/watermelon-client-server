package com.xsy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer {

    // 注入我们配置文件中写好的图片保存路径
    @Value("${user.filepath.video}")
    private String video;

    // 注入我们配置文件中写好的图片保存路径
    @Value("${user.filepath.cover}")
    private String cover;


    // 注入我们配置文件中写好的图片保存路径
    @Value("${user.filepath.avatar}")
    private String avatar;

    // 自定义资源映射
    // 访问图片示例：http://localhost:3000/api/images/图片名称.jpg
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/**")
                .addResourceLocations("file:"+ video).addResourceLocations("file:"+ cover).addResourceLocations("file:"+ avatar);
    }
}
