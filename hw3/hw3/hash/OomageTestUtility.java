package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] B_num = new int[M];//initial:0
        int bucketNum;
        int N = oomages.size();
        double lower_bound = N/50;
        double upper_bound = N/2.5;
        for(Oomage o: oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            B_num[bucketNum]= B_num[bucketNum]+1;
        }
        for(int i: B_num){
            double com = (double)i;
            if(com>upper_bound||com<lower_bound){
                return false;
            }
        }
        return true;
    }
}
