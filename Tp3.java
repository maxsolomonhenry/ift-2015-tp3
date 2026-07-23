import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
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

        // Init graph for storing the nodes and edges.
        UndirectedGraph graph = new UndirectedGraph();

        // Read input.
        for (String line : lines) {
            switch (state) {
                case READ_NODES -> {
                    if (line.equals("---")){
                        state = State.READ_EDGES;
                        continue;
                    }

                    // Make an empty entry for this node.
                    graph.addNode(new Node(line.trim()));
                }
                case READ_EDGES -> {
                    if (line.equals("---")){
                        state = State.END;
                        continue;
                    }
                    Edge edge = parseEdge(line);

                    // Store edge (stores duplicates and checks for valid nodes).
                    graph.addEdge(edge);
                }
                case END -> {}
            }
        }

        UndirectedGraph mst = MST.applyKruskal(graph);
        StringBuilder result = new StringBuilder();

        // Add nodes in alphabetical order.
        for (Node node : mst.getNodes()) {
            result.append(node.getName() + "\n");
        }

        // Collect edges and sort.
        ArrayList<Edge> edges = new ArrayList<>(mst.getUniqueEdges());
        edges.sort(Edge.BY_NODE);

        // Add edges in sorted order by label then nodes. Also accumulate weight.
        int totalWeight = 0;
        for (Edge edge : edges) {
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
            System.out.println("\n=====\nSAVED\n=====");
            System.out.print(result);
        }

        // Write output.
        Files.writeString(outputPath, result);

    }

    // Helper function to parse edges from the text input.
    public static Edge parseEdge(String line) {
        String[] parts = line.trim().split("\\s+");

        // Impose start/end nodes in alphabetical order. This helps achieve the 
        // desired sorting for the output, though we note that this would not
        // generalize to directed graphs.
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