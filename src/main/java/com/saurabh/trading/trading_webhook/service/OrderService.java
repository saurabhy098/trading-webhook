package com.saurabh.trading.trading_webhook.service;

public interface OrderService {

    void placeMarketOrder(String symbol,
                          String symbolToken,
                          String transactionType);
}
