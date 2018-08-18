package com.baselogic.service.clientapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @Value("${serviceId}")
    private String serviceId;

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    FailAfterProperties failAfter;

    @GetMapping("/hello-message")
    public HelloResponse getMessage(
            @RequestParam(value = "name", defaultValue = "Stranger", required = false) String name) {

        // Reset requestCount after 30s
        if (failAfter.isEnabled()
                && failAfter.getRequestCount() > failAfter.getCount()
                && System.currentTimeMillis() - failAfter.getFailureTime() > 30000) {
            failAfter.setRequestCount(0);
        }

        failAfter.setRequestCount(failAfter.getRequestCount() + 1);

        if (failAfter.isEnabled() && failAfter.getRequestCount() > failAfter.getCount()) {
            failAfter.setFailureTime(System.currentTimeMillis());
            throw new RuntimeException("Service Failed");
        }

        return new HelloResponse("Hello '" + name
                + "' from server "
                + " (" + serverName + ")"
                + " (" + serviceId + ")")
                ;
    }

    @PostConstruct
    public void afterSetup() {

//        No qualifying bean of type 'org.springframework.core.env.ConfigurableEnvironment

        System.out.println("serverName: " + serverName);
        System.out.println("serviceId: " + serviceId);
        System.out.println("instanceId: " + instanceId);
        System.out.println("failAfter.enabled=" + failAfter.isEnabled());
        System.out.println("failAfter.count=" + failAfter.getCount());
    }
}
