package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus{
    @Test
    public void testBasicFucntion(){
        Clorus c = new Clorus(1);
        assertEquals(1, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(0.97, c.energy(), 0.01);
        c.move();
        assertEquals(0.94, c.energy(), 0.01);
        c.stay();
        assertEquals(0.93, c.energy(), 0.01);
        c.stay();
        assertEquals(0.92, c.energy(), 0.01);
        c.replicate();
        assertEquals(0.46, c.energy(), 0.01);
    }


    @Test
    public void testReplicate() {
        Clorus p = new Clorus(2);
        p.replicate();
        assertEquals(1.0, p.energy(), 0.01);
        p.replicate();
        assertEquals(0.5, p.energy(), 0.01);
        p.replicate();
        assertEquals(0.25, p.energy(), 0.01);
        p.replicate();
        assertEquals(0.125, p.energy(), 0.01);
    }

    @Test
    public void testStay() {
        // Neither empty adjacent spaces nor Plip around Clorus; stay.
        Clorus c = new Clorus(0.8);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
    }
    @Test
    public void testPlipsaround(){
        Clorus c = new Clorus(0.8);
        Plip p = new Plip(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, p);
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        assertEquals(expected, actual);
    }
    @Test
    public  void testReplicated(){
        Clorus c = new Clorus(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Clorus());
        surrounded.put(Direction.BOTTOM, new Clorus());
        surrounded.put(Direction.LEFT, new Clorus());
        surrounded.put(Direction.RIGHT, new Empty());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        assertEquals(expected, actual);
    }
    @Test
    public void testMove() {
        // Neither empty adjacent spaces nor Plip around Clorus; stay.
        Clorus c = new Clorus(0.8);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Empty());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.MOVE,Direction.RIGHT );
        assertEquals(expected, actual);
    }
}