package com.saurabh.trading.trading_webhook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(
        name = "paper.trading",
        havingValue = "false"
)
public class RealAngelOrderService implements OrderService {

    private final WebClient webClient;

    @Autowired
    private AngelAuthService authService;

    @Value("${angel.api.key}")
    private String apiKey;

    public RealAngelOrderService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://apiconnect.angelone.in")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public void placeMarketOrder(String symbol,
                                 String symbolToken,
                                 String transactionType) {

        String jwt = authService.getJwtToken();

        Map<String, Object> order = new HashMap<>();
        order.put("variety", "NORMAL");
        order.put("tradingsymbol", symbol);
        order.put("symboltoken", symbolToken);
        order.put("transactiontype", transactionType);
        order.put("exchange", "NSE");
        order.put("ordertype", "MARKET");
        order.put("producttype", "INTRADAY");
        order.put("duration", "DAY");
        order.put("quantity", "1");

        String response = webClient.post()
                .uri("/rest/secure/angelbroking/order/v1/placeOrder")
                .header("Authorization", "Bearer " + jwt)
                .header("X-PrivateKey", apiKey)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("REAL ORDER Response: " + response);
    }
}