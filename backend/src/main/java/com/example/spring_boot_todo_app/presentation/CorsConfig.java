package com.example.spring_boot_todo_app.presentation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // すべてのオリジンからのリクエストを許可
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 許可するHTTPメソッド
                .exposedHeaders("X-Total-Count"); // 公開するレスポンスヘッダーを指定
    }
}