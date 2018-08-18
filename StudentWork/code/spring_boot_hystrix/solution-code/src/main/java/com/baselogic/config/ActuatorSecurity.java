package com.baselogic.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorSecurity //extends WebSecurityConfigurerAdapter
{

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
//                .anyRequest().hasRole("ENDPOINT_ADMIN")
//                .and()
//                .httpBasic();
//
//      // Disable all security:
//        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
//			      .anyRequest().permitAll();
//    }

}
