package com.max.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String>  orderServiceFallback(){ // mono for 0 to 1 elements
        return Mono.just("Order service is taking to long to respond or the service is down , try again later");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String>  paymentServiceFallback(){
        return Mono.just("Payment service is taking to long to respond or the service is down , try again later");
    }
}
