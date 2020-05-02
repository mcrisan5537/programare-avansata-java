package com.testing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Knapsack {

    private double capacity;
    private List<Item> items;

    public Knapsack(double capacity) {
        this.capacity = capacity;
        items = new ArrayList<>();
    }

    public Knapsack(double capacity, List<Item> items) {
        this.capacity = capacity;
        this.items = items;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
        capacity = capacity - item.getWeight();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(items, (i1, i2) -> {
            double p1 = i1.profitFactor();
            double p2 = i2.profitFactor();
            if(p1 < p2) {
                return 1;
            } else if(p1 == p2){
                return 0;
            } else {
                return -1;
            }
        });

        double totalWeight = 0;
        double totalValue = 0;

        for(Item item : items) {
//            sb.append(item);
            totalWeight += item.getWeight();
            totalValue += item.getValue();
//            sb.append("\n");
        }

        sb.append("( total weight = " + totalWeight + ", total value " + totalValue + " )");

        return sb.toString();
    }

}
