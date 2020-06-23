package clab6;
//it is mine
public class UnionFind {
    private int[] Set;
    private int total_index;
    private int[] compression;
    private int comp_num;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        Set = new int[n];
        compression = new int[n];
        for(int i=0; i<n; i++){
            Set[i] = -1;
        }
        total_index = n-1;
        comp_num = 0;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex>total_index){
            throw new IllegalArgumentException(vertex+ "is not a valid index. ");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -(Set[find(v1)])  ;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return Set[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if(find(v1) == find(v2 )){
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int i1 = sizeOf(v1);
        int i2 = sizeOf(v2);
        //when v1, v2 are in the same set.
        //nothing will do except compression.
        if(find(v1) == find(v2)){
            return;
        }
        //when the weight is equal
        if(i1 == i2){
            int temp =Set[find(v1)];
            Set[find(v1)]= find(v2);
            Set[find(v2)] = temp + Set[find(v2)];
            return;
        }
        //when the i1 is bigger
        if(i1>i2){
            int temp =Set[find(v2)];
            Set[find(v2)]= find(v1);
            Set[find(v1)] = temp + Set[find(v1)];
            return;
        }
        //when the i2 is bigger
        if(i2>i1){
            int temp =Set[find(v1)];
            Set[find(v1)]= find(v2);
            Set[find(v2)] = temp + Set[find(v2)];
            return;
        }

    }
    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if(parent(vertex) > -1 ){
            compression[comp_num] = vertex;
            comp_num = comp_num+1;
            return find(   parent(vertex)    );
        }
        /*for the root < -1*/
        else{
            for(int i=0; i< comp_num; i++){
                Set[ compression[i] ] = vertex;
            }
            comp_num = 0;
            return vertex;
        }
    }
}
