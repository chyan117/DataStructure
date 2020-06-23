public class OffByN implements CharacterComparator{
    private int OffN;
    public OffByN(int N){
        OffN = N;
    }
    @Override
    public boolean equalChars(char x, char y){
        int diff;
        diff = x - y;
        if(diff == OffN||diff ==-OffN){
            return true;
        }
        return false;
    }
}