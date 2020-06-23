public class Palindrome{
    public Deque<Character>wordToDeque(String word){
        Deque<Character> D = new LinkedListDeque<>() ;
        for(int i=0; i<word.length(); i++){
              D.addLast(word.charAt(i));
        }
        return D ;
    }
    public boolean isPalindrome(String word){
        Deque D = wordToDeque(word);
        String vol = "";
        for(int i = 0; i<word.length(); i++){
            vol += D.removeLast();
        }
        if(vol.equals(word) /*||word.length()==1||word.length()==0*/){
            return true;
        }
        return false;
    }
    /*OverLoaded*/
    /*Do not need to compare odd char with the middle*/
    /*Any 0 to 1 char will be consider Palindrome*/
    public boolean isPalindrome(String word, CharacterComparator cc){
        int wordlength = word.length();
        for(int i=0; i<wordlength/2; i++) {
            if(!(cc.equalChars(word.charAt(i),word.charAt(wordlength-i-1) ))){
                return false;
            }
        }
        return true;
    }
}