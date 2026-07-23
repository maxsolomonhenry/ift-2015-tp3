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

    // Must implement against `Object` type for this to work in HashMaps.
    @Override
    public boolean equals(Object other) {
        // If same address, it's the same thing.
        if (this == other) return true;

        // If it's not a Node, it must be false.
        if (!(other instanceof Node)) return false;

        // Compare names (the only property).
        return this.name.equals(((Node) other).getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
