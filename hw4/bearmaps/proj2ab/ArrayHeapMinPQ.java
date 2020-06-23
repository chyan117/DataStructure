package bearmaps.proj2ab;
import java.util.*;
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private int size;
    public Node[] MytreeStruct;
    private HashMap<T, Node> TreeT;
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority){
        if(contains(item)){
            throw  new IllegalArgumentException();
        }
        size = size + 1;
        Node new_node = new Node(priority, item, size);
        /*It is Root */
        if(size==0) {
            MytreeStruct[size] = new Node(priority, item, size);;
            TreeT.put(item, new_node);
            return;
        }
        /*Other than Root*/
        if(size==MytreeStruct.length){
            resize(size*2);
        }
        MytreeStruct[size] = new_node;
        TreeT.put(item, new_node);
        swim(size);
    }
    private void resize(int newsize){
        Node[] NewOne = new ArrayHeapMinPQ.Node[newsize];
        System.arraycopy(MytreeStruct, 0, NewOne, 0,size);
        MytreeStruct = NewOne;
    }
    private boolean is_empty(){
        return size==0;
    }
    /* Returns true if the PQ contains the given item. */
    /*Must take O(logn)*/
    public boolean contains(T item){
        return TreeT.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        if(is_empty()){
            throw new NoSuchElementException();
        }
        return MytreeStruct[1].item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest(){
        if(is_empty()){
            throw new NoSuchElementException();
        }
        TreeT.remove(MytreeStruct[1].item);
        T return_val = MytreeStruct[1].item;
        TreeT.remove(MytreeStruct[size].item);
        MytreeStruct[size].Setindex(1);
        TreeT.put(MytreeStruct[size].item,MytreeStruct[size] );
        if(size==1){
            TreeT.remove(MytreeStruct[size].item);
        }
        MytreeStruct[1] = MytreeStruct[size];
        MytreeStruct[size] = null;
        size = size -1;
        sink(1);
        size_down();
        return return_val;
    }
    private void size_down(){
        if(size<5){
            return;
        }
        if((float)(size()+1)/(float)MytreeStruct.length<0.5) {
            Node[] newStruct = new ArrayHeapMinPQ.Node[MytreeStruct.length / 2];//4
            System.arraycopy(MytreeStruct, 0, newStruct, 0, MytreeStruct.length / 2);
            MytreeStruct = newStruct;
        }
        return;
    }
    /* Returns the number of items in the PQ. */
    @Override
    public int size(){
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    /*O(logN)*/
    @Override
    public void changePriority(T item, double priority){
        if(!contains(item)||is_empty()){
            throw new NoSuchElementException();
        }
        Node temp = TreeT.get(item);
        TreeT.remove(item);
        temp.SetPriority(priority);
        TreeT.put(temp.item, temp);
        //If it is the root
        if(TreeT.get(item).indexOf==1){
            sink(1);
            return;
        }
        int the_index = TreeT.get(item).get_index();
        if(MytreeStruct[the_index].priority<MytreeStruct[parent(the_index)].priority){
            swim(the_index);
            return;
        }
        sink(the_index);
        return;
    }
    public ArrayHeapMinPQ(){
        MytreeStruct = new ArrayHeapMinPQ.Node[5];
        MytreeStruct[0]=null;
        size = 0;
        TreeT = new HashMap<>();
    }
    public class Node{
        double priority;
        T item;
        int indexOf;
        Node(double priority, T item, int index){
            this.priority = priority;
            this.item = item;
            indexOf = index;
        }
        Node(Node N){
            this.priority = N.priority;
            this.item = N.item;
            this.indexOf = N.indexOf;
        }
        void SetPriority(double priority){
            this.priority = priority;
        }
        void Setindex(int i){
            this.indexOf = i;
        }
        int get_index(){
            return indexOf;
        }
        T getItem(){
            return item;
        }
    }
    private int parent(int i){
        return i/2;
    }
    private int LeftChild(int i){
        return i*2;
    }
    private int RightChild(int i){
        return i*2+1;
    }
    private void swim(int i){
        if(i>1){
            if(MytreeStruct[i].priority<= MytreeStruct[parent(i)].priority){
                Swap(i,parent(i) );
                swim(parent(i));
            }
            return;
        }
    }
    ////////////
    //Sink need to check whether need to sink
    private void sink(int i){
        //Do not have Children
        if(  (RightChild(i)>size()&&LeftChild(i)>size())   ){
            return;
        }
        //Only have left Children
        else if(RightChild(i)>size()&&LeftChild(i)<=size()){
            if(MytreeStruct[LeftChild(i)].priority<MytreeStruct[i].priority){
                Swap(i, LeftChild(i));
                sink(LeftChild(i));
                return;
            }
        }
        //Have two child
        else {
            Node LeftChild = MytreeStruct[LeftChild(i)];
            Node RightChild = MytreeStruct[RightChild(i)];
            if( (MytreeStruct[LeftChild(i)].priority<=MytreeStruct[i].priority) || (MytreeStruct[RightChild(i)].priority<=MytreeStruct[i].priority)) {
                if (LeftChild.priority <= RightChild.priority) {
                    Swap(i, LeftChild(i));
                    sink(LeftChild(i));
                    LeftChild = null;
                    RightChild = null;
                    return;
                }
                if (RightChild.priority <= LeftChild.priority) {
                    Swap(i, RightChild(i));
                    sink(RightChild(i));
                    LeftChild = null;
                    RightChild = null;
                    return;
                }
            }
        }
        return;
    }
    ///////////////
    private void Swap(int i, int j){
        //Swap index
        TreeT.remove(MytreeStruct[i].item);
        TreeT.remove(MytreeStruct[j].item);
        MytreeStruct[i].Setindex(j);
        MytreeStruct[j].Setindex(i);
        T new_i =  MytreeStruct[i].item;
        T new_j =  MytreeStruct[j].item;
        TreeT.put(MytreeStruct[i].getItem(), MytreeStruct[i]);
        TreeT.put(MytreeStruct[j].getItem(), MytreeStruct[j]);
        MytreeStruct[i] = TreeT.get(new_j);
        MytreeStruct[j] = TreeT.get(new_i);
    }
}