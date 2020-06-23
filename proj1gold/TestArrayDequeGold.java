import static org.junit.Assert.*;
import org.junit.Test;
/*the bug of student array is at "removeLast" Method !!*/
/*If my thought is wrong, feel free to contact me*/
public class TestArrayDequeGold{
    int actual;
    int expect;
    @Test
    public void Test1(){
        StudentArrayDeque<Integer> Studentans= new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ArrayDeSolu= new ArrayDequeSolution<Integer>();
        for (int i =0;i<1;i++ ){
            Studentans.addFirst(40);
            ArrayDeSolu.addFirst(40);
        }
        System.out.print("addFirst("+40+")\n");
        for (int i =0;i<1;i++ ){
            Studentans.addLast(50);
            ArrayDeSolu.addLast(50);
        }
        System.out.print("addLast("+50+")\n");
        for (int i =0;i<1;i++ ){
           actual = Studentans.removeFirst();
           expect = ArrayDeSolu.removeFirst();
        }
        assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                    + " not equal to " + expect + "!", expect, actual);
        System.out.print("removeFirst()\n");
    }

    @Test
    public void Test2() {
        StudentArrayDeque<Integer> Studentans = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ArrayDeSolu = new ArrayDequeSolution<Integer>();
        for (int i = 0; i < 100; i++) {
            /*It doesn't matter if we used the method of Integer.valueof() or not. */
            /*But I intentionally use it as an exercise. */
            Studentans.addFirst(Integer.valueOf(i));
            ArrayDeSolu.addFirst(Integer.valueOf(i));
        }
        for (int i = 0; i < 99; i++) {
            Integer C = Studentans.removeFirst();
            Integer D = ArrayDeSolu.removeFirst();
            assertEquals(D, C);
        }
        assertEquals(ArrayDeSolu.get(0), Studentans.get(0));
    }

    @Test
    public void Test3(){
        StudentArrayDeque<Integer> Studentans= new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ArrayDeSolu= new ArrayDequeSolution<Integer>();
        for (int i =0;i<100;i++ ){

            Studentans.addFirst(Integer.valueOf(i));
            ArrayDeSolu.addFirst(Integer.valueOf(i));
        }
        for (int i = 0; i<100; i++){
            assertEquals(ArrayDeSolu.get(i), Studentans.get(i));
        }
    }

    @Test
    public void Test4(){
        StudentArrayDeque<Integer> Studentans= new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ArrayDeSolu= new ArrayDequeSolution<Integer>();
        for (int i =0;i<100;i++ ){
            Studentans.addLast(Integer.valueOf(i));
            ArrayDeSolu.addLast(Integer.valueOf(i));
        }
        for (int i = 0; i<100; i++){
            assertEquals("your answer is wrong", ArrayDeSolu.get(i), Studentans.get(i));
        }
    }
    @Test
    public void Test5(){
        StudentArrayDeque<Integer> Studentans= new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ArrayDeSolu= new ArrayDequeSolution<Integer>();
        for (int i =0;i<100;i++ ){
            Studentans.addLast(i);
            ArrayDeSolu.addLast(i);
        }
        for (int i =0;i<99;i++ ){
            Integer actual = Studentans.removeLast();
            Integer expected = ArrayDeSolu.removeLast();
            int StudentSize = Studentans.size();
            int SolutionSize = Studentans.size();

            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }
        assertEquals(Studentans.get(0), ArrayDeSolu.get(0));
    }
}

