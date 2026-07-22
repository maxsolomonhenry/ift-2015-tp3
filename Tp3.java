import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class Tp3 {

    // Debugging flag.
    static final boolean DEBUG = true;

    public enum State {
        READ_NODES, READ_EDGES, END
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);

        // Read input.
        List<String> lines = Files.readAllLines(inputPath);
        State state = State.READ_NODES;

        // Build a list of nodes.
        ArrayList<Node> nodes = new ArrayList<>();

        // Build edges as an array first, then heapify (fixheap) for best complexity.
        ArrayList<Edge> edges = new ArrayList<>();

        // Read input.
        for (String line : lines) {
            switch (state) {
                case READ_NODES -> {
                    if (line.equals("---")){
                        state = State.READ_EDGES;
                        continue;
                    }   
                    nodes.add(new Node(line.trim()));
                }
                case READ_EDGES -> {
                    if (line.equals("---")){
                        state = State.END;
                        continue;
                    }
                    edges.add(parseEdge(line));
                }
                case END -> {}
            }
        }

        ArrayList<Edge> mst = MST.applyKruskal(nodes, edges);

        // Sort for pretty printing.
        mst.sort(Edge.BY_NODE);
        nodes.sort(null);
        
        StringBuilder result = new StringBuilder();

        // Add nodes in alphabetical order.
        for (Node node : nodes) {
            result.append(node.getName() + "\n");
        }

        // Add edges in sorted order by label then nodes. Also accumulate total
        // weight.
        int totalWeight = 0;
        for (Edge edge : mst) {
            result.append(
                String.format("%s\t%s\t%s\t%d\n",
                    edge.getLabel(), 
                    edge.getStart().getName(),
                    edge.getEnd().getName(),
                    edge.getWeight()
                )
            );
            totalWeight += edge.getWeight();
        }

        // Print weight in required formatting.
        result.append("---\n");
        result.append(String.valueOf(totalWeight)).append("\n");

        // Debugging for us as we go...
        if (DEBUG) {
            System.out.println("\n=====\nNODES\n=====");
            for (Node node : nodes) {
                System.out.println(node);
            }
            
            System.out.println("\n=====\nEDGES\n=====");
            for (Edge edge: edges) {
                System.out.println(edge);
            }
            
            System.out.println("\n=====\n MST \n=====");
            for (Edge edge: mst) {
                System.out.println(edge);
            }

            System.out.println("\n=====\nSAVED\n=====");
            System.out.print(result);
        }

        // Write output.
        Files.writeString(outputPath, result);

    }

    public static Edge parseEdge(String line) {
        String[] parts = line.trim().split("\\s+");

        // Impose start/end nodes in alphabetical order.
        String start = parts[2];
        String end = parts[3];
        if (start.compareTo(end) > 0) {
            start = parts[3];
            end = parts[2];
        }

        return new Edge(
            parts[0],                   // label
            new Node(start),            // start
            new Node(end),              // end
            Integer.parseInt(parts[4])  // weight
        );
    }
}