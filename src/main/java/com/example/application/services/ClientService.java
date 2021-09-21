package com.example.application.services;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Capa Cliente que envia el servicio rest al api backend
 * La clase RestTemplate es un cliente asincróno
 * para llevar a cabo peticiones HTTP
 */
@Service
public class ClientService {
    private final KeycloakRestTemplate keycloakRestTemplate;

    public ClientService(KeycloakRestTemplate keycloakRestTemplate) {
        this.keycloakRestTemplate = keycloakRestTemplate;
    }

    public List<String> getMonths() {
        return keycloakRestTemplate.exchange("http://localhost:9999/months",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {})
                .getBody();
    }

    public String getAdminPage() {
        return keycloakRestTemplate.exchange("http://localhost:9999/admin",
                HttpMethod.GET,
                null,
                String.class)
                .getBody();
    }
}
