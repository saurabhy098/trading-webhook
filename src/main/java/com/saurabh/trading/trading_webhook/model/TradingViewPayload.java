package com.saurabh.trading.trading_webhook.model;

import lombok.Data;

@Data
public class TradingViewPayload {

    private String ticker;
    private String action;
    private Double price;
    private String time;
    private String externalId;
}
