import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        quicksort(bears, beds);
    }
    private List<Bear> sortedbears;
    private List<Bed> sortedbeds;
    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return sortedbears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return sortedbeds;
    }
    //Bear
    private void partitionsBear(List<Bear>unsorted , List<Bear>equal, List<Bear>greater, List<Bear>less, Bed pivot){
        for(Bear b: unsorted){
            if(b.compareTo(pivot)>0){
                greater.add(b);
            }
            else if(b.compareTo(pivot)==0){
                equal.add(b);
            }
            else{
                less.add(b);
            }
        }
    }
    //Bed
    private void partitionsBed(List<Bed>unsorted , List<Bed>equal, List<Bed>greater, List<Bed>less, Bear pivot){
        for(Bed b: unsorted){
            if(b.compareTo(pivot)>0){
                greater.add(b);
            }
            else if(b.compareTo(pivot)==0){
                equal.add(b);
            }
            else{
                less.add(b);
            }
        }
    }
    //Canatate
    private List<Bear> CanatateBear(List<Bear> a, List<Bear> b){
        List return_val= new ArrayList<Bear>();
        for(Bear ba: a){
            return_val.add(ba);
        }
        for(Bear bb: b){
            return_val.add(bb);
        }
        return return_val;
    }
    //Canatate
    private List<Bed> CanatateBed(List<Bed> a, List<Bed> b){
        List return_val= new ArrayList<Bear>();
        for(Bed ba: a){
            return_val.add(ba);
        }
        for(Bed bb: b){
            return_val.add(bb);
        }
        return return_val;
    }
    private Pair<List<Bear>, List<Bed> > quicksort(List<Bear> bears, List<Bed> beds){
        if(bears.size()<=1||beds.size()<=1){
            return new Pair(bears, beds);
        }
        Bed pivot = beds.get(0);
        List<Bear> LessBear = new ArrayList<>();
        List<Bear> LargerBear = new ArrayList<>();
        List<Bear> EqualBear = new ArrayList<>();
        partitionsBear(bears, EqualBear, LargerBear, LessBear, pivot);
        Bear pivotb = EqualBear.get(0);
        List<Bed> LessBed = new ArrayList<>();
        List<Bed> LargerBed = new ArrayList<>();
        List<Bed> EqualBed = new ArrayList<>();
        partitionsBed(beds, EqualBed, LargerBed, LessBed, pivotb);
        //After Partition
        Pair<List<Bear>, List<Bed> > LessPair = quicksort(LessBear, LessBed);
        List<Bear> lessbb = LessPair.first();
        List<Bed> lessbbb= LessPair.second();
        Pair<List<Bear>, List<Bed> > GreaterPair = quicksort(LargerBear, LargerBed);
        List<Bear> greaterbb = GreaterPair.first();
        List<Bed> greaterbbb= GreaterPair.second();
        sortedbears = CanatateBear(CanatateBear(lessbb, EqualBear),greaterbb );
        sortedbeds = CanatateBed(CanatateBed(lessbbb, EqualBed),greaterbbb );
        return new Pair(sortedbears, sortedbeds);
    }
}
