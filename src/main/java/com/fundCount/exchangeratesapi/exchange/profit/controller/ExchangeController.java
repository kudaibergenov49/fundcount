package com.fundCount.exchangeratesapi.exchange.profit.controller;

import com.fundCount.exchangeratesapi.exchange.profit.service.ForeignExchangeRestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ExchangeController {
    private final ForeignExchangeRestService foreignExchangeRestService;

    public ExchangeController(ForeignExchangeRestService foreignExchangeRestService) {
        this.foreignExchangeRestService = foreignExchangeRestService;
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        return "main";
    }

    @PostMapping("find")
    public String find(@RequestParam(name = "date") String date,
                       @RequestParam(name = "amount") Integer amount,
                       Map<String, Object> model) {
        String profit = foreignExchangeRestService.profit(date, amount);
        model.put("profit", profit);
        return "main";
    }
}