package com.baselogic.netflix;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "microservice-producer", fallback = HelloAPIFallback.class)
public interface HelloAPI {

    @RequestMapping(value = "/hello-message", method = RequestMethod.GET)
    Map<String, Object> getGreeting(@RequestParam("name") String name);

}
