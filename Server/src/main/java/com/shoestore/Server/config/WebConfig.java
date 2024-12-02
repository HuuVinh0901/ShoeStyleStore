package com.shoestore.Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                        .allowedOrigins("http://localhost:3000") // Cho phép client từ localhost:9090
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Các phương thức HTTP cho phép
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // Cấu hình phục vụ ảnh từ thư mục static/images
                registry.addResourceHandler("/images/**")
                        .addResourceLocations("file:./src/main/resources/static/images/");
            }
        };
    }
}

