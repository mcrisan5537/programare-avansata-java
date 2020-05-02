package com.testing;

import java.util.Random;

public class Weapon implements Item {

    private WeaponType type;
    private double weight;
    private double value;

    public Weapon(WeaponType type, double weight, double value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    public static Weapon getRandom(double upperLimitWeight, double upperLimitValue) {
        Random rand = new Random();

        WeaponType type = (WeaponType.values())[Math.abs(rand.nextInt()) % WeaponType.values().length]; // choose random type
        double weight = 1 + Math.abs(rand.nextDouble()) % upperLimitWeight; // 1 <= weight <= upperLimitWeight
        double value = 1 + Math.abs(rand.nextDouble()) % upperLimitValue; // 1 <= value <= upperLimitValue

        return new Weapon(type, weight, value);
    }

    @Override
    public String getName() {
        return type.toString();
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getName() + ", weight = " + getWeight() + ", value = " + getValue() + " ( profit factor = " + profitFactor() + " ) ";
    }

}
