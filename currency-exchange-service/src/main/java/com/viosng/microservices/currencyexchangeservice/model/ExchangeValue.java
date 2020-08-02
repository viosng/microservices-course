package com.viosng.microservices.currencyexchangeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ExchangeValue {

    @Id
    private long id;

    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionRate;
    private int port;

    public ExchangeValue() {
    }

    public ExchangeValue(long id, String from, String to, BigDecimal conversionRate, int port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionRate = conversionRate;
        this.port = port;
    }

    public long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public int getPort() {
        return port;
    }

    public ExchangeValue setPort(int port) {
        this.port = port;
        return this;
    }
}
