package bearmaps.hw4;
import bearmaps.proj2ab.*;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;
public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    //timeout passed in second
    private int deque_number;
    Vertex x;
    List<Vertex> answer ;
    Vertex source;
    Vertex end;
    HashMap<Vertex, Double> disTo;
    HashMap<Vertex, Vertex>  EdgeTo;
    ArrayHeapMinPQ<Vertex> Heap;
    HashSet<Vertex> mark;
    SolverOutcome outcome;
    ArrayList<Vertex> RecordDiscardItem;
    double timeSpent;
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        disTo = new HashMap<>();
        EdgeTo = new HashMap<>();
        RecordDiscardItem = new ArrayList<>();
        source = start;
        this.end = end;
        mark = new HashSet<>();
        Heap= new ArrayHeapMinPQ<>();
        //the above are inits.
        Heap.add(source, 0);
        disTo.put(source, 0.0);
        EdgeTo.put(source, null);
        deque_number =0;
        int j =0;
        //will return a list of WeightedEdge.
        //When relaxing an edge,
        // if the relaxation is successful and the target vertex is not in the PQ, add it.
        while(Heap.size()!=0 ){
            if(source.equals(end)){
                break;
            }
            //Find the edges
            mark.add(Heap.getSmallest());
            RecordDiscardItem.add(j,Heap.getSmallest() );
            j = j+1;
            if(Heap.getSmallest().equals(end)){
                break;
            }
            List<WeightedEdge<Vertex>> Edge= input.neighbors(Heap.removeSmallest());
            deque_number = deque_number+1;
            //Relax
            //Find the smallest one
            for(WeightedEdge<Vertex> ed: Edge){
                if( (!disTo.containsKey(ed.to()) ) && (!mark.contains(ed.to()))) {
                    disTo.put(ed.to(), Double.POSITIVE_INFINITY);
                }
                mark.add(ed.to());
                //if succeed
                if(disTo.get(ed.from())+ed.weight()<disTo.get(ed.to())){
                    EdgeTo.put(ed.to(), ed.from());
                    disTo.put(ed.to(), ed.weight()+ disTo.get(ed.from()));
                    if(Heap.contains(ed.to())){
                        Heap.changePriority(ed.to(), disTo.get(ed.from())+ed.weight()+input.estimatedDistanceToGoal(ed.to(), end));
                    }
                    else{
                        Heap.add(ed.to(), disTo.get(ed.from())+ed.weight()+input.estimatedDistanceToGoal(ed.to(), end));
                    }
                }
            }
            timeSpent = sw.elapsedTime();
            if(timeSpent>=timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
        }
        if(!EdgeTo.containsKey(end)){
            outcome = SolverOutcome.UNSOLVABLE;
        }
        else if(source.equals(end)){
            outcome = SolverOutcome.SOLVED;
            answer = new ArrayList<>();
            answer.add(source);
        }
        else {
            x = EdgeTo.get(end);
            outcome = SolverOutcome.SOLVED;
            answer = new ArrayList<>();
            while(!EdgeTo.get(end).equals(source)) {
                answer.add(end);
                end = EdgeTo.get(end);
            }
            answer.add(end);
            answer.add(source);
            Collections.reverse(answer);
        }
    }
    public SolverOutcome outcome(){
        return outcome;
    }
    //A list of vertices corresponding to a solution.
    // Should be empty if result was TIMEOUT or UNSOLVABLE.
    public List<Vertex> solution(){
        if(outcome ==SolverOutcome.TIMEOUT ||outcome ==SolverOutcome.UNSOLVABLE){
            return new ArrayList<>();
        }
        else{
            return answer;
        }
    }
    //The total weight of the given solution,
    // taking into account edge weights. Should be 0 if result was TIMEOUT or UNSOLVABLE.
    public double solutionWeight(){
        if(outcome ==SolverOutcome.TIMEOUT ||outcome ==SolverOutcome.UNSOLVABLE){
            return 0;
        }
        else{
            return disTo.get(end);
        }
    }
    //The total number of priority queue dequeue operations.
    public int numStatesExplored(){
        return deque_number;
    }
    //The total time spent in seconds by the constructor.
    public double explorationTime(){
        return timeSpent;
    }
}