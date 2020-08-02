package com.viosng.microservices.currencyconversionservice.controller;

import com.viosng.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.viosng.microservices.currencyconversionservice.service.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CurrencyExchangeServiceProxy exchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("from", from);
            put("to", to);
        }};

        ResponseEntity<CurrencyConversionBean> entity = new RestTemplate()
                .getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, map);
        CurrencyConversionBean bean = entity.getBody();
        bean.setQuantity(quantity);
        bean.setTotalCalculatedAmount(quantity.multiply(bean.getConversionRate()));
        return bean;
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        CurrencyConversionBean bean = exchangeServiceProxy.retrieveExchangeValue(from, to);
        bean.setQuantity(quantity);
        bean.setTotalCalculatedAmount(quantity.multiply(bean.getConversionRate()));
        log.info("{}", bean);
        return bean;
    }
}
