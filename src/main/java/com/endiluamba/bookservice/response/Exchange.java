package com.endiluamba.bookservice.response;

import java.io.Serializable;
import java.util.Objects;

public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String from;
    private String to;
    private Double conversionFactor;
    private Double convertedValue;
    private String environment;

    public Exchange() {
    }

    public Exchange(Long id, String from, String to, Double conversionFactor, Double convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exchange)) return false;
        Exchange exchange = (Exchange) o;
        return Objects.equals(id, exchange.id) && Objects.equals(from, exchange.from) && Objects.equals(to, exchange.to) && Objects.equals(conversionFactor, exchange.conversionFactor) && Objects.equals(convertedValue, exchange.convertedValue) && Objects.equals(environment, exchange.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, conversionFactor, convertedValue, environment);
    }
}
