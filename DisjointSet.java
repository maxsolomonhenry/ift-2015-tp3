public class DisjointSet {
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