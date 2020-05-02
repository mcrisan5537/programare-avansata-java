package com.testing;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Book implements Item {

    private String name;
    private int pages;
    private double value;

    public Book(String name, int pages, double value) {
        this.name = name;
        this.pages = pages;
        this.value = value;
    }

    public static Book getRandom(int upperLimitWeight, double upperLimitValue) {
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

        String name = "Book : " + sb.toString();
        int pages = 100 + Math.abs(rand.nextInt()) % (upperLimitWeight * 100);
        // 100 <= pages <= upperLimitWeight * 100
        // <=>
        // 1 <= weight <= upperLimitWeight
        double value = 1 + Math.abs(rand.nextDouble()) % upperLimitValue;

        return new Book(name, pages, value);
    }

    public double getWeight() {
        return pages / 100;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
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
        return getName() + ", weight = " + getWeight() + ", value = " + getValue() + " ( " + profitFactor() + " ) ";
    }

}
