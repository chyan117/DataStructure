/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
import java.util.LinkedList;
import java.util.Queue;
class RollingString{
    private Queue<Character> char_Q;
    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        char_Q = new LinkedList<>();
        for(char c : s.toCharArray()){
            char_Q.offer(c);
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    /*Can not directly add a character to a string because it will resize every time*/
    public void addChar(char c) {
        /*add last*/
        char_Q.offer(c);
        //remove first
        char_Q.poll();
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    /*only us append to construct the "Linear Time" in the string. */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for(char c : char_Q) {
            strb.append(c);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return char_Q.size();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        if(this==o){
            return true;
        }
        if(this.getClass()!=o.getClass()){
            return false;
        }
        RollingString b = (RollingString)o;
        if(b.length()!=this.length()){
            return false;
        }
        if(b.hashCode()!=this.hashCode()){
            return false;
        }
        if(!this.toString().equals(b.toString())){
            return false;
        }
        return true;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        int c = 0;
        for(char ch : char_Q) {
            c = c*UNIQUECHARS;
            c = ch + c;
        }
        return Math.floorMod(c, PRIMEBASE);
    }
}
