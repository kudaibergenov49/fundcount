package com.fundCount.exchangeratesapi.exchange.profit.controller;

import com.fundCount.exchangeratesapi.exchange.profit.model.Result;
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
        return "main";//foreignExchangeRestService.diffRate("2010-01-12").toString();
    }

    @PostMapping("find")
    public String find(@RequestParam(name = "date") String date, Map<String, Object> model) {
        Result diff = date == null ? new Result("0") : new Result(foreignExchangeRestService.diffRate(date).toString());
        System.out.println(diff.getCount());
        model.put("diff", diff.getCount());
        return "main";
    }
}