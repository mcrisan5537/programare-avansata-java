package com.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

//        List<Item> items = new ArrayList<>();
//
//        instance where for Knapsack.getCapacity == 5000 Greedy will fail
//        items.add(new Weapon(WeaponType.Sword, 5000, 4999)); 5000 = weight, 4999 = value
//        items.add(new Weapon(WeaponType.Knife, 1, 1)); 1 = weight = value
//
//        long time1, time2;
//        for(int i = 0; i < 5; i++) {
//            Knapsack knapsack = new Knapsack(5000 + Math.abs(new Random().nextInt()) % 5000);
//            System.out.println("Knapsack weight : " + knapsack.getCapacity());
//            items = getRandomItemList(10000);
//
//            time1 = System.nanoTime();
//            new DynamicProgramming().solve(knapsack, items);
//            time2 = System.nanoTime();
//            System.out.println("DP time in ms: " +  (time2 - time1)/1000000);
//
//            time1 = System.nanoTime();
//            new Greedy().solve(knapsack, items);
//            time2 = System.nanoTime();
//            System.out.println("GREEDY time in ms: " +  (time2 - time1)/1000000);
//
//            System.out.println();
//        }

        List<Item> items = getRandomItemList(3500);
        new Bonus().solve(new Knapsack(25000), items);

    }

    private static List<Item> getRandomItemList(int upperNoOfItemsLimit) {
        Random rand = new Random();
        List<Item> items = new ArrayList<>();

        for(int i = 0; i < upperNoOfItemsLimit; i++)
            switch(Math.abs(rand.nextInt()) % 3) {
                case 0:
                    Weapon weapon = Weapon.getRandom(20, 10);
                    weapon.setWeight((int)weapon.getWeight());
                    weapon.setValue((int)weapon.getValue());
                    items.add(weapon);
                    break;

                case 1:
                    Book book = Book.getRandom(20, 10);
                    book.setValue((int)book.getValue());
                    items.add(book);
                    break;

                case 2:
                    Food food = Food.getRandom(10);
                    food.setWeight((int)food.getWeight());
                    items.add(food);
                    break;
            }

        return items;
    }

}
