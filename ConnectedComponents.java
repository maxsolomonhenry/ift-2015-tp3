import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class ConnectedComponents {
    private Map<Node, Integer> node2int = new HashMap<>();
    private DisjointSet sets;

    public ConnectedComponents(Set<Node> nodes) {
        sets = new DisjointSet(nodes.size());

        // // Create map of nodes -> int.
        int counter = 0;
        for (Node node : nodes) {
            node2int.put(node, counter);
            counter++;
        }
    }

    // Simple wrapper over DisjointSets that accepts a node.
    public int find(Node node) {
        return sets.find(node2int.get(node));
    }

    // Likewise, simple wrapper for union that accepts nodes.
    public boolean union(Node u, Node v) {
        return sets.union(node2int.get(u), node2int.get(v));
    }
}
