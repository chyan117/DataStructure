package es.datastructur.synthesizer;
import java.util.Iterator;
public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int Buffer_num;
    private int adjust_F_ring_position(int first){
        if(first+1==Buffer_num){
            first   =   0;
        }else{
            first = first   +   1;
        }
        return first;
    }
    private int adjust_L_ring_position(int last){
        if(last+1==Buffer_num){
            last   =   0;
        }else{
            last = last   +   1;
        }
        return last;
    }
    private class ArrayRB implements Iterator<T>{
        private int seer;
        private int size;
        public ArrayRB(){
            seer = first;
            size = 0;
        }

        @Override
        public boolean hasNext() {
            if (size    <   fillCount) {
                return true;
            }
            return false;
        }
        @Override
        public T next(){
            T  return_val =   rb[seer];
            seer    =   adjust_F_ring_position(seer);
            size    =   size    +   1;
            return return_val;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayRB();
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        Buffer_num  =   capacity;
        rb  =(T[]) new Object[Buffer_num];
        fillCount = 0;
        first   =   0;
        last    =   0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if(fillCount==Buffer_num){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last]   =   x;
        last    =   adjust_L_ring_position(last);
        fillCount   =   fillCount+1;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if(fillCount==0){
            throw new RuntimeException("Ring buffer underflow");
        }
        T return_val    =   rb[first];
        rb[first]   =   null;
        first   =   adjust_F_ring_position(first);
        fillCount   =   fillCount-1;
        return return_val;
    }

    @Override
    public boolean equals(Object o){
        int method_first = first;
        if(this.getClass()!=o.getClass()){
            return false;
        }
        if(this==o){
            return true;
        }
        if(o==null){
            return false;
        }
        ArrayRingBuffer<T>  ob=    (ArrayRingBuffer<T>)o;
        if(this.capacity()!=ob.capacity()){
            return false;
        }
        if(this.fillCount()!=ob.fillCount()){
            return false;
        }
        for(T i :   ob){
            if(!rb[method_first].equals(i)){
                return false;
            }
            method_first    =   adjust_F_ring_position(method_first);
        }
        return true;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if(fillCount==0){
            throw new RuntimeException("Ring buffer underflow");
        }
        T return_val_p  =   rb[first];
        return return_val_p;
    }
    @Override
    public int capacity(){
        return Buffer_num;
    }     // return size of the buffer
    @Override
    public int fillCount(){
        return fillCount;
    }    // return number of items currently in the buffer
}

