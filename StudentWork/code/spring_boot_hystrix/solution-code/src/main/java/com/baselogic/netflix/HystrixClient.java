package com.baselogic.netflix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HystrixClient implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private HelloAPI helloAPI;

    @Autowired
    private EchoServiceImpl echoService;

    @Override
    public void run(String... args) {

        System.out.println("What's your name?");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        // HelloAPI first:
        processHelloMessage(name);

        System.out.println(echoService.getGreeting(name));

        // Exit
        System.out.println("Press return to exit");
        input.nextLine();

        shutDown();

    }

    /**
     * shutDown();
     */
    private void shutDown(){
        if (applicationContext != null ) {
            System.exit(SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
                @Override
                public int getExitCode() {
                    return 418;
                }
            }));
        } else {
            System.exit(0);
        }
    }

    private void processHelloMessage(String name){

        /*
         * We're going to do 1_000 requests, and keep track of the numbers of
         * responses
         */
        Map<String, Integer> responseCounts = new HashMap<>();

        for (int i = 0; i < 1_000; i++) {

            Map<String, Object> resp = helloAPI.getGreeting(name);
            String greeting = (String) resp.get("message");

            System.out.println("Server says: " + resp.get("message"));

            if (!responseCounts.containsKey(greeting)) {
                responseCounts.put(greeting, 0);
            }
            responseCounts.put(greeting, responseCounts.get(greeting) + 1);
        }
        for (String greeting : responseCounts.keySet()) {
            System.out.printf("%s occured %d times\n", greeting, responseCounts.get(greeting));
        }


    }
}
