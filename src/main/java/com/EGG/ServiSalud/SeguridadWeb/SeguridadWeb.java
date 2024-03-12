package com.EGG.ServiSalud.SeguridadWeb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb  extends WebSecurityConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().requestMatchers("/css/*","/js/*", "/img/*","/**").permitAll();
    }


}
