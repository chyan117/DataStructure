import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;
public class BSTMap<K extends Comparable<K>, V> implements  Map61B<K, V> {
    private TreeNode Root;
    private int size;
    private class TreeNode{
        private K key;
        private V value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
        public void clear(){
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;
        }
        public K get_K_V(){
            return key;
        }
        public V get_V_V(){
            return value;
        }
    }
    //traverse algorithm in BST
    private class for_each_class implements Iterator<K>{
        private Stack<TreeNode> MyStack = new Stack<TreeNode>();
        private for_each_class(TreeNode Root){
            NodeToLeft(Root);
        }
        @Override
        public boolean hasNext(){
            if(MyStack.isEmpty()){
                return false;
            }
            return true;
        }
        @Override
        public K next(){
            TreeNode returnNode = MyStack.pop();
            NodeToLeft(returnNode.right);
            return returnNode.key;
        }
        private void NodeToLeft(TreeNode Node){
            if(Node!=null){
                MyStack.push(Node);
                NodeToLeft(Node.left);
            }
            return;
        }
    }


    @Override
    public Iterator<K> iterator(){
        return new for_each_class(Root);
    }
    public BSTMap(){
        size = 0;
        Root = null;
    }
    /* Removes all of the mappings from this map. */
    @Override
    public void clear(){
        size = 0;
        Root = null;
    }
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(size()==0){
            return null;
        }
        TreeNode M = Root;
        while(M!=null){
            if( key.compareTo( M.get_K_V() )>0  ){
                M = M.right;
            }
            else if(key.compareTo( M.get_K_V() ) < 0){
                M = M.left;
            }
            else if(key.compareTo( M.get_K_V() ) == 0){
                return M.get_V_V();
            }
            else{
                return null;
            }
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }
    //this method is used for find the front Node of the target Node.
    private TreeNode find0(TreeNode Rootptr_for0, K key){
        if(Rootptr_for0.right!=null){
            if(key.compareTo(Rootptr_for0.right.key )==0 ){
                return Rootptr_for0;
            }
            if(key.compareTo(Rootptr_for0.right.key )>0 ){
                Rootptr_for0 = Rootptr_for0.right;
                return find0(Rootptr_for0, key);
            }
        }
        if(Rootptr_for0.left!=null){
            if(key.compareTo(Rootptr_for0.left.key )==0 ){
                return Rootptr_for0;
            }
            if(key.compareTo(Rootptr_for0.left.key )>0 ){
                Rootptr_for0 = Rootptr_for0.left;
                return find0(Rootptr_for0, key);
            }
        }
        return Rootptr_for0;
    }
    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if(size==0){
            Root = new TreeNode(key, value);
            size = size+1;
            return;
        }
        else{
            TreeNode M = Root;
            while(M!=null) {
                if (key.compareTo(M.get_K_V()) > 0) {
                    if(M.right==null){
                        M.right = new TreeNode(key, value);
                        size = size+1;
                        return;
                    }
                    M = M.right;
                } else if (key.compareTo(M.get_K_V()) < 0) {
                    if(M.left==null ) {
                        M.left = new TreeNode(key, value);
                        size = size + 1;
                        return;
                    }
                    M = M.left;
                } else if (key.compareTo(M.get_K_V()) == 0) {
                    return;
                }
            }
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        Set<K> S = new HashSet<>();
        for_each_class F = new for_each_class(Root);
        while(F.hasNext()){
            S.add(F.next());
        }
        return S;
    }
    private int num_child(TreeNode P){
        if(P.left!=null&&P.right!=null){
            return 2;
        }
        if(P.left!=null||P.right!=null){
            return 1;
        }
        return 0;
    }
    private void find(K key){
        while(Root!=null){
            if(key.compareTo(Root.key)>0){
                Root   =   Root.right;
            }
            else if(key.compareTo(Root.key)<0){
                Root   =   Root.left;
            }
            /*Find that! */
            else if(key.compareTo(Root.key)==0){
                return;
            }
            /*Do not find the key*/
            else{
                return;
            }
        }
        return;
    }
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        TreeNode Rootptr = Root;
        TreeNode Rootptr_for0 = Root;
        int set_Root=0;
        V return_val;
        find(key);
        if(Root==Rootptr){
            set_Root = 1;
        }
        if(Root==null){
            Root = Rootptr;
            return null;
        }
        else {
            int child_number = num_child(Root);
            if (child_number == 0) {
                return_val = Root.value;
                Rootptr_for0 = find0(Rootptr_for0, key);
                //it is a root.
                if ((Rootptr_for0.key.compareTo(Rootptr.key) == 0) && (Rootptr_for0.left == null) && (Rootptr_for0.right == null)) {
                    Rootptr = null;
                    set_Root = 0;
                }
                if (Rootptr_for0.left != null) {
                    if (Rootptr_for0.left.key == key) {
                        Rootptr_for0.left = null;
                    }
                }
                if (Rootptr_for0.right != null) {
                    if (Rootptr_for0.right.key == key) {
                        Rootptr_for0.right = null;
                    }
                }
            } else if (child_number == 1) {
                return_val = Root.value;
                Rootptr_for0 = find0(Rootptr_for0, key);
                if (Root.right != null) {
                    Root = Root.right;
                }
                if (Root.left != null) {
                    Root = Root.left;
                }
                if (Rootptr_for0.right != null) {
                    if (Rootptr_for0.right.key.compareTo(key) == 0) {
                        Rootptr_for0.right = Root;
                    }
                } else if (Rootptr_for0.left != null) {
                    if (Rootptr_for0.left.key.compareTo(key) == 0) {
                        Rootptr_for0.left = Root;
                    }
                }
            }
            /*This one has multiple situation. */
            /*we have to replace a taken one by a choosen element that exists originally on the tree*/
            else {
                return_val = Root.value;
                TreeNode OrigninalRight = Root.right;
                TreeNode OrigninalLeft = Root.left;
                Root = Root.left;
                while (Root.right != null) {
                    Root = Root.right;
                }
                /*Ready to replace */
                TreeNode temp = Root.left;
                if (Root != OrigninalLeft) {
                    Root.left = OrigninalLeft;
                } else {
                    Root.left = null;
                }
                Root.right = OrigninalRight;
                /*Check the replace hole*/
                if (temp != null) {
                    put(temp.key, temp.value);
                    size = size -1;
                }
            }
        }
        size = size - 1;
        if(set_Root!=1){
                Root = Rootptr;
                return return_val;
        }
        return return_val;

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        TreeNode Rootptr = Root;
        find(key);
        if(Root==null){
            Root = Rootptr;
            return null;
        }
        if(Root.get_V_V()!=value){
            Root = Rootptr;
            return null;
        }
        Root = Rootptr;
        return remove(key);
    }

    public void printInOrder(){
        for(K i : this){
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.print(". ");
    }
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if(key==null){
            return false;
        }
        else{
            for(K i: this){
                if(i.equals(key)){
                    return true;
                }
            }
            return false;
        }
    }
}