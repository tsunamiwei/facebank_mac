package com.pafacebank.facebank;

import com.pafacebank.facebank.filter.UserAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.securityContext().securityContextRepository(new NullSecurityContextRepository());
        http.cors().and().csrf().disable().httpBasic().and().authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(new UserAuthenticationFilter(), BasicAuthenticationFilter.class);
    }
}
