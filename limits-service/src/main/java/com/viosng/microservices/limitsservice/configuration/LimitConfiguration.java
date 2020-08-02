package com.viosng.microservices.limitsservice.configuration;

public class LimitConfiguration {
    private final int maximum;
    private final int minimum;

    public LimitConfiguration(int maximum, int minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimum() {
        return minimum;
    }
}
