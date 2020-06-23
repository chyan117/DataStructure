public class ArrayDeque<item> implements Deque<item>{
    private int size;
    private item[]sentinal;
    private int nextfirst;
    private int nextlast;
    /*It is Empty array, and my start point is 3*/
    public ArrayDeque(){
        sentinal = (item[])new Object[8];
        size = 0;
        nextfirst = 3;
        nextlast  = 4;
    }
    private void resize(int i){
        item [] a = (item[] )new Object[i];
        /*the blank part is on the right side*/
        if(nextfirst>=sentinal.length/2){
            /*copy left hand side */
            System.arraycopy(sentinal, 0, a, sentinal.length/2, sentinal.length/2);
            /*copy right hand side*/
            System.arraycopy(sentinal, sentinal.length/2, a , sentinal.length, nextlast-sentinal.length/2);
            /*copy out of bound*/
            if(nextfirst!=sentinal.length-1){
                System.arraycopy(sentinal, nextfirst+1, a, a.length/4 - (sentinal.length-nextlast-1), sentinal.length-nextlast-1);
            }
            sentinal  = a ;
            nextlast = sentinal.length/2 + (nextlast - (sentinal.length)/4);
            nextfirst = (sentinal.length/4) - ((sentinal.length/2-1) -nextfirst) -1;
        }
        else{
            /*copy left hand side*/
            System.arraycopy(sentinal, nextfirst+1, a, nextfirst+1+sentinal.length/2, (sentinal.length-2)/2- nextfirst);
            /*copy right hand side*/
            System.arraycopy(sentinal, sentinal.length/2, a, sentinal.length, sentinal.length/2);
            /*copy out of bound*/
            if(nextfirst!=0){
                System.arraycopy(sentinal, 0, a, sentinal.length+sentinal.length/2, nextfirst);
            }
            sentinal = a;
            nextlast = (sentinal.length/2)+ (sentinal.length/4) + nextlast;
            nextfirst = nextfirst+ sentinal.length/4;
        }

    }
    @Override
    public int size(){
        return size;
    }
    /*resizing at size==sentinal.length-1*/
    public void addFirst(item i){
        if(nextfirst==nextlast){
            resize((sentinal.length)*2);
        }
        sentinal[nextfirst] = i;
        if(nextfirst-1<0) {
            nextfirst = sentinal.length-1 ;
        }
        else{
            nextfirst = nextfirst - 1;
        }
        size = size + 1;
    }
    @Override
    public void addLast(item i){
        if(nextfirst==nextlast){
            resize(sentinal.length*2);
        }
        sentinal[nextlast] = i;
        if(nextlast+1==sentinal.length) {
            nextlast = 0 ;
        }
        else{
            nextlast = nextlast + 1;
        }
        size = size + 1;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        else {
            return false;
        }
    }
    /*Remove the first item on the array*/
    @Override
    public item removeFirst(){
        item i;
        if(nextfirst==sentinal.length-2 ){
            i = sentinal[sentinal.length-1];
            sentinal[sentinal.length-1] = null;
            nextfirst = sentinal.length-1;
        }
        else if(nextfirst==sentinal.length-1 ){
            i = sentinal[0];
            sentinal[0] = null;
            nextfirst = 0;
        }
        else {
            i = sentinal[nextfirst + 1];
            sentinal[nextfirst + 1] = null;
            nextfirst = nextfirst + 1;
        }
        size = size -1 ;
        checkhalfsizing(sentinal);
        return i;
    }
    /*Remove the last item on the array*/
    @Override
    public item removeLast(){
        item i;
        if(nextlast-1<0){
            i = sentinal[sentinal.length-1];
            sentinal[sentinal.length-1]= null;
            nextlast = sentinal.length-1;
        }
        else{
            i = sentinal[nextlast-1];
            sentinal[nextlast-1]=null;
            nextlast =nextlast-1;
        }
        size = size-1;
        checkhalfsizing(sentinal);
        return i;
    }
    @Override
    public item get(int i) {
        item value;
        if (i > size - 1) {
            value= null;
        }
        else {
            /*condition 1: direct send number without overbound issue*/
            if ((nextfirst < sentinal.length / 2) && (nextlast) >= sentinal.length / 2) {
                value = sentinal[nextfirst + 1 + i];
            }
            /*condition 2: nextfirst is at the left hand side, and nextlast as well*/
            else if ((nextfirst < sentinal.length / 2)&&(nextlast<sentinal.length/2)) {
                if(i+1+nextfirst>sentinal.length-1){
                    value = sentinal[i+1+nextfirst-(sentinal.length)];
                }
                else{
                    value = sentinal[i+1+nextfirst];
                }
            }
            /*condtion 3: nextlast is at the right hand side and nextfirst as well*/
            else{
                if(i+nextfirst+1>sentinal.length-1){
                    value = sentinal[i+nextfirst+1 - sentinal.length];
                }
                else{
                    value = sentinal[i+nextfirst+1];
                }
            }
         }
        return value;
    }
    @Override
    public void printDeque(){
        for(int i=0;i<size;i++) {
            System.out.print(get(i));
            System.out.print(" ");
        }
        System.out.println(" ");
    }
    public ArrayDeque(ArrayDeque other){
        int num = other.sentinal.length;
        size = other.size();
        item []A = (item[])new Object[num];
        System.arraycopy(other.sentinal, 0, A, 0, num);
        sentinal = A ;
        nextfirst= other.nextfirst;
        nextlast= other.nextlast;
    }
    private void checkhalfsizing(item[] A){
        double s = (double) size;
        double l = (double) A.length;
        double r = (s/l);
        if( r < 0.25 ){
            int num = A.length;
            item[] B =(item[]) new Object[num/2];
            /*copy the left hand side*/
            System.arraycopy(A,A.length/4, B, 0,A.length/4 );
            /*copy the right hand side*/
            System.arraycopy(A,A.length/2, B, B.length/2, A.length/4);
            sentinal = B;
            nextfirst = nextfirst - num/4;
            nextlast  = nextlast- num/4;
        }
    }
}