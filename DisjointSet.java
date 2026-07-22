public class DisjointSet {
    // A note that this class implements the canonincal disjoint set, as an 
    // array of ints (representing elements) and an array of ranks. It uses
    // path compression and merge by rank. 
    //
    // Because this code is very, _very_ standard, it resembles many other 
    // implementations online, such as those might one find on github, or
    // stack exchange, some of which we did consult when writing the code. 
    // I tried to leave comments throughout to make it clear that we have an 
    // understanding of the operations at hand.
    //
    // We decided to implement a bare DisjointSet class, and have this mapped 
    // onto by a `Node` class, just to keep it as general as possible. Thanks.
    
    private int[] parent;
    private int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            // Roots are their own parents (B&B convention).
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int val) {
        if (parent[val] != val) {
            // Recursive path compression.
            parent[val] = find(parent[val]);
        }
        return parent[val];
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        // If in the same set, nothing to merge. Return false.
        if (rootU == rootV) {
            return false;
        }

        // Merge the smaller rank tree into the larger rank one.
        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootV] < rank[rootU]) {
            parent[rootV] = rootU;
        } else {
            // If both the same rank, merge and increment the rank by 1.
            parent[rootV] = rootU;
            rank[rootU]++;
        }
        return true;
    }
}