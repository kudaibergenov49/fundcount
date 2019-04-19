package com.fundCount.exchangeratesapi.exchange.profit.service;

import com.fundCount.exchangeratesapi.exchange.profit.model.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class ForeignExchangeRestService {
    private static final BigDecimal SPREAD = new BigDecimal(0.64);
    private RestTemplate restTemplate;

    public ForeignExchangeRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String profit(String date, Integer amount) {
        if (date == null){
            return "0";
        }
        BigDecimal latestRate = getRate("RUB", null).getRates().get("RUB");
        BigDecimal dateRate = getRate("RUB", date).getRates().get("RUB");
        BigDecimal diff = latestRate.subtract(dateRate).add(SPREAD);
        return diff.multiply(new BigDecimal(amount)).toString();
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