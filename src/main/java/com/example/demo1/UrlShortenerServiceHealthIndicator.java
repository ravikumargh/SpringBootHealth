package com.example.demo1;

import java.net.Socket;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UrlShortenerServiceHealthIndicator implements HealthIndicator {
    private static final String URL = "https://cleanuri.com/api/v1/shorten";

    @Override
    public Health health() {
        // check if url shortener service url is reachable
        try (Socket socket = new Socket(new java.net.URL(URL).getHost(), 80)) {
        } catch (Exception e) {
            log.warn("Failed to connect to: {}", URL);
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }

}
