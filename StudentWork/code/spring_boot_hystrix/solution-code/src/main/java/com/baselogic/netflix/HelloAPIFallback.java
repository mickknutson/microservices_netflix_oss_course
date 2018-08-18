package com.baselogic.netflix;

import java.util.HashMap;
import java.util.Map;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component
public class HelloAPIFallback implements HelloAPI {

    // @FeignClient is essentially performing the same task:
    //@HystrixCommand(fallbackMethod = "defaultGreeting")
    @Override
    public Map<String, Object> getGreeting(String name) {
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("message", "Fallback");
        return resp;
    }

}
