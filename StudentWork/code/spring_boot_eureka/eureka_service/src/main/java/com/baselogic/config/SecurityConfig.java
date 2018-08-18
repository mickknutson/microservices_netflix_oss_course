package com.baselogic.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig //extends WebSecurityConfigurerAdapter
{

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll()
//                .and().csrf().disable();

//    http.authorizeRequests()
//            .requestMatchers(EndpointRequest.to("status", "info")).permitAll()
//.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
//.requestMatchers(EndpointRequest.toCommonLocations()).permitAll()
//.antMatchers("/**").hasRole("USER");
//

//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER")
//                .and()
//                .withUser("manager")
//                .password("password")
//                .credentialsExpired(true)
//                .accountExpired(true)
//                .accountLocked(true)
//                .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
//                .roles("MANAGER");
//    }
}

