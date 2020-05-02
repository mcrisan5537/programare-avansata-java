package com.testing;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class Bonus implements Algorithm {

    // list of items -> total profit mapping
    public Map<List<Item>, Double> itemsToProfit = new HashMap<>();

    @Override
    public void solve(Knapsack knapsack, List<Item> items) {

        List<Item> itemsAdded;
        Graph<Item, DefaultWeightedEdge> graph;

        // for each item, create a graph and calculate each path
        for(Item item : items) {
            if(item.getWeight() < knapsack.getCapacity()) {

                // create the graph
                graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
                items.forEach(graph::addVertex);

                // add the item
                knapsack.addItem(item);

                for(Item itemInGraph : graph.vertexSet()) {
                    // if item is not in the graph and it can be added in the knapsack
                    if(knapsack.getCapacity() > 0 && !knapsack.getItems().contains(itemInGraph) &&
                            itemInGraph.getWeight() < knapsack.getCapacity())
                        knapsack.addItem(itemInGraph);
                }
            }

            // keep in memory the path and its profit
            itemsAdded = new ArrayList<>(knapsack.getItems());
            itemsToProfit.put(itemsAdded, itemsAdded.stream().mapToDouble(Item::profitFactor).sum());
        }

        List<Item> optimalPath = null;
        double maxProfit = 0;
        // find the path with the highest profit
        for(List<Item> list : itemsToProfit.keySet())
            if(itemsToProfit.get(list) > maxProfit) {
                optimalPath = list;
                maxProfit = itemsToProfit.get(list);
            }

        System.out.println("weight: " + optimalPath.stream().mapToDouble(Item::getWeight).sum());
        System.out.println("Profit: " + maxProfit);

    }

}
