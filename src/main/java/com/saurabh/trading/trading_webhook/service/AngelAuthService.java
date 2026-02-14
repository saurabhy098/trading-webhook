package com.saurabh.trading.trading_webhook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class AngelAuthService {

    private final WebClient webClient;

    @Value("${angel.api.key}")
    private String apiKey;

    @Value("${angel.client.code}")
    private String clientCode;

    @Value("${angel.mpin}")
    private String mpin;

    @Value("${angel.totp}")
    private String totp;

    private String jwtToken;

    public AngelAuthService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://apiconnect.angelone.in")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String getJwtToken() {

        if (jwtToken != null) {
            return jwtToken;
        }

        Map<String, String> request = new HashMap<>();
        request.put("clientcode", clientCode);
        request.put("password", mpin);
        request.put("totp", totp);

        Map response = webClient.post()
                .uri("/rest/auth/angelbroking/user/v1/loginByPassword")
                .header("X-PrivateKey", apiKey)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map data = (Map) response.get("data");
        jwtToken = (String) data.get("jwtToken");

        System.out.println("Angel Login Successful");

        return jwtToken;
    }
}
