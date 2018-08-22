package com.baselogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

// add class-level annotations
@RestController
public class PriceService {

    private static Logger log = LoggerFactory.getLogger(PriceService.class);

    // Future reference to Flux
    @GetMapping("price")
    public Flux<String> hello() {

        return Mono.just(calculatePrice())
                .repeat()
                .take(5_00)
                .log("calculatePrice()")
                ;
    }


    @GetMapping("priced")
    public Flux<String> helloDelayed() {

        return Mono.just(calculatePrice())
                .repeat()
                .take(5_00)
                .log("calculatePrice(delayed)")
                .delayElements(Duration.ofMillis(500L))
                ;
    }

    public String calculatePrice() {

        int price = ThreadLocalRandom.current().nextInt(1, 20_000 + 1);
//        String priceString2 = String.format("$%d \n", price);
        String priceString = "$" + price + "\n";
        log.info(priceString);
        return priceString;
    }

}