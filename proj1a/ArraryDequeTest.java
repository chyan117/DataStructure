public class ArraryDequeTest {
    public static void array_increment_first(ArrayDeque T, int number){
        for(int i=0;i<number;i++){
            T.addFirst(i);
        }
    }
    public static void array_remove_first(ArrayDeque T, int number){
        for(int i=0;i<number;i++){
            T.removeFirst();
        }
    }
    public static void array_increment_last(ArrayDeque T, int number){
        for(int i=0;i<number;i++){
            T.addLast(i);
        }
    }
    public static void array_remove_last(ArrayDeque T, int number){
        for(int i=0;i<number;i++){
            T.removeLast();
        }
    }

    public static void test_Integer2(){
        ArrayDeque<Integer> arraydd1 = new ArrayDeque<Integer>();
        array_increment_first(arraydd1, 69);
        arraydd1.printDeque();
        //array_remove_first(arraydd, 13);
    }
    public static void test_Integer(){
        ArrayDeque<Integer> arraydd = new ArrayDeque<Integer>();
        array_increment_first(arraydd, 10000);
        array_remove_first(arraydd, 9999);
    }
    public static void test_Integer3(ArrayDeque arraydd3){
        array_increment_last(arraydd3, 69);
        arraydd3.printDeque();
        array_remove_last(arraydd3, 50);
        arraydd3.printDeque();
    }
    public static void main(String[] args){
        System.out.println("Running the Array Deque tests.\n");
        test_Integer();// test increment
        test_Integer2();//test print
        ArrayDeque<Integer> arraydd3 = new ArrayDeque<Integer>();
        test_Integer3(arraydd3);//test add_last
        ArrayDeque<Integer> sample = new ArrayDeque<Integer>(arraydd3);
        System.out.println("sample is : ");
        sample.printDeque();
    }
}
