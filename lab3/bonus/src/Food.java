package com.testing;

import java.util.Random;

public class Food implements Item {

    private String name;
    private double weight;

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public static Food getRandom(double upperLimitWeight) {
        Random rand = new Random();

        StringBuilder sb = new StringBuilder();
        char randomASCIIChar;
        do{
            randomASCIIChar = (char)(97 + Math.abs(rand.nextInt()) % 26);
            // 97 <= randomASCIIChar <= 122
            // <=>
            // 'a' <= randomASCIIChar <= 'z'
            sb.append(randomASCIIChar);
        }while(sb.length() < 8);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

        String name = "Food : " + sb.toString();
        double weight = 1 + Math.abs(rand.nextDouble()) % upperLimitWeight;

        return new Food(name, weight);
    }

    public double getValue() {
        return weight * 2;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + ", weight = " + getWeight() + ", value = " + getValue() + " ( " + profitFactor() + " ) ";
    }

}
