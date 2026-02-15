package com.saurabh.trading.trading_webhook.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "executions")
@Data
public class Execution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long signalId;

    private String accountId;

    @Enumerated(EnumType.STRING)
    private ExecutionStatus status;

    @Column(length = 2000)
    private String brokerResponse;

    private LocalDateTime executedAt;
}
