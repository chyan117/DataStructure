package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private Percolation p;
    private double[] statics;
    private int static_open_times;
    private int total;
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N<=0||T<=0){
            throw new IllegalArgumentException("N or T should be bigger than 0 ");
        }
        statics = new double[T];
        total = N*N;
        for(int i=0; i<T; i++){
            p = pf.make(N);
            static_open_times = 0;
            while(!p.percolates()){
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                if(!p.isOpen(row, col)) {
                    p.open(row, col);
                    static_open_times = static_open_times + 1;
                }
            }
            statics[i] = (double)static_open_times/total;
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(statics) ;
    }
    // sample standard deviation of percolation threshold
    public double stddev()  {
        return StdStats.stddev(statics) ;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() -  1.96*stddev()/Math.sqrt(statics.length) ;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh()  {
        return mean() + 1.96*stddev()/Math.sqrt(statics.length);
    }
}
