package com.saurabh.trading.trading_webhook.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
@ConditionalOnProperty(
        name = "paper.trading",
        havingValue = "true"
)
public class PaperOrderService implements OrderService {

    private final AtomicLong orderCounter = new AtomicLong(1);

    @Override
    public void placeMarketOrder(String symbol,
                                 String symbolToken,
                                 String transactionType) {

        long orderId = orderCounter.getAndIncrement();

        System.out.println("=================================");
        System.out.println("📄 PAPER TRADE EXECUTED");
        System.out.println("Order ID: " + orderId);
        System.out.println("Symbol: " + symbol);
        System.out.println("Type: " + transactionType);
        System.out.println("Time: " + LocalDateTime.now());
        System.out.println("=================================");
    }
}
