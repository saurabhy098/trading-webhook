package com.saurabh.trading.trading_webhook.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Accounts {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private String Id;

private String brokerUserId;
private boolean accountactive;
 private String apiKey;
 private String secret;
private LocalDateTime updated_at;

}
