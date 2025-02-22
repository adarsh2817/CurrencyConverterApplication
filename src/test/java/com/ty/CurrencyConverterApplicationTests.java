package com.ty;

import com.ty.currencyconverter.dto.ConversionRequest;
import com.ty.currencyconverter.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyConverterApplicationTests {

    private CurrencyService currencyService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        currencyService = new CurrencyService();
      
        try {
            java.lang.reflect.Field field = CurrencyService.class.getDeclaredField("exchangeRateApiUrl");
            field.setAccessible(true);
            field.set(currencyService, "https://api.exchangerate-api.com/v4/latest/");
        } catch (Exception e) {
            throw new RuntimeException("Failed to set exchangeRateApiUrl", e);
        }
    }

    @Test
    void testGetExchangeRates() {
        Map<String, Object> dummyResponse = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.95);
        dummyResponse.put("rates", rates);

        when(restTemplate.getForObject(anyString(), Mockito.eq(Map.class)))
            .thenReturn(dummyResponse);

        Map<String, Object> result = currencyService.getExchangeRates("USD");
        @SuppressWarnings("unchecked")
        Map<String, Double> resultRates = (Map<String, Double>) result.get("rates");
        assertEquals(0.95, resultRates.get("EUR"));
    }

    @Test
    void testConvertCurrency() {
        Map<String, Object> dummyResponse = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.95);
        dummyResponse.put("rates", rates);

        when(restTemplate.getForObject(anyString(), Mockito.eq(Map.class)))
            .thenReturn(dummyResponse);

        ConversionRequest request = new ConversionRequest();
        request.setFrom("USD");
        request.setTo("EUR");
        request.setAmount(100);

        Map<String, Object> conversionResult = currencyService.convertCurrency(request);
        assertEquals("USD", conversionResult.get("from"));
        assertEquals("EUR", conversionResult.get("to"));
        assertEquals(100, conversionResult.get("amount"));
  
        assertEquals(95.0, conversionResult.get("convertedAmount"));
    }
}
