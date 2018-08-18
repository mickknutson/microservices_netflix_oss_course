package com.baselogic.spring.samples.helloclient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MicroserviceClient implements CommandLineRunner {


    @Autowired
    private ApplicationContext applicationContext;




    @Value("${serviceId}")
    private String serviceId;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public void run(String... args) {

        System.out.println("What's your name?");

        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        // Insert Lookup Code here...
        List<ServiceInstance> helloServers = discoveryClient.getInstances(serviceId);


        //---------------------------------------------------------------------------//
        System.out.println("The following hello-services are available:");
        for (ServiceInstance instance : helloServers) {
            System.out.printf("%s at %s\n", instance.getServiceId(), instance.getUri().toString());

        }

        //---------------------------------------------------------------------------//
        // Insert code to make the call here...
        if (helloServers.size() == 0) {
            System.out.println("Sorry, but I can't find a server to use");
            shutDown();
        }
        // Pick one
        int chosen = (int) (Math.random() * helloServers.size());
        ServiceInstance chosenInstance = helloServers.get(chosen);

        RestTemplate rt = restTemplateBuilder.build();
        URI callUri = UriComponentsBuilder.fromUri(
                chosenInstance.getUri())
                .path("/hello-message")
                .queryParam("name", name)
                .build()
                .toUri();

        // Do the call.
        Map<String, Object> resp = (Map<String, Object>) rt.getForObject(callUri, Map.class);
        System.out.println("\n");
        System.out.println("------------------------------------------------");
        System.out.println("Chosen Instance: " + chosenInstance.getUri());
        System.out.println("Server says: " + resp.get("message"));
        System.out.println("------------------------------------------------");
        System.out.println("\n");

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

}
