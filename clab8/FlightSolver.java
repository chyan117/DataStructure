import java.util.ArrayList;
import  java.util.PriorityQueue;
//using specific comparator
import java.util.Comparator;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    /*If the first*/
    class The_Comparator_start implements Comparator<Flight> {
        @Override
        public int compare(Flight F1, Flight F2)
        {
            return F1.startTime()- F2.startTime();
        }
    }
    /*If the first is*/
    class The_Comparator_end implements Comparator<Flight> {
        @Override
        public int compare(Flight F1, Flight F2)
        {
            return F1.endTime()- F2.endTime();
        }
    }
    //the default priority queue is min-heap.
    PriorityQueue<Flight> PQ_start = new PriorityQueue<Flight>(new The_Comparator_start()  );
    PriorityQueue<Flight> PQ_end = new PriorityQueue<Flight>(new The_Comparator_end());
    public FlightSolver(ArrayList<Flight> flights) {
        for(int i=0; i<flights.size(); i++) {
            PQ_start.add( flights.get(i));
            PQ_end.add(flights.get(i) );
        }
    }

    public int solve() {
        int temp = 0;
        int max_passenger = 0;
        while(!PQ_start.isEmpty()){
            /*peek is look at the top*/
            if(PQ_end.peek().endTime()>=PQ_start.peek().startTime()){
                /*poll is remove top*/
                temp = temp+PQ_start.poll().passengers();
                if(temp>max_passenger){
                    max_passenger = temp;
                }
            }
            else{
                temp = temp-PQ_end.poll().passengers();
            }
        }
        return max_passenger;
    }
}
