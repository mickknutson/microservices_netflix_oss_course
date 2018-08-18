package com.baselogic.netflix.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

// add class-level annotations
@RestController
public class PriceService {

    private static Logger log = LoggerFactory.getLogger(PriceService.class);

    // add mapping for "/price"
    @RequestMapping("/price")
    public String getPrice() {

        int price = ThreadLocalRandom.current().nextInt(1, 20_000 + 1);
        String priceString = "$" + price;
        log.info(priceString);
        return priceString;
    }

    // insert the getPrice() method
    // return a random price (string)
    // between "$1" and "20000"

}