package clab6;


import clab6.UnionFind;
public class BubbleGrid {
    private int[][] grid;
    private int row_num;
    private int column_num;
    private int ceiling=0;
    private int row_ad;
    private int colum_ad;
    int total_size_on_ceiling;
    int new_total_size_on_ceiling;
    //the neighboring of the point.
    private int[][] neighbor= {{1, 0},
                                {0, 1},
                                {-1, 0},
                                {0, -1} };
    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid   =   grid;
        this.row_num    =   grid.length;
        this.column_num =   grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */

    public int[] popBubbles(int[][] darts) {
        int[] return_val = new int[darts.length];
        int i = 0;
        UnionFind UF    =   new UnionFind(row_num*column_num+1);
        for(int row_f=0; row_f< row_num; row_f++){
            for(int column_f=0; column_f< column_num; column_f++){
                if(grid[row_f][column_f]==1) {
                    UnionConnected(row_f, column_f, grid, UF);
                }
            }
        }
        total_size_on_ceiling   = UF.sizeOf(ceiling);
        for(int[]dart:  darts ){
            UnionFind UF1    =   new UnionFind(row_num*column_num+1);
            if(grid[dart[0]][dart[1]]  ==   0){
                return_val[i]   =   0;
                i = i+1;
                continue;
            }
            if(grid[dart[0]][dart[1]]  ==   1)
            grid[dart[0]][dart[1]]  =   2;
            for(int row_f=0; row_f< row_num; row_f++){
                for(int column_f=0; column_f< column_num; column_f++){
                    if(grid[row_f][column_f]==1) {
                        UnionConnected(row_f, column_f, grid, UF1);
                    }
                }
            }
            //turn the changed grid into the original one.
            if(grid[dart[0]][dart[1]]  ==   2) {
                grid[dart[0]][dart[1]] = 1;
            }
            //after being impacted
            new_total_size_on_ceiling   =   UF1.sizeOf(ceiling);
            //it hits at 1 and that 1 is connected to the ceiling. The minus one in the equation means the pop's Bubbles.
            int out_element = total_size_on_ceiling - new_total_size_on_ceiling - 1;
            //if it hits at 1, but that 1 is not connected to ceiling
            if(total_size_on_ceiling == new_total_size_on_ceiling){
                out_element= 0;
            }
            return_val[i]   =   out_element;
            i = i+1;
        }
        return return_val;
    }
    //if there is a bubble on the spot, we will call this function and connect them together
    private void UnionConnected(int r, int c, int[][] grid, UnionFind UF){
        //if the point is at the topmost of the grid, We will count is as ceiling part.
        if(r==0){
            UF.union( TwoDTO1D(r, c), ceiling);
        }
        for(int[] nei : neighbor){
            row_ad  =   r+ nei[0];
            colum_ad    = c+ nei[1];
            //if it wants to be added into the ceiling part, it bust be in the valid position and neighboring an 1.  
            if( row_ad>=row_num||   colum_ad>=column_num||      row_ad<0||  colum_ad<0|| grid[row_ad][colum_ad]!=1 ){
                continue;
            }
            UF.union(TwoDTO1D(row_ad, colum_ad), TwoDTO1D(r, c));
        }
    }

    private int TwoDTO1D(int row, int column){
        return row*column_num+column+1;
    }
}
