import edu.princeton.cs.algs4.Queue;

import org.junit.Assert;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer>Pattern = new Queue<>();
        for(int i=0; i<5; i++){
            Pattern.enqueue(4-i);
        }
        Pattern.enqueue(0);
        Pattern= QuickSort.quickSort(Pattern);
        //After Sorted, it should be form the order 0 ,1 , 2, ....., 9.
        Assert.assertEquals(Pattern.dequeue(), (Integer) 0 );
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
    }
    @Test
    public void testMergeSort0() {
        Queue<Integer>Pattern = new Queue<>();
        for(int i=0; i<5; i++){
            Pattern.enqueue(4-i);
        }
        Pattern.enqueue(0);
        Pattern = MergeSort.mergeSort(Pattern);
        //After Sorted, it should be form the order 0 ,1 , 2, ....., 9.
        Assert.assertEquals(Pattern.dequeue(), (Integer) 0 );
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
        Assert.assertEquals(Pattern.dequeue(), (Integer) (4- Pattern.size()));
    }
    @Test
    public void testMergeSort() {
        Queue<Integer>Pattern = new Queue<>();
        for(int i=0; i<10; i++){
            Pattern.enqueue(9-i);
        }
        Pattern = MergeSort.mergeSort(Pattern);
        //After Sorted, it should be form the order 0 ,1 , 2, ....., 9.
        while(Pattern.size()>0){
            Assert.assertEquals(Pattern.dequeue(), (Integer) (9- Pattern.size()));
        }
    }
    @Test
    public void testQuickSort0() {
        Queue<Integer>Pattern = new Queue<>();
        for(int i=0; i<10; i++){
            Pattern.enqueue(9-i);
        }
        Pattern = QuickSort.quickSort(Pattern);
        //After Sorted, it should be form the order 0 ,1 , 2, ....., 9.
        while(Pattern.size()>0){
            Assert.assertEquals(Pattern.dequeue(), (Integer) (9- Pattern.size()));
        }
    }
    @Test
    public void issorted_or_not_Quick() {
        Queue<Double>Pattern = new Queue<>();
        for(int i=0; i<100_000; i++){
            double x = Math.random()*100_000;
            Pattern.enqueue(x);
        }
        Pattern = QuickSort.quickSort(Pattern);
        Assert.assertTrue(isSorted(Pattern));
    }
    @Test
    public void issorted_or_not_Merge() {
        Queue<Double>Pattern = new Queue<>();
        for(int i=0; i<100_000; i++){
            double x = Math.random()*100_000;
            Pattern.enqueue(x);
        }
        Pattern = MergeSort.mergeSort(Pattern);
        Assert.assertTrue(isSorted(Pattern));
    }
    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
