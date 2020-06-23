/**
 * Created by hug.
 */
public class ExperimentHelper {
    private static int total_node;
    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        return  (int)(optimalAverageDepth(N)*N) ;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double random_insetion(int x){
        return optimalAverageDepth(x);
    }
    public static double random_deletion(int x){
       return optimalAverageDepth(x);
    }
    public static double optimalAverageDepth(int N) {
        total_node = N;
        if(N==0){
            return 0;
        }
        int square=1;
        double return_val=0;
        while(N-square>=0){
            return_val   =   return_val+square*( Math.log(square)/Math.log(2)    );
            N   =   N -square;
            square = square*2 ;
        }
        if(N>0){
            N   = (int) (N*  (Math.log( square)/Math.log(2) )  );
            return_val  =   return_val  +   N;
        }
        return_val  =   return_val/total_node;
        return return_val;
    }
}
