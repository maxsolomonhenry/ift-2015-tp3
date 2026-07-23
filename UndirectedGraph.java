import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class UndirectedGraph {

    // The only property is private, completely encapsulated.
    private TreeMap<Node, TreeSet<Edge>> graph;
    
    public UndirectedGraph() {
        this.graph = new TreeMap<Node, TreeSet<Edge>>();
    }

    // Add nodes one at a time.
    public void addNode(Node node) {
        graph.put(node, new TreeSet<>());
    }

    // Add a set of nodes (useful to build a skeleton MST).
    public void addNodes(Set<Node> nodes) {
        for (Node node : nodes) {
            this.addNode(node);
        }
    }

    // Return nodes as set (useful to build a skeleton MST).
    public Set<Node> getNodes() {
        return graph.keySet();
    }

    // Report number of nodes.
    public int getNumNodes() {
        return graph.size();
    }

    // Check for nodes in graph (useful for error checking).
    public boolean hasNode(Node node) {
        return graph.containsKey(node);
    }

    // Add "double edge," one from each node (start to end, end to start) to 
    // make the canonical representation of an undirected graph as an adjacency 
    // list.
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
