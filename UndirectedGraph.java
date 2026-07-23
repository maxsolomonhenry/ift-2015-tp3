import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class UndirectedGraph {

    private TreeMap<Node, TreeSet<Edge>> graph;
    
    public UndirectedGraph() {
        this.graph = new TreeMap<Node, TreeSet<Edge>>();
    }

    public void addNode(Node node) {
        graph.put(node, new TreeSet<>());
    }

    public boolean hasNode(Node node) {
        return graph.containsKey(node);
    }

    public void addNodes(Set<Node> nodes) {
        for (Node node : nodes) {
            this.addNode(node);
        }
    }

    public Set<Node> getNodes() {
        return graph.keySet();
    }

    public int getNumNodes() {
        return graph.size();
    }

    public void addEdge(Edge edge) {

        if (!hasNode(edge.getStart()) || !hasNode(edge.getEnd())) {
            throw new IllegalArgumentException(
                "Edge connects node(s) not in this graph: " + edge);
        }

        // Add to both nodes because this is undirected graph.
        graph.get(edge.getStart()).add(edge);
        graph.get(edge.getEnd()).add(edge);
    }

    // Convenience function to get all edges while ignoring duplicates due to 
    // the representation of undirected graphs where edges are counted twice.
    // Use a set to perform the deduplication.
    public TreeSet<Edge> getUniqueEdges() {
        TreeSet<Edge> edges = new TreeSet<>();
        for (TreeSet<Edge> outgoing : graph.values()) {
            edges.addAll(outgoing);
        }
        return edges;
    }
}
