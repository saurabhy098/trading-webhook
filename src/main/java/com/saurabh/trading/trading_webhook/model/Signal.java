package com.saurabh.trading.trading_webhook.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "signals", uniqueConstraints = {
        @UniqueConstraint(columnNames = "externalId")
})
@Data
public class Signal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId; // From TradingView or generated hash

    private String ticker;
    private String action;
    private Double price;

    private LocalDateTime receivedAt;

    @Enumerated(EnumType.STRING)
    private SignalStatus status;
}
