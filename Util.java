import java.util.TreeMap;
import java.util.TreeSet;

public class Util {
    // Convenience function to get all edges while ignoring duplicates due to 
    // the representation of undirected graphs where edges are counted twice.
    // Use a set to perform the deduplication.
    public static TreeSet<Edge> getUniqueEdges(TreeMap<Node, TreeSet<Edge>> graph) {
        TreeSet<Edge> edges = new TreeSet<>();
        for (TreeSet<Edge> outgoing : graph.values()) {
            edges.addAll(outgoing);
        }
        return edges;
    }
}
