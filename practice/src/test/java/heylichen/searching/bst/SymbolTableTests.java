package heylichen.searching.bst;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author lichen
 * @date 2020/5/27 15:33
 * @desc
 */
public abstract class SymbolTableTests {

    @Test
    public void testPutGet() {
        SymbolTable<Integer, Integer> bt = createSymbolTable();
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
        SymbolTable<Integer, Integer> bt = createSymbolTable();
        Set<Integer> expectedKeys = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            expectedKeys.add(i);
            bt.put(i, i);
        }

        Set<Integer> keys = bt.keySet();
        Assert.assertEquals("", expectedKeys, keys);
    }


    @Test
    public void testRankSelect() {
        SymbolTable<Integer, Integer> bt = createSymbolTable();
        for (int i = 1; i <= 100; i++) {
            bt.put(i, i);
        }
        for (int i = 0; i <= 101; i++) {
            Integer key = bt.select(i);
            if (key != null) {
                int actualRank = bt.rank(key);
                Assert.assertEquals("rank error", i, actualRank);
            }
            System.out.println("rank " + i + ":" + key);
        }

        System.out.println(bt.floor(16));
        System.out.println(bt.ceiling(20));
    }

    @Test
    public void testDelete() {
        SymbolTable<Integer, Integer> bt = randomSt100();
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
    public void testDeleteMin() {
        SymbolTable<Integer, Integer> bt = randomSt100();
        int expectedMin = 0;
        while (!bt.isEmpty()) {
            int min = bt.min();
            Assert.assertEquals("", expectedMin, min);
            expectedMin++;
            bt.deleteMin();
            Assert.assertEquals("", false, bt.containsKey(min));
        }
    }

    private SymbolTable<Integer, Integer> randomSt100() {
        int SIZE = 100;
        List<Integer> keys = new ArrayList<>(SIZE);
        for (int i = 0; i <SIZE; i++) {
            keys.add(i);
        }
        Collections.shuffle(keys);
        SymbolTable<Integer, Integer> bt = createSymbolTable();
        for (Integer key : keys) {
            bt.put(key, key);
        }
        return bt;
    }


    protected abstract <K extends Comparable<K>, V> SymbolTable<K, V> createSymbolTable();
}
