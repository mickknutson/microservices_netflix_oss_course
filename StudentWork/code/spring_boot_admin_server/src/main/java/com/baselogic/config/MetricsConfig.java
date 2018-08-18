package com.baselogic.config;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * https://touk.pl/blog/2018/03/05/spring-boot-2-0-http-request-metrics-with-micrometer/
 */
//@Configuration
public class MetricsConfig {

    private static final Duration HISTOGRAM_EXPIRY = Duration.ofMinutes(10);

    private static final Duration STEP = Duration.ofSeconds(5);

    @Value("${host_id}")
    private String hostId;

    @Value("${service_id}")
    private String serviceId;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() { // (2)
        return registry -> registry.config()
                .commonTags("host", hostId, "service", serviceId) // (3)
                .meterFilter(MeterFilter.deny(id -> { // (4)
                    String uri = id.getTag("uri");
                    return uri != null && uri.startsWith("/swagger");
                }))
                .meterFilter(new MeterFilter() {
                    @Override
                    public DistributionStatisticConfig configure(Meter.Id id,
                                                                 DistributionStatisticConfig config) {
                        return config.merge(DistributionStatisticConfig.builder()
                                .percentilesHistogram(true)
                                .percentiles(0.5, 0.75, 0.95) // (5)
                                .expiry(HISTOGRAM_EXPIRY) // (6)
                                .bufferLength((int) (HISTOGRAM_EXPIRY.toMillis() / STEP.toMillis())) // (7)
                                .build());
                    }
                });
    }

}