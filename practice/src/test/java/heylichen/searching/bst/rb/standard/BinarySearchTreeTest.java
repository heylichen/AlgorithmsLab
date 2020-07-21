package heylichen.searching.bst.rb.standard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author lichen
 * @date 2020/6/3 15:42
 * @desc
 */
public class BinarySearchTreeTest {

    @Test
    public void testDelete() {
        BinarySearchTree<Integer, Integer> bt = randomSt100();
        List<Integer> keys = new ArrayList<>(bt.keySet());

        int size = bt.size();
        for (int i = 0; i < keys.size(); i++) {
            Integer key = keys.get(i);
            Assert.assertEquals("", true, bt.containsKey(key));
            bt.delete(key);
            Assert.assertEquals("contains " + key + " error ", false, bt.containsKey(key));
            size--;
            Assert.assertEquals("", size, bt.size());
        }
        Assert.assertEquals("", true, bt.isEmpty());
    }

    @Test
    public void testPutGet() {
        BinarySearchTree<Integer, Integer> bt = new BinarySearchTree<>();
        for (int i = 0; i < 100; i++) {
            bt.put(i, i);
        }
        Assert.assertEquals("", 100, bt.size());
        for (int i = 0; i < 100; i++) {
            int value = bt.get(i);
            Assert.assertEquals("", i, value);
        }
    }


    @Test
    public void testKeys() {
        BinarySearchTree<Integer, Integer> bt = new BinarySearchTree<>();
        Set<Integer> expectedKeys = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            expectedKeys.add(i);
            bt.put(i, i);
        }

        Set<Integer> keys = bt.keySet();
        Assert.assertEquals("", expectedKeys, keys);
    }


    private BinarySearchTree<Integer, Integer> randomSt100() {
        int SIZE = 100;
        List<Integer> keys = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            keys.add(i);
        }
        Collections.shuffle(keys);
        BinarySearchTree<Integer, Integer> bt = new BinarySearchTree<>();
        for (Integer key : keys) {
            bt.put(key, key);
        }
        return bt;
    }


}
