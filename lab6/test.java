//I wrote this test on my purpose in order to test my lab6
import static org.junit.Assert.*;
import org.junit.Test;
public class test{
    @Test
    public void simpletest1(){
        UnionFind UF = new UnionFind(15);
        UF.union(0, 1);
        UF.union(2, 3);
        UF.union(4, 5);
        UF.union(6, 7);
        UF.union(1, 3);
        UF.union(5, 7);
        UF.union(4, 0);
        assertTrue( UF.parent(2)==3    );
        assertTrue(UF.sizeOf(3)==8);
        assertTrue(UF.parent(4)== 7);
        UF.union(8, 9);
        UF.union(10, 11);
        UF.union(9, 10);
        UF.union(8, 5);
        assertTrue( UF.parent(11)==3    );
        assertTrue( UF.parent(4)==7    );
        assertTrue( UF.parent(3)==-12   );
        UF.union(8, 5);



    }
}
