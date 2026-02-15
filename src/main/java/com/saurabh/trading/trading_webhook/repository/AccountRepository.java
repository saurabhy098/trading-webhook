package com.saurabh.trading.trading_webhook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurabh.trading.trading_webhook.model.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    List<Accounts> findByAccountactiveTrue();
}
