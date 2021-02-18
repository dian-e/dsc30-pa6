import org.junit.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTreeTester {

    /* BSTree bst1, bst2, bst3;
    Iterator iter1, iter2, iter3;

    @Before
    public void setUp() {
        bst1 = new BSTree();
        int[] intKeys = {39, 49, 18, 53, 83, 24, 14, 41, 32, 48};
        for (int key: intKeys) { bst1.insert(key); }
        bst1.insertData(83, "str");
        bst1.insertData(83, "string");
        bst1.insertData(83, "String");
        bst1.insertData(49, "test");
        bst1.insertData(49, "testing");
        bst1.insertData(48, "fourty eight");
        bst1.insertData(32, "three two");
        bst1.insertData(24, "8 * 3 !");

        bst2 = new BSTree();
        char[] charKeys = {'b', 'a', 'd', 'c', 'e', 'f'};
        for (char key: charKeys) { bst2.insert(key); }
        bst2.insertData('a', false);
        bst2.insertData('c', true);
        bst2.insertData('c', false);
        bst2.insertData('e', false);

        bst3 = new BSTree();

        iter1 = bst1.iterator();
        iter2 = bst2.iterator();
        iter3 = bst3.iterator();
    }

    @Test
    public void testGetRoot() {
        assertEquals(39, bst1.getRoot().getKey());
        assertEquals('b', bst2.getRoot().getKey());
        assertEquals(null, bst3.getRoot());
    }

    @Test
    public void testGetSize() {
        assertEquals(10, bst1.getSize());
        assertEquals(6, bst2.getSize());
        assertEquals(0, bst3.getSize());
    }

    @Test (expected = NullPointerException.class)
    public void testInsertThrowsNPE() { bst1.insert(null); }

    @Test
    public void testInsert() {
        assertTrue(bst1.insert(0));
        assertTrue(bst1.insert(-4));
        assertFalse(bst1.insert(39));
        assertEquals(12, bst1.getSize());

        assertTrue(bst2.insert('z'));
        assertEquals(7, bst2.getSize());

        assertTrue(bst3.insert("str"));
        assertEquals(1, bst3.getSize());
        assertFalse(bst3.insert("str"));
    }

    @Test (expected = NullPointerException.class)
    public void testFindKeyThrowsNPE() { bst1.findKey(null); }

    @Test
    public void testFindKey() {
        assertTrue(bst1.findKey(48));
        assertTrue(bst1.findKey(32));
        assertFalse(bst1.findKey(30));
        assertFalse(bst1.findKey(100));
        assertFalse(bst1.findKey(-2));
        assertFalse(bst1.findKey(33));

        assertTrue(bst2.findKey('f'));

        assertFalse(bst3.findKey(48));
    }

    @Test (expected = NullPointerException.class)
    public void testInsertDataKeyThrowsNPE() { bst1.insertData(null, "ijk"); }

    @Test (expected = NullPointerException.class)
    public void testInsertDataDataThrowsNPE() { bst1.insertData(0, null); }

    @Test (expected = IllegalArgumentException.class)
    public void testInsertDataThrowsIAE() { bst1.insertData(0, "info"); }

    @Test
    public void testInsertData() {
        bst1.insertData(83, "st");
        assertEquals("[str, string, String, st]", bst1.findDataList(83).toString());
        bst1.insertData(83, "st");
        assertEquals("[str, string, String, st, st]", bst1.findDataList(83).toString());

        bst2.insertData('c', "st");
        assertEquals("[true, false, st]", bst2.findDataList('c').toString());
    }

    @Test (expected = NullPointerException.class)
    public void testFindDataListThrowsNPE() { bst1.findDataList(null); }

    @Test (expected = IllegalArgumentException.class)
    public void testFindDataListThrowsIAE() { bst1.findDataList(0); }

    @Test
    public void testFindDataList() {
        assertEquals("[test, testing]", bst1.findDataList(49).toString());
        assertEquals("[8 * 3 !]", bst1.findDataList(24).toString());
        assertEquals("[false]", bst2.findDataList('e').toString());
    }

    @Test
    public void testFindHeight() {
        assertEquals(3, bst1.findHeight());
        assertEquals(3, bst2.findHeight());

        assertEquals(-1, bst3.findHeight());
        bst3.insert(4.008);
        assertEquals(0, bst3.findHeight());
    }

    @Test
    public void testHasNext() {
        assertTrue(iter1.hasNext());
        assertTrue(iter1.hasNext());

        assertTrue(iter2.hasNext());
        assertTrue(iter2.hasNext());

        assertFalse(iter3.hasNext());
        assertFalse(iter3.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void testNextThrowsNSEE() { iter3.next(); }

    @Test
    public void testNext() {
        assertEquals(14, iter1.next());
        assertEquals(18, iter1.next());
        assertEquals(24, iter1.next());
        assertEquals(32, iter1.next());
        assertEquals(39, iter1.next());
        assertEquals(41, iter1.next());
        assertEquals(48, iter1.next());
        assertEquals(49, iter1.next());
        assertEquals(53, iter1.next());
        assertEquals(83, iter1.next());
        assertFalse(iter1.hasNext());

        assertEquals('a', iter2.next());
        assertEquals('b', iter2.next());
        assertEquals('c', iter2.next());
        assertEquals('d', iter2.next());
        assertEquals('e', iter2.next());
        assertEquals('f', iter2.next());
        assertFalse(iter2.hasNext());
    } */

    @Test
    public void testIntersection() {
        BSTree bst4 = new BSTree();
        int[] bst4Keys = {5, 1, 0, 4, 10, 7, 9};
        for (int key: bst4Keys) { bst4.insert(key); }

        BSTree bst5 = new BSTree();
        int[] bst5Keys = {10, 7, 4, 9, 20};
        for (int key: bst5Keys) { bst5.insert(key); }

        Iterator iter4 = bst4.iterator();
        Iterator iter5 = bst5.iterator();
        assertEquals("[4, 7, 9, 10]", bst4.intersection(iter4, iter5).toString());
    }
}