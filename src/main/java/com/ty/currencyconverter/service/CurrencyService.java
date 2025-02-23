package com.ty.currencyconverter.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ty.currencyconverter.dto.ConversionRequest;

@Service
public class CurrencyService {
    
    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public CurrencyService() {
    }

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getExchangeRates(String base) {
        try {
            String url = API_URL + base;
            return restTemplate.getForObject(url, Map.class);
        } catch (RestClientException e) {
            throw new RuntimeException("External API is unavailable: " + e.getMessage(), e);
        }
    }

    public Map<String, Object> convertCurrency(ConversionRequest request) {
        if (request == null || request.getFrom() == null || request.getTo() == null) {
            throw new IllegalArgumentException("Invalid conversion request: missing parameters");
        }

        Map<String, Object> response = getExchangeRates(request.getFrom());
        if (response == null || !response.containsKey("rates")) {
            throw new IllegalArgumentException("Invalid base currency or API response does not contain rates");
        }

        @SuppressWarnings("unchecked")
        Map<String, Double> rateMap = (Map<String, Double>) response.get("rates");

        if (!rateMap.containsKey(request.getTo())) {
            throw new IllegalArgumentException("Invalid target currency code: " + request.getTo());
        }

        double convertedAmount = request.getAmount() * rateMap.get(request.getTo());

        return Map.of(
                "from", request.getFrom(),
                "to", request.getTo(),
                "amount", request.getAmount(),
                "convertedAmount", convertedAmount
        );
    }
}
