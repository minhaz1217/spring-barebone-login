package io.github.minhaz1217.spring_barebone_login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception         {
        auth.inMemoryAuthentication()
            .withUser("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER")
            .and()
            .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN")
            .and()
            .withUser("1").password(new BCryptPasswordEncoder().encode("1")).roles("ADMIN");
    }

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated();
        //http.anonymous().disable();
        //http.authorizeRequests().antMatchers("/signin").permitAll();
    }
    */
}
