import java.util.HashSet;
import java.util.PriorityQueue;

public class MST {
    
    public static HashSet<Edge> applyKruskal(HashSet<Node> nodes, HashSet<Edge> edges){

        ConnectedComponents components = new ConnectedComponents(nodes);

        PriorityQueue<Edge> edgesMinHeap = new PriorityQueue<>(edges);

        HashSet<Edge> tree = new HashSet<>();

        while (tree.size() != nodes.size() - 1){

            Edge candidateEdge = edgesMinHeap.poll();
            Node startNode = candidateEdge.getStart();
            Node endNode = candidateEdge.getEnd();
            
            if (components.find(startNode) != components.find(endNode)){
                tree.add(candidateEdge);
                components.union(startNode, endNode);
            }

        }

        return tree;
    }


}
