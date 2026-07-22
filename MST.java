import java.util.ArrayList;
import java.util.PriorityQueue;

public class MST {
    
    public static ArrayList<Edge> applyKruskal(ArrayList<Node> nodes, ArrayList<Edge> edges){

        ConnectedComponents components = new ConnectedComponents(nodes);
        PriorityQueue<Edge> edgesMinHeap = new PriorityQueue<>(edges);
        ArrayList<Edge> tree = new ArrayList<>();

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
