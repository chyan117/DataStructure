public class LinkedListDeque<T> implements Deque<T>{
    private int size;
    public class DLinkedList {
        DLinkedList  next;
        DLinkedList  prev;
        public T item;
        public DLinkedList(){
            next = this;
            prev = this;
            item = null;
        }
        public DLinkedList(T item){
            next = null;
            prev = null;
            this.item = item;
        }
        public void changeprev(DLinkedList prev){
            this.prev = prev;
        }
        public void changenext(DLinkedList next){
            this.next = next;
        }

    }
    DLinkedList  sentinal;
    public LinkedListDeque(){
        sentinal = new DLinkedList();
        size = 0 ;
    }
    @Override
    public void addFirst(T i){
        size=size+1;
        DLinkedList temp = new DLinkedList(i);
        temp.changenext(sentinal.next);
        temp.changeprev(sentinal);
        sentinal.next.prev/*start*/ = temp;
        sentinal.prev.next.next/*end*/ = temp;
        sentinal.next = temp;
    }
    @Override
    public void addLast(T item){
        if(size==0){
            addFirst( item);
        }
        else {
            size = size + 1;
            DLinkedList temp = new DLinkedList(item);
            temp.changeprev(sentinal.prev);
            temp.changenext(sentinal);
            sentinal.prev.next = temp;
            sentinal.prev = temp;
        }
    }
    @Override
    public boolean isEmpty(){
        if(sentinal.next==sentinal){
            return true;
        }
        else if(sentinal.next == null){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int size(){

        return size;
    }
    @Override
    public void printDeque(){
        DLinkedList D = sentinal;
        while(D.next!=sentinal){
            System.out.print(D.next.item);
            System.out.print(" ");
            D =D.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        T removeF = sentinal.next.item;
        DLinkedList D = sentinal.next.next;
        DLinkedList F = sentinal;
        sentinal.next.next.prev = F;
        sentinal.next = D;
        size = size -1;
        return removeF;
    }
    @Override
    public T removeLast(){
        size = size -1;
        T i = sentinal.prev.item;
        DLinkedList F =sentinal;
        DLinkedList D = sentinal.prev.prev;
        sentinal.prev = D;
        sentinal.prev.next = F;
        return i ;
    }
    @Override
    public T get(int index){
        DLinkedList d = sentinal;
        for(int i = 0; i<index;i++){
            d = d.next;
            if(d.next==sentinal){
                return null;
            }
        }
        System.out.print(d.next.item);
        return d.next.item;
    }
    private T getRecursive_i(int index, DLinkedList current){
        if(index==0){
            System.out.print(current.item);
            return current.item;
        }
        else{
            return getRecursive_i(index-1, current.next);
        }
    }

    public T getRecursive(int index){
            return getRecursive_i(index , sentinal.next);
    }

    /** this is the deep copy of the DLL**/
    public LinkedListDeque(LinkedListDeque other){
        sentinal = new DLinkedList();
        size = 0 ;
        for(int i=0; i<other.size(); i++){
            addLast((T)other.get(i) );
        }
    }

}