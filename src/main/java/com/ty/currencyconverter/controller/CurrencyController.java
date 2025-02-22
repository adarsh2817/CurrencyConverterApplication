package com.ty.currencyconverter.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ty.currencyconverter.dto.ConversionRequest;
import com.ty.currencyconverter.service.CurrencyService;

@RequestMapping("/api")
@RestController
public class CurrencyController {
	private final CurrencyService currencyService;

	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@GetMapping("/rates")
	public ResponseEntity<Map<String, Object>> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
		try {
			Map<String, Object> rates = currencyService.getExchangeRates(base);
			return ResponseEntity.ok(rates);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("error", e.getMessage()));
		}
	}

	@PostMapping("/convert")
	public ResponseEntity<Map<String, Object>> convertCurrency(@RequestBody ConversionRequest request) {
		try {
			Map<String, Object> conversionResult = currencyService.convertCurrency(request);
			return ResponseEntity.ok(conversionResult);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("error", e.getMessage()));
		}
	}
}
