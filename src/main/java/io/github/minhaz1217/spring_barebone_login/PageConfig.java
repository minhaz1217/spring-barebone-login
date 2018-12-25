package io.github.minhaz1217.spring_barebone_login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry){
        viewControllerRegistry.addViewController("/home").setViewName("home");
        viewControllerRegistry.addViewController("/").setViewName("home");
        viewControllerRegistry.addViewController("/dashboard").setViewName("dashboard");
        viewControllerRegistry.addViewController("/login").setViewName("login");
    }
}
