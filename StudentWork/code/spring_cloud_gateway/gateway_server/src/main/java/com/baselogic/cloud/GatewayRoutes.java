package com.baselogic.cloud;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

//    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ping route
                .route("path_route", r -> r.path("/get")
                        .uri("http://httpbin.org"))

                // Simple Proxy Route
                .route("price", r ->
                        r.path("/price/**")
                                .filters(
                                        f -> f.rewritePath("/price-service/(?<segment>.*)", "/${segment}")
                                )
                                .uri("lb://price-service/price")
                )
                .build();
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        //@formatter:off
        return builder.routes()

                // http :8080/get
                // http :8080/get2
                .route("path_route", r -> r.path("/get2")
                        .uri("http://httpbin.org"))

                // http :8080 Host:www.myhost.io
                .route("host_route", r -> r.host("*.myhost.io")
                        .uri("http://httpbin.org"))

                // http :8080/rewrite Host:www.rewrite.io
                .route("rewrite_route", r -> r.host("*.rewrite.io")
                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)",
                                "/${segment}"))
                        .uri("http://httpbin.org"))

                // http :8080/delay/3 Host:www.hystrix.org
                .route("hystrix_route", r -> r.host("*.hystrix.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
                        .uri("http://httpbin.org"))

                // http :8080/delay/3 Host:www.hystrixfallback.org
                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
                        .uri("http://httpbin.org"))

                // http :8080/anything/rate Host:www.limited.org
                .route("limit_route", r -> r
                        .host("*.limited.org").and().path("/anything/**")
                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
                        .uri("http://httpbin.org"))

                // websocket_route
                .route("websocket_route", r -> r.path("/echo")
                        .uri("ws://localhost:9000"))
                .build();
        //@formatter:on
    }

    @Bean
    RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 2);
    }
}