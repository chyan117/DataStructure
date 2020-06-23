package clab6;
import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic1() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}, {1, 1} };
        int[] expected = {2, 1};
        validate(grid, darts, expected);
    }
    @Test
    public void testBasic2() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 1, 1}};
        int[][] darts = {{1, 0}, {1, 1}, {3, 1} };
        int[] expected = {5, 0, 2};
        validate(grid, darts, expected);
    }
    @Test
    public void testBasic3() {
        int[][] grid = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        int[][] darts = {{2, 2}, {2, 0} };
        int[] expected = {0, 4};
        validate(grid, darts, expected);
    }
    @Test
    public void testBasic4() {
        int[][] grid = {{1, 1, 0, 1},
                        {1, 1, 1, 1,},
                        {0, 0, 0, 1 },
                        {1, 1, 1, 1}};
        int[][] darts = {{2, 2}, {2, 0}, {2,3}, {3,1} };
        int[] expected = {0, 0, 4, 1};
        validate(grid, darts, expected);
    }
    @Test
    public void testBasic5() {
        int[][] grid = {{1, 1, 0, 1},
                        {1, 1, 0, 1,},
                        {0, 0, 0, 1 },
                        {1, 1, 0, 1}};
        int[][] darts = {{2, 2}, {2, 0}, {2,3}, {3,1}, {1, 3} };
        int[] expected = {0, 0, 1, 0, 2};
        validate(grid, darts, expected);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        //asserArrayEquals!
        assertArrayEquals(expected, sol.popBubbles(darts));
    }
}
