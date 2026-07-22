import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class MST {
    
    public static TreeSet<Edge> applyKruskal(TreeSet<Node> nodes, ArrayList<Edge> edges){

        ConnectedComponents components = new ConnectedComponents(nodes);

        PriorityQueue<Edge> edgesMinHeap = new PriorityQueue<>(edges);

        if (Tp3.DEBUG){
            System.out.println("\n==============\nEDGES (SORTED)\n==============");
            while (!edgesMinHeap.isEmpty()) {
                System.out.println(edgesMinHeap.poll());
            }
        }

        TreeSet<Edge> tree = new TreeSet<>(Edge.COMPARE_BY_START_END_WEIGHT);

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
