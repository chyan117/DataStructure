package creatures;
import huglife.*;

import javax.management.loading.PrivateMLet;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;
import static huglife.HugLifeUtils.randomEntry;
public class Clorus extends Creature{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e ;
    }
    public Clorus(){
        this(3);
    }
    public void move() {
        energy = energy - 0.03;
    }
    public void stay() {
        energy = energy - 0.01 ;
    }
    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }
    @Override
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    public Clorus replicate() {
        energy = energy/2;
        Clorus c = new Clorus(energy/2);
        return c;
    }
    public double energy(){
        return energy;
    }

    public Action chooseAction(Map<Direction/*it's the key set*/, Occupant/*it's the value*/> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> PlipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        for(Direction i: neighbors.keySet() ){
            /*check if there is empty space*/
            //When we compare String, we must use .equals to do that!!
            if( (neighbors.get(i).name().equals("empty")   )   ){
                emptyNeighbors.addLast(i);
            }
            if( (neighbors.get(i).name().equals("plip")   )   ){
                PlipNeighbors.addLast(i);
                anyPlip = true;
            }
        }
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        if(emptyNeighbors.size()==0 && PlipNeighbors.size()==0){
            return new Action(Action.ActionType.STAY);
        }
        //see if there is Plip!
        // Rule 2
        if(anyPlip){
            return new Action(Action.ActionType.ATTACK, randomEntry(PlipNeighbors));
        }
        //if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        // Rule 3
        if(energy>=1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }
        //the Clorus will MOVE to a random empty square.
        else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
            //randomEntry(emptyNeighbors)
        }
    }
}