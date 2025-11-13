package graph;

import java.util.*;
import java.util.stream.Collectors;

public class MSTEdgeRemovalDemo {
    public static void main(String[] args) {
// Creating graph with alternative edges
        Graph g = new Graph();
        g.addEdge("A", "B", 4);
        g.addEdge("A", "C", 3);
        g.addEdge("B", "C", 2);
        g.addEdge("B", "D", 5);
        g.addEdge("C", "D", 7);
        g.addEdge("D", "E", 6);
        g.addEdge("C", "E", 1);
        g.addEdge("B", "E", 3);

// Building MST using Kruskal
        KruskalMST algo = new KruskalMST();
        MSTResult mst = algo.run(g);

        System.out.println("Original MST:");
        mst.print();

// Remove the heaviest edge
        Edge removed = Collections.max(mst.edges, Comparator.comparingInt(Edge::getWeight));
        mst.edges.remove(removed);
        mst.recalcTotalCost(); // recalculate cost after removal
        System.out.println("\nRemoved edge: " + removed);

// Determine components after edge removal
        Set<Set<String>> components = getComponents(mst, g);
        System.out.println("\nComponents after removal:");
        components.forEach(c -> System.out.println(" " + c));

// Find minimal edge to restore MST, excluding removed edge
        Edge replacement = findReplacementEdge(g, components, removed);
        System.out.println("\nReplacement edge found: " + replacement);

// Add found edge and recalculate cost
        mst.edges.add(replacement);
        mst.recalcTotalCost(); // recalculate cost after addition
        System.out.println("\nNew MST:");
        mst.print();
    }

// Getting components after edge removal
    private static Set<Set<String>> getComponents(MSTResult mst, Graph g) {
        UnionFind uf = new UnionFind();
        for (String v : g.getNodes()) uf.makeSet(v);
        for (Edge e : mst.edges) uf.union(e.getFrom(), e.getTo());

        Map<String, Set<String>> comps = new HashMap<>();
        for (String v : g.getNodes()) {
            String root = uf.find(v);
            comps.computeIfAbsent(root, k -> new HashSet<>()).add(v);
        }
        return new HashSet<>(comps.values());
    }

// Search for minimal edge to connect components, excluding removed edge
    private static Edge findReplacementEdge(Graph g, Set<Set<String>> comps, Edge removed) {
        List<Set<String>> list = new ArrayList<>(comps);
        if (list.size() != 2) {
            throw new IllegalStateException("Expected exactly 2 components after edge removal.");
        }

        Set<String> compA = list.get(0);
        Set<String> compB = list.get(1);

        Edge best = null;
        int bestW = Integer.MAX_VALUE;

// Exclude removed edge
        List<Edge> candidateEdges = g.getAllEdges().stream()
                .filter(e -> !e.equals(removed))
                .collect(Collectors.toList());

        for (Edge e : candidateEdges) {
            boolean connects = (compA.contains(e.getFrom()) && compB.contains(e.getTo()))
                    || (compA.contains(e.getTo()) && compB.contains(e.getFrom()));
            if (connects && e.getWeight() < bestW) {
                best = e;
                bestW = e.getWeight();
            }
        }
        return best;
    }
}
