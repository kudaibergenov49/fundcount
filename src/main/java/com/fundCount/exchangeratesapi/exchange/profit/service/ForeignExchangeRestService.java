package com.fundCount.exchangeratesapi.exchange.profit.service;

import com.fundCount.exchangeratesapi.exchange.profit.model.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class ForeignExchangeRestService {
    private RestTemplate restTemplate;

    public ForeignExchangeRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal diffRate(String date) {
        BigDecimal latestRate = getRate("RUB", null).getRates().get("RUB");
        BigDecimal dateRate = getRate("RUB", date).getRates().get("RUB");
        return latestRate.subtract(dateRate);
    }

    private Rate getRate(String symbols, String date) {
        if (date == null) {
            date = "latest";
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.exchangeratesapi.io/" + date + "?base=USD")
                .queryParam("symbols", symbols);
        return restTemplate.getForObject(builder.toUriString(), Rate.class);
    }
}