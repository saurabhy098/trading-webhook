package com.saurabh.trading.trading_webhook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurabh.trading.trading_webhook.model.Signal;

@Repository
public interface SignalRepository extends JpaRepository<Signal, Long> {

    boolean existsByExternalId(String externalId);
}
