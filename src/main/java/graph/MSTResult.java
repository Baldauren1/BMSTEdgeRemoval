package graph;

import java.util.*;

public class MSTResult {
    public List<Edge> edges;
    public int totalCost;

    public MSTResult(List<Edge> edges) {
        this.edges = edges;
        recalcTotalCost();
    }

    public void recalcTotalCost() {
        this.totalCost = edges.stream().mapToInt(Edge::getWeight).sum();
    }

    public void print() {
        System.out.println("Edges:");
        for (Edge e : edges)
            System.out.println(" " + e);
        System.out.println("Total cost = " + totalCost);
    }
}
