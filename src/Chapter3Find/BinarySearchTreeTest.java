//package Chapter3Find;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BinarySearchTreeTest {
//    private BinarySearchTree<String, Integer> bTree;
//
//    @BeforeEach
//    void setUp() {
//        bTree = new BinarySearchTree<>();
//        String test = "SEARCHEXAMPLE";
//        for (int i = 0; i < test.split("").length; i++) {
//            bTree.put(test.split("")[i], i);
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//        bTree = null;
//    }
//
//    @Test
//    void size() {
//        assertEquals(10, bTree.size());
//    }
//
//    @Test
//    void get() {
//        assertEquals(0, (int) bTree.get("S"));
//    }
//
//    @Test
//    void put() {
//    }
//
//    @Test
//    void min() {
//        assertEquals("A" , bTree.min());
//    }
//
//    @Test
//    void max() {
//        assertEquals("X", bTree.max());
//    }
//
//    @Test
//    void floor() {
//        //
//    }
//
//    @Test
//    void ceiling() {
//        //
//    }
//
//    @Test
//    void select() {
//        assertEquals( "H", bTree.select(3));
//    }
//
//    @Test
//    void rank() {
//        //
//    }
//
//    @Test
//    void deleteMin() {
//        bTree.deleteMin();
//        assertEquals("C" ,bTree.min() );
//    }
//
//    @Test
//    void deleteMax() {
//        bTree.deleteMax();
//        assertEquals("H", bTree.max());
//    }
//
//    @Test
//    void delete() {
//        //
//    }
//
//    @Test
//    void keys() {
//        //
//    }
//
//    @Test
//    void height() {
//    }
//}