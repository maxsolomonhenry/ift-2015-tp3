import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;

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

        // Debugging (maybe not the best data structure for this).
        List<Node> nodes = new LinkedList<>();

        // Build edges as an array first, then heapify (fixheap) for best complexity.
        List<Edge> edges = new ArrayList<>();

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
                case END -> { break; }
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);

        if (DEBUG) {
            System.out.println("\n=====\nNODES\n=====");
            for (Node node : nodes) {
                System.out.println(node);
            }
    
            System.out.println("\n=====\nEDGES\n=====");
            for (Edge edge: edges) {
                System.out.println(edge);
            }

            System.out.println("\n==============\nEDGES (SORTED)\n==============");
            while (!pq.isEmpty()) {
                System.out.println(pq.poll());
            }
        }

        String result = "[TO BE IMPLEMENTED]";

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