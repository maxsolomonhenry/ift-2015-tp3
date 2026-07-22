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
        TreeMap<Node, TreeSet<Edge>> graph = new TreeMap<>();

        // Read input.
        for (String line : lines) {
            switch (state) {
                case READ_NODES -> {
                    if (line.equals("---")){
                        state = State.READ_EDGES;
                        continue;
                    }

                    // Make an empty entry for this node.
                    graph.put(new Node(line.trim()), new TreeSet<>());
                }
                case READ_EDGES -> {
                    if (line.equals("---")){
                        state = State.END;
                        continue;
                    }
                    Edge edge = parseEdge(line);
                    if (!graph.containsKey(edge.getStart()) || 
                        !graph.containsKey(edge.getEnd())){
                        // Skip this edge, it doesn't belong in this graph.
                        continue;
                    }

                    // Store twice because this is an undirected graph.
                    graph.get(edge.getStart()).add(edge);
                    graph.get(edge.getEnd()).add(edge);
                }
                case END -> {}
            }
        }

        TreeMap<Node, TreeSet<Edge>> mst = MST.applyKruskal(graph);
        StringBuilder result = new StringBuilder();

        // Add nodes in alphabetical order.
        for (Node node : mst.keySet()) {
            result.append(node.getName() + "\n");
        }

        // Collect edges.
        TreeSet<Edge> edges = new TreeSet<>();
        for (TreeSet<Edge> outgoing : mst.values()) {
            edges.addAll(outgoing);
        }
        ArrayList<Edge> list = new ArrayList<>(edges);
        list.sort(Edge.BY_NODE);

        // Add edges in sorted order by label then nodes. Also accumulate weight.
        int totalWeight = 0;
        for (Edge edge : list) {
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

    public static Edge parseEdge(String line) {
        String[] parts = line.trim().split("\\s+");

        return new Edge(
            parts[0],                   // label
            new Node(parts[2]),         // start
            new Node(parts[3]),         // end
            Integer.parseInt(parts[4])  // weight
        );
    }
}