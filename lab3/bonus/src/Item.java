package com.testing;

import java.util.Comparator;

public interface Item {

    String getName();
    double getValue();
    double getWeight();

    default double profitFactor() {
        return getValue() / getWeight();
    }

}
