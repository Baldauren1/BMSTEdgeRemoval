package graph;

import java.util.*;

public class KruskalMST {
    public MSTResult run(Graph graph) {
        List<Edge> edges = graph.getAllEdges();
        edges.sort(Comparator.comparingInt(Edge::getWeight));

        UnionFind uf = new UnionFind();
        for (String node : graph.getNodes()) {
            uf.makeSet(node);
        }

        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {
            String rootA = uf.find(e.getFrom());
            String rootB = uf.find(e.getTo());
            if (!rootA.equals(rootB)) {
                uf.union(rootA, rootB);
                mst.add(e);
            }
        }

        return new MSTResult(mst);
    }
}
