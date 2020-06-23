import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
public class MyTrieSet implements  TrieSet61B{
    private final int R = 4;
    /** Clears all items out of Trie */
    private Node Root;
    @Override
    public void clear(){
        Root = new Node();
    }
    public MyTrieSet(){
        Root = new Node();
    }
    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
        Node Search = Root;
        //Two circumstance will fail. The first one is fell off trees and another is false.
        for(int i=0; i<key.length(); i++){
            if(Root.Node_HasTable==null){
                return false;
            }
            if(! Search.Node_HasTable.containsKey(key.charAt(i)) ){
                return false;
            }
            if(i==key.length()-1){
                if(!Search.Node_HasTable.get(key.charAt(i)).is_Blue){
                    return false;
                }
                continue;
            }
            Search = Search.Node_HasTable.get(key.charAt(i));
        }
        return true;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key){
        if(key.length()<=0|| key==null){
            return;
        }
        Node traverse = Root;
        for(int i = 0; i<key.length(); i++){
            if(traverse.Node_HasTable==null){
                traverse.Node_HasTable = new HashMap<>();
            }
            if( !traverse.Node_HasTable.containsKey(key.charAt(i))){
                if(i==key.length()-1) {
//                    Blue
                    traverse.Node_HasTable.put(key.charAt(i), new Node(true));
                }
                else{
//                    if(traverse.is_Blue==true){
//                        traverse.Node_HasTable.put(key.charAt(i), new Node(true));
//                    }
                    traverse.Node_HasTable.put(key.charAt(i), new Node(false));
                }
                traverse = traverse.Node_HasTable.get(key.charAt(i));
            }
            else{
                traverse = traverse.Node_HasTable.get(key.charAt(i));
                if(i==key.length()-1) {
                    traverse.SetTrue();
                }
            }
        }
    }
    private class Node{
        boolean is_Blue;
        private HashMap<Character, Node > Node_HasTable;
        Node(){
            is_Blue = false;
        }
        Node(Boolean b){
            //Node_HasTable = new HashMap<>(R);
            is_Blue = b;
        }
        void SetTrue(){
            is_Blue = true;
        }

    }
    /** Returns a list of all words that start with PREFIX */
    //Need the traverse algorithm
    /**
     * Find the node α corresponding to the string (in pink).
            Create an empty list x.
            For character c in α.next.keys():
                Call colHelp(“sa” + c, x, α.next.get(c)).
     * */
    @Override
    public List<String> keysWithPrefix(String prefix){
        List<String> x = new ArrayList<>();
        Node Prefix = Root;
        //Find the keyWord
        for(char i: prefix.toCharArray()) {
            if(Prefix.Node_HasTable==null || Prefix.Node_HasTable.get(i)==null){
                return null;
            }
            Prefix = Prefix.Node_HasTable.get(i);
        }
        for(char c: Prefix.Node_HasTable.keySet()){
            colhelper(prefix+c, x, Prefix.Node_HasTable.get(c) );
        }
        return x;
    }
    /**
    1. Create an empty list of results x.
    2. For character c in root.next.keys():
            Call colHelp(“c”, x, root.next.get(c)).
                Return x.
     */
    /*This method I write to test the traverse colhelper method*/
    private ArrayList traverse_all(){
        List x = new ArrayList();
        for(char c: Root.Node_HasTable.keySet()){
            colhelper( Character.toString(c) , x, Root.Node_HasTable.get(c));
        }
        return (ArrayList) x;
    }
    /***
     * 1. If n.isKey, then x.add(s).
     * 2. For character c in n.next.keys()
     *          Call colHelp(s + c, x, n.next.get(c))
     */
    private ArrayList colhelper(String s, List<String> x, Node n){
        if(n.is_Blue){
            x.add(s);
        }
        if(n.Node_HasTable==null){
            return (ArrayList) x;
        }
        for(char c: n.Node_HasTable.keySet()){
            colhelper( s+c, x, n.Node_HasTable.get(c));
        }
        return (ArrayList) x;
    }
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key){
        Node longest = Root;
        String s ="";
        String return_val="";
        if(Root.Node_HasTable==null){
            return s;
        }
        for(char c: key.toCharArray()){
            if(longest.Node_HasTable==null){
                return return_val;
            }
            if(longest.Node_HasTable.containsKey(c)) {
                s = s+c;
                longest = longest.Node_HasTable.get(c);
                if(longest.is_Blue){
                    return_val = s;
                }
            }
        }
        return return_val;
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("he");
        t.add("zebra");
        System.out.println(t.longestPrefixOf("hisp") );//expect hi
        System.out.println(t.longestPrefixOf("helpful") );// expect help
        System.out.println(t.contains("hella")); // expect false
        System.out.print(t.keysWithPrefix("h").toString());//expect hello, hi, help, he

    }
}
