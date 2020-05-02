package com.testing;

import java.util.List;

public class DynamicProgramming implements Algorithm {

    private int[][] matrix;
    private int[] w;
    private int[] v;

    private void initData(int knapsackCapacity, List<Item> items) {
        int noOfItems = items.size();
        matrix = new int[noOfItems + 1][knapsackCapacity + 1];
        for (int i = 0; i < noOfItems + 1; i++)
            matrix[i][0] = 0;
        for (int j = 0; j < knapsackCapacity + 1; j++)
            matrix[0][j] = 0;

        w = new int[noOfItems + 1];
        v = new int[noOfItems + 1];
        for (int i = 1; i < noOfItems + 1; i++) {
            w[i] = (int) items.get(i-1).getWeight();
            v[i] = (int) items.get(i-1).getValue();
        }
    }

    @Override
    public void solve(Knapsack knapsack, List<Item> items) {
        int noOfItems = items.size();
        int knapsackCapacity = (int)knapsack.getCapacity();
        initData((int)knapsack.getCapacity(), items);

        int maxi = 0;
        int maxj = 0;

        for(int i = 1; i < noOfItems + 1; i++)
            for(int j = 1; j < knapsackCapacity + 1; j++) {
                if (w[i] > j || j - w[i] < 0)
                    matrix[i][j] = matrix[i - 1][j];
                else
                    matrix[i][j] = Integer.max(matrix[i - 1][j],
                            matrix[i - 1][j - w[i]] + v[i]);

                if(matrix[i][j] > matrix[maxi][maxj]) {
                    maxi = i;
                    maxj = j;
                }
            }

        System.out.println("( total weight = " + maxj + ", total value " + matrix[maxi][maxj] + " )");
    }

}
