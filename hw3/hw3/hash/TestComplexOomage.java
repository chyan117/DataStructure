package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */
    //Let all the deadlyList element overflow
    //By the hind, I discovered that x*256**4=0 in hash code(x is constant number).
    //e.g. 50000, 20000, 70000 's hash codes are also equal to 0.
    //another observation is that is the integer over some bound, and multiple it with fix number, it will stay the same;
    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        int N = 100;
        //given by hint, it will overflow when it overs 256**3;
        for (int i = 0; i < N; i += 1) {
            //int b = StdRandom.uniform(0, 10);
            //b = b*5;
            int b=5;
            ArrayList<Integer> params = new ArrayList<>(b);
            for (int j = 0; j < b; j += 1) {
                int r = StdRandom.uniform(1,100);
                if(j%5==0){
                    params.add(r);
                }
                else{
                    params.add(0);
                }
            }
            ComplexOomage c = new ComplexOomage(params);
            deadlyList.add(c);
            //System.out.println(c.hashCode());
        }
        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
