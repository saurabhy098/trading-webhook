package com.saurabh.trading.trading_webhook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurabh.trading.trading_webhook.model.Accounts;
import com.saurabh.trading.trading_webhook.model.Signal;

@Service
public class ExecutionOrchestrator {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExecutionService executionService;

    public void processSignal(Signal signal) {

        List<Accounts> accounts = accountService.getActiveAccounts();

        for (Accounts account : accounts) {
            executionService.executeAsync(signal, account);
        }
    }
}