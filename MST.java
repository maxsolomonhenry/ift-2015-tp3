import java.util.PriorityQueue;
import java.util.TreeSet;

public class MST {
    
    public static UndirectedGraph applyKruskal(UndirectedGraph graph){

        // Collect all edges into a set which removes duplicates.
        TreeSet<Edge> edges = graph.getUniqueEdges();

        ConnectedComponents components = new ConnectedComponents(graph.getNodes());
        PriorityQueue<Edge> edgesMinHeap = new PriorityQueue<>(edges);

        // Init the MST (should have same nodes as the input graph, by definition).
        UndirectedGraph tree = new UndirectedGraph(graph.getNodes());

        int numEdges = 0;
        while (numEdges < graph.getNumNodes() - 1){

            Edge edge = edgesMinHeap.poll();
            Node startNode = edge.getStart();
            Node endNode = edge.getEnd();
            
            if (components.find(startNode) != components.find(endNode)){
                // Add undirected edge.
                tree.addEdge(edge);

                // Mark as in the tree.
                components.union(startNode, endNode);

                // Increase edge count (only increments on condition).
                numEdges++;
            }
        }
        return tree;
    }
}
