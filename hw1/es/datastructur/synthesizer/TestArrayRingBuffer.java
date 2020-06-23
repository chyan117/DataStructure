package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(7);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        System.out.println(arb.isFull());
        System.out.println(arb.fillCount() );
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        System.out.println(arb.fillCount() );
        arb.dequeue();
        System.out.print(arb.isEmpty() );
    }
    @Test
    public void someTest1() {
        ArrayRingBuffer arb = new ArrayRingBuffer(7);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        ArrayRingBuffer arb1 = new ArrayRingBuffer(7);
        arb1.enqueue(3);
        arb1.enqueue(4);
        arb1.enqueue(5);
        arb1.enqueue(6);
        arb1.enqueue(7);
        arb1.enqueue(8);
        arb1.enqueue(9);
        boolean equal_result    =  arb.equals(arb1);
        assertTrue(equal_result);
        boolean equal_check_non_destructive    =  arb.equals(arb1);
        assertTrue(equal_check_non_destructive);
        arb1.dequeue();
        arb1.dequeue();
        System.out.print(arb1.fillCount());
    }
    @Test
    public void someTest3() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
        assertEquals((Integer) 1, arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals((Integer) 2, arb.peek());
        arb.enqueue(4);
        assertTrue(arb.isFull());
    }
    @Test
    public void someTest4() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
        assertEquals((Integer) 1, arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals((Integer) 2, arb.peek());
        arb.enqueue(4);
        assertTrue(arb.isFull());
        ArrayRingBuffer<Integer> sample = new ArrayRingBuffer<>(3);
        sample.enqueue(2);
        sample.enqueue(3);
        sample.enqueue(4);
        assertTrue(arb.equals(sample));
        sample.dequeue();
        assertTrue( !arb.equals(sample));
    }
}
