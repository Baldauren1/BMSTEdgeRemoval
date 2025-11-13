package graph;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adj = new HashMap<>();

    public void addNode(String node) {
        adj.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        addNode(from);
        addNode(to);
        adj.get(from).add(new Edge(from, to, weight));
        adj.get(to).add(new Edge(to, from, weight));
    }

    public List<String> getNodes() {
        return new ArrayList<>(adj.keySet());
    }

    public List<Edge> getAllEdges() {
        List<Edge> all = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (var list : adj.values()) {
            for (Edge e : list) {
                String key = e.getFrom() + "-" + e.getTo();
                String rev = e.getTo() + "-" + e.getFrom();
                if (!seen.contains(key) && !seen.contains(rev)) {
                    all.add(e);
                    seen.add(key);
                }
            }
        }
        return all;
    }
}
