import org.junit.Test;
import static org.junit.Assert.*;

public class OffByOne implements  CharacterComparator{
    /*when the char is not equal, return true*/
    @Override
    public boolean equalChars(char x, char y){
        int diff;
        diff = x - y;
        if(diff == 1||diff ==-1){
            return true;
        }
        return false;
    }
}
