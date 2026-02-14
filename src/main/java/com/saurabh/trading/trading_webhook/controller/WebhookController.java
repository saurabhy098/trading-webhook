package com.saurabh.trading.trading_webhook.controller;

import com.saurabh.trading.trading_webhook.model.TradingViewPayload;
import com.saurabh.trading.trading_webhook.service.OrderService;
import com.saurabh.trading.trading_webhook.service.RealAngelOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    //private RealAngelOrderService orderService; for real trading
    private OrderService orderService; // for paper trading

    private static final Logger logger =
            LoggerFactory.getLogger(WebhookController.class);

    @PostMapping
    public ResponseEntity<String> receiveWebhook(
            @RequestBody TradingViewPayload payload) {
        System.out.println("Received Signal: " + payload);

        logger.info("Received Webhook:");
        logger.info("Ticker: {}", payload.getTicker());
        logger.info("Action: {}", payload.getAction());
        logger.info("Price: {}", payload.getPrice());
        logger.info("Time: {}", payload.getTime());
String transactionType =
                payload.getAction().equalsIgnoreCase("BUY") ? "BUY" : "SELL";
                        orderService.placeMarketOrder(payload.getTicker(), payload.getSymbolToken(), transactionType);


        return ResponseEntity.ok("order placed  Ticker: " + payload.getTicker() + ", Action: " + payload.getAction() + ", Price: " + payload.getPrice() + ", Time: " + payload.getTime());
    }
}
