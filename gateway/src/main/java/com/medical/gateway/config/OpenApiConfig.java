package com.medical.gateway.config;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    GroupedOpenApi prescriptionService() {
        return GroupedOpenApi.builder()
                .group("prescriptionService")
                .pathsToMatch("/prescription/**")
                .build();
    }

    @Bean
    GroupedOpenApi userServices() {
        return GroupedOpenApi.builder()
                .group("userService")
                .pathsToMatch("/userService/**")
                .build();
    }

    // Optional: Dynamically fetch services from Eureka
    public void logRegisteredServices() {
        discoveryClient.getServices().forEach(System.out::println);
    }
}
