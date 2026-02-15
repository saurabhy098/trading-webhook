package com.saurabh.trading.trading_webhook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurabh.trading.trading_webhook.model.Signal;
import com.saurabh.trading.trading_webhook.model.TradingViewPayload;
import com.saurabh.trading.trading_webhook.repository.SignalRepository;

@Service
public class SignalService {

    @Autowired
    private SignalRepository signalRepository;

    public Signal createSignal(TradingViewPayload payload) {

        String externalId = payload.getExternalId();

        // If TradingView doesn't send one, generate hash
        if (externalId == null || externalId.isBlank()) {
            String raw = payload.getTicker()
                    + payload.getAction()
                    + payload.getTime();

            externalId = HashUtil.sha256(raw);
        }

        // Check duplicate BEFORE saving
        if (signalRepository.existsByExternalId(externalId)) {
            throw new RuntimeException("Duplicate signal ignored");
        }

        Signal signal = new Signal();
        signal.setExternalId(externalId);
        signal.setTicker(payload.getTicker());
        signal.setAction(payload.getAction());
        signal.setPrice(payload.getPrice());
        signal.setReceivedAt(LocalDateTime.now());
        signal.setStatus(SignalStatus.RECEIVED);

        return signalRepository.save(signal);
    }
}