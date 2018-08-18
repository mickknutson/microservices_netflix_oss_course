package com.baselogic.netflix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EchoServiceImpl {

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public Map<String, Object> getGreeting(String name) {
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("message", name);
        return resp;
    }

    public Map<String, Object> defaultGreeting(String name) {
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("message", "Fallback");
        return resp;
    }

}
