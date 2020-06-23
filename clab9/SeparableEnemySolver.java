import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class SeparableEnemySolver {
    ArrayList<String> s_Array;
    Graph g;
    HashSet<String> con_or_not;
    boolean[] mark;
    int[] edgeTo;
    ArrayList<Integer> edgeList;
    HashMap<String, Integer> map_for_name;
    int[] depth;
    int depth_now;
    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        //List all the labels in the graph.
        //Total is O(V+E).
        edgeList = new ArrayList<>();
        s_Array = new ArrayList<>();
        map_for_name = new HashMap<>();
        int i = 0;
        for(String c: g.labels()){
            map_for_name.put(c, i);
            s_Array.add(c);
            i= i+1;
        }
        mark = new boolean[s_Array.size()];
        edgeTo = new int[s_Array.size()];
        depth = new int[s_Array.size()];
        con_or_not = new HashSet<>(s_Array.size());
        depth_now = 0;
        //we have already record the depth
        //inspect each node's neighbor if there is odds' connection
        //iterate all the number of nodes
        //O(V)
        for(int s=0; s<map_for_name.size(); s++) {
            if(con_or_not.contains(s_Array.get(s)) ) {
                continue;
            }
            depth_now = 0;
            if( dfs(s_Array.get(s))==false ){
                return false;
            }
        }
        return true;
    }
    //O(E)
    //call node and record depth
    private boolean dfs(String s){
        //s_Array.add(s);
        con_or_not.add(s);
        depth_now = depth_now +1;
        mark[map_for_name.get(s)] = true;
        depth [map_for_name.get(s)] = depth_now;
        //Compare all the edges
        for(String t: g.neighbors(s)){
            if(mark[map_for_name.get(t)]==true){
                //Compare the edges that we ignore usually
                if( (depth[map_for_name.get(s)]%2==1 && depth[map_for_name.get(t)]%2==1)  ||(depth[map_for_name.get(s)]%2==0 && depth[map_for_name.get(t)]%2==0) ){
                    return false;
                }
                continue;
            }
            edgeTo[map_for_name.get(t)] = map_for_name.get(s);
            dfs(t);
            if( (depth[map_for_name.get(s)]%2==1 && depth[map_for_name.get(t)]%2==1)  ||(depth[map_for_name.get(s)]%2==0 && depth[map_for_name.get(t)]%2==0) ){
                return false;
            }
        }
        return true;
    }

    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */
    /**DFS -Method
     **The above is the color method (implemented bt BFS).
     *     public boolean isSeparable() {
     *         //List all the labels in the graph.
     *         //Total is O(V+E).
     *         edgeList = new ArrayList<>();
     *         s_Array = new ArrayList<>();
     *         map_for_name = new HashMap<>();
     *         int i = 0;
     *         for(String c: g.labels()){
     *             map_for_name.put(c, i);
     *             s_Array.add(c);
     *             i= i+1;
     *         }
     *         mark = new boolean[s_Array.size()];
     *         edgeTo = new int[s_Array.size()];
     *         depth = new int[s_Array.size()];
     *         con_or_not = new HashSet<>(s_Array.size());
     *         depth_now = 0;
     *         //we have already record the depth
     *         //inspect each node's neighbor if there is odds' connection
     *         //iterate all the number of nodes
     *         //O(V)
     *         for(int s=0; s<map_for_name.size(); s++) {
     *             if(con_or_not.contains(s_Array.get(s)) ) {
     *                 continue;
     *             }
     *             depth_now = 0;
     *             if( dfs(s_Array.get(s))==false ){
     *                 return false;
     *             }
     *         }
     *         return true;
     *     }
     *     //O(E)
     *     //call node and record depth
     *     private boolean dfs(String s){
     *         //s_Array.add(s);
     *         con_or_not.add(s);
     *         depth_now = depth_now +1;
     *         mark[map_for_name.get(s)] = true;
     *         depth [map_for_name.get(s)] = depth_now;
     *         //Compare all the edges
     *         for(String t: g.neighbors(s)){
     *             if(mark[map_for_name.get(t)]==true){
     *                 //Compare the edges that we ignore usually
     *                 if( (depth[map_for_name.get(s)]%2==1 && depth[map_for_name.get(t)]%2==1)  ||(depth[map_for_name.get(s)]%2==0 && depth[map_for_name.get(t)]%2==0) ){
     *                     return false;
     *                 }
     *                 continue;
     *             }
     *             edgeTo[map_for_name.get(t)] = map_for_name.get(s);
     *             dfs(t);
     *             if( (depth[map_for_name.get(s)]%2==1 && depth[map_for_name.get(t)]%2==1)  ||(depth[map_for_name.get(s)]%2==0 && depth[map_for_name.get(t)]%2==0) ){
     *                 return false;
     *             }
     *         }
     *         return true;
     *     }
    * */
}
