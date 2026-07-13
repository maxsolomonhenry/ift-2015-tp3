import java.util.Comparator;

public class Edge implements Comparable<Edge>{

    private String label;
    private Node start;
    private Node end;
    private int weight;

    public Edge(String label, Node start, Node end, int weight) {
        this.label = label;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // For printing convenience.
    public String getLabel() {
        return this.label;
    }

    // Interface used mostly by comparator logic...    
    public Node getStart() {
        return this.start;
    }
    
    public Node getEnd() {
        return this.end;
    }
    
    public int getWeight() {
        return this.weight;
    }

    // Chain comparators, starting with weight, then start node, then end node.
    @Override
    public int compareTo(Edge other) {
        return Comparator.comparingInt(Edge::getWeight)
            .thenComparing(Edge::getStart)
            .thenComparing(Edge::getEnd)
            .compare(this, other);
    }

    @Override
    public String toString() {
        return String.format("Edge{label='%s',start=%s,end=%s,weight=%d}",
            this.label, this.start, this.end, this.weight
        );
    }
}
