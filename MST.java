import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class MST {
    
    public static TreeMap<Node, TreeSet<Edge>> applyKruskal(TreeMap<Node, TreeSet<Edge>> graph){

        // Collect all edges into a set, which will remove duplicates.
        TreeSet<Edge> edges = new TreeSet<>();
        for (TreeSet<Edge> outgoing : graph.values()) {
            edges.addAll(outgoing);
        }

        ConnectedComponents components = new ConnectedComponents(graph.keySet());
        PriorityQueue<Edge> edgesMinHeap = new PriorityQueue<>(edges);

        // Init the MST (should have same nodes as the input graph, by definition).
        TreeMap<Node, TreeSet<Edge>> tree = new TreeMap<>();
        for (Node node : graph.keySet()) {
            tree.put(node, new TreeSet<>());
        }

        int numEdges = 0;
        while (numEdges < graph.size() - 1){

            Edge edge = edgesMinHeap.poll();
            Node startNode = edge.getStart();
            Node endNode = edge.getEnd();
            
            if (components.find(startNode) != components.find(endNode)){
                
                // Following convention add the edge twice, once per node. 
                tree.get(startNode).add(edge);
                tree.get(endNode).add(edge);

                // Mark as in the tree.
                components.union(startNode, endNode);
                numEdges++;
            }
        }
        return tree;
    }
}
