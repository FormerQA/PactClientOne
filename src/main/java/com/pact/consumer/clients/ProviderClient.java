package com.pact.consumer.clients;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.pact.consumer.models.Greeting;


@Configuration
@Component
public class ProviderClient {
    private RestTemplate restTemplate;

    @Value("${provider.url}")
    private String provider;

    @Autowired
    public ProviderClient() {
        restTemplate = new RestTemplate();
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Greeting getProviderHello(String name) {
        String providerUrl = String.format(provider, name);
        return restTemplate.exchange(providerUrl, HttpMethod.GET, null, Greeting.class).getBody();
    }
}