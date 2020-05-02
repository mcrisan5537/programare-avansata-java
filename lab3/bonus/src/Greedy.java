package com.testing;

import java.util.Collections;
import java.util.List;

public class Greedy implements Algorithm {

    @Override
    public void solve(Knapsack knapsack, List<Item> items) {
        Collections.sort(items, (i1, i2) -> {
            double v1 = i1.profitFactor();
            double v2 = i2.profitFactor();
            if(v1 < v2) {
                return 1;
            } else if(v1 == v2) {
                return 0;
            } else {
                return -1;
            }
        });

        for(Item item : items)
            if (item.getWeight() < knapsack.getCapacity())
                knapsack.addItem(item);

        System.out.println(knapsack);

    }

}
