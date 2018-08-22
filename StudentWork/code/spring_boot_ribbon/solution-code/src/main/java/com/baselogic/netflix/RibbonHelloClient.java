package com.baselogic.netflix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RibbonHelloClient implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    // Insert autowired HelloAPI here...
    @Autowired
    private HelloAPI helloAPI;

    @Override
    public void run(String... args) {

        System.out.println("What's your name?");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        /*
         * We're going to do 500 requests, and keep track of the numbers of
         * responses
         */
        Map<String, Integer> responseCounts = new HashMap<String, Integer>();
        for (int i = 0; i < 500; i++) {

            //  Insert code to make the call here...
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
