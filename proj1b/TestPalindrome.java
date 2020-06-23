
import static org.junit.Assert.*;

import org.junit.Test;

public class TestPalindrome {

    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testisPalindrome(){
        String S = "This is me";
        assertTrue(palindrome.isPalindrome(S));
    }
    @Test
    public void testisPalindromeOffByone(){
        CharacterComparator OffByOne = new OffByOne();
        String S = "abab";
        assertTrue(palindrome.isPalindrome(S,OffByOne ));
    }
}
