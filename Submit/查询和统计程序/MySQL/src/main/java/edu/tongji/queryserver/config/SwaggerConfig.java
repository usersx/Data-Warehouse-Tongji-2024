package edu.tongji.queryserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI movieQueryAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("电影查询系统 API")
                        .description("提供电影相关的查询服务")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Tongji University")
                                .email("your.email@tongji.edu.cn")));
    }
} 