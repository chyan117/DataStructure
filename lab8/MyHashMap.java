import java.util.*;

public class MyHashMap<K, V> implements  Map61B<K, V>{
    int initialSize;
    double loadFactor;
    private LinkedList<ListElement>[] MyList;
    private int size;
    public MyHashMap(){
        initialSize = 16;
        loadFactor = 0.75;
        MyList = new LinkedList[initialSize];
        for(int i=0; i<initialSize; i++){
            MyList[i] = new LinkedList<>();
        }
    }
    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
        MyList = new LinkedList[initialSize];
        for(int i=0; i<initialSize; i++){
            MyList[i] = new LinkedList<>();
        }
    }
    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        MyList = new LinkedList[initialSize];
        for(int i=0; i<initialSize; i++){
            MyList[i] = new LinkedList<>();
        }
    }
    private class iteration_Hash implements  Iterator<K>{
        private LinkedList<K> L_I;
        public iteration_Hash(){
            L_I = new LinkedList<>();
            setallkey();
        }
        @Override
        public boolean hasNext() {
            if(L_I.isEmpty()) {
                return false;
            }
            return true;
        }
        @Override
        public K next(){
            return L_I.removeFirst();
        }
        private void setallkey(){
            for(LinkedList<ListElement> L : MyList){
                if(L!=null) {
                    int k = L.size();
                    for(int i=0; i<k; i++) {
                        ListElement K = L.getFirst();
                        L_I.add( L.removeFirst().key );
                        L.addLast(K);
                    }
                }
            }
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new iteration_Hash();
    }
    //can only use ArrayList, LinkedList, HashSet, iterator and Set library
    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
       size = 0;
        MyList = new LinkedList[initialSize];
        for(int i=0; i<initialSize; i++){
            MyList[i] = new LinkedList<>();
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if(get(key)!=null){
            return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        int search = key.hashCode() % initialSize;
        if(search<0){
            search = Math.floorMod(search, initialSize);
        }
        if(MyList[search]==null){
            MyList[search] = new LinkedList<>();
        }
        for (ListElement L : MyList[search]) {
            if(L.get_key()==null){
                return null;
            }
            if (L.get_key().equals(key)) {
                return L.get_V();
            }
            return null;
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    //null keys will never be inserted.
    public void put(K key, V value){
        /*this is a set*/
        ListElement E = new ListElement(key, value);
        int hash = key.hashCode();
        int aftertrans;
        if(hash<0){
            aftertrans = Math.floorMod(hash, initialSize);
        }
        else{
            aftertrans= hash%initialSize;
        }
        if(containsKey(key)){
            for(ListElement L : MyList[aftertrans]){
                if(L.get_key().equals(key)){
                    L.setV(value);
                }
            }
        }
        else {
            if(MyList[aftertrans]==null){
                MyList[aftertrans] = new LinkedList<ListElement>();
            }
            MyList[aftertrans].addFirst(new ListElement(key, value) );
            size = size+1;
        }
        //resize
        int j;
        if((double)size/initialSize >= loadFactor){
            initialSize = initialSize*2;
            LinkedList<ListElement>[] NewList = new LinkedList[initialSize];
            for(LinkedList<ListElement> L : MyList){
                if(L==null){
                    L = new LinkedList<>();
                }
                while(!L.isEmpty()){
                    if(L.getFirst().key.hashCode()<0){
                        j = Math.floorMod(L.getFirst().key.hashCode(), initialSize);
                    }
                    else{
                        j = L.getFirst().key.hashCode()%initialSize;
                    }
                    if(NewList[j]==null){
                        NewList[j] = new LinkedList<>();
                    }
                    NewList[j].add(L.removeFirst());
                }
            }
            MyList = NewList;
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        Set<K> S = new HashSet<K>();
        for(K i:this){
            S.add(i);
        }
        return S;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        int i = key.hashCode();
        ListElement Remove;
        if(i<0){
            i = Math.floorMod(i, initialSize);
        }
        else {
            i = i % initialSize;
        }
        for(ListElement L : MyList[i]){
            if(L.get_key().equals(key)){
                V return_val = L.get_V();
                MyList[i].remove(L);
                size = size-1;
                return return_val;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        if(containsKey(key)){
            if(value.equals(get(key)) ){
                return remove(key);
            }
        }
        return null;
    }
    private class ListElement{
        private K key;
        private V value;
        public V get(K key){
            return value;
        }
        public K get_key(){
            return key;
        }
        public void setV(V val){
            this.value = val;
        }
        public ListElement(K key, V val){
            this.key = key;
            this.value = val;
        }
        public V get_V(){
            return value;
        }
    }
    //try itrtation
    public void printall(){
        int i=0;
        for(K key: this){
            System.out.print(key+"\n");
            i = i+1;
        }
        System.out.println(i);
    }

}