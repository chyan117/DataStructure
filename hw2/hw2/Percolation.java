package hw2;
/*The is_full method is apart from the percolation since we want to avoid backwash. */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private int[][] grid;
    private int row_num;
    private int column_num;
    private int open_num;
    WeightedQuickUnionUF b;//this one is to judge whether the plot is percolation. It does contain bottom.
    WeightedQuickUnionUF B;//this one is to judge whether the plot is full or not. It doesn't contain bottom.
    private int water=0;
    private int Bottom;
    private int[][] neighbering = {{0, 1},
                                    {0, -1},
                                    {1, 0},
                                    {-1, 0} };
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if(N<=0){
            throw new IllegalArgumentException("the NxN array's N should bigger than 0 !");
        }
        grid = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                grid[i][j]=0;
            }
        }
        row_num = N;
        column_num = N;
        open_num=0;
        Bottom = N*N+1;
        b = new WeightedQuickUnionUF(N*N+2);
        B = new WeightedQuickUnionUF(N*N+1);
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(row>row_num-1||col>column_num-1||row<0||col<0){
            throw new java.lang.IndexOutOfBoundsException();
        }else if( isOpen(row, col) ){
            return;
        }else{
            open_num = open_num+1;
            grid[row][col]   =   1;
            join2gether(row, col);
        }

    }
    //call this function only if the site is open
    private void join2gether(int row, int col){
        if(row==0){
            b.union(TwoDTo1D(row, col), water);
            B.union(TwoDTo1D(row, col), water);
        }
        if(row==row_num-1){
            b.union(TwoDTo1D(row, col), Bottom);
        }
        int row_join;
        int col_join;
        for(int[]i : neighbering){
            row_join = row+i[0];
            col_join = col+i[1];
            if(row_join<0||col_join<0||row_join>row_num-1||col_join>row_num-1||grid[row_join][col_join]!=1){
                continue;
            }
            b.union(TwoDTo1D(row_join, col_join), TwoDTo1D(row, col));
            B.union(TwoDTo1D(row_join, col_join), TwoDTo1D(row, col));
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row>row_num-1||col>column_num-1||row<0||col<0){
            throw new java.lang.IndexOutOfBoundsException();
        }else{
            if(grid[row][col]==0){
                return false;
            }
            if(grid[row][col]==1){
                return true;
            }
            return false;
        }
    }
    // is the site (row, col) full?
    //Use another disjoint set to judge.
    public boolean isFull(int row, int col) {
        if(row>row_num-1||col>column_num-1||row<0||col<0){
                throw new java.lang.IndexOutOfBoundsException();
        }else{
            if( B.connected( TwoDTo1D(row, col), water) ){
                return true;
            }
            return false;
        }
    }
    private int TwoDTo1D(int row, int col){
        return row*column_num+col+1;
    }
    // number of open sites
    public int numberOfOpenSites(){
        return open_num;
    }
    // does the system percolate?
    //Use one of the disjoint set to judge
    public boolean percolates(){
        return b.connected(water, Bottom);
    }
    public static void main(String[] args){}   // use for unit testing (not required, but keep this here for the autograder)
}