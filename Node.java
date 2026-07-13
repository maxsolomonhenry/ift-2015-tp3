public class Node implements Comparable<Node>{

    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // To permit keying on node name.
    @Override
    public int compareTo(Node other) {
        return this.name.compareTo(other.name);
    }

    // Printable name for debugging.
    @Override
    public String toString() {
        return "Node{name='" + name + "'}";
    }
}
