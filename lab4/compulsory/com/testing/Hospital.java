package com.testing;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital> {

    private String name;

    public Hospital(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Hospital h) {
        return this.getName().compareTo(h.getName());
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                '}';
    }
}
