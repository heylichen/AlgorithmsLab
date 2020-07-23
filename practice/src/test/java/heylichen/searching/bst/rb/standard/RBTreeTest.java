package heylichen.searching.bst.rb.standard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class RBTreeTest {

    @Test
    public void testInsertionKeepRBProperties() {
        RBTreeChecker checker = new RBTreeChecker();
        RBTree<Integer, Integer> rbt = new RBTree<>();
        List<Integer> keys = Arrays.asList(11, 2, 14, 1, 7, 15, 5, 8, 4);
        for (Integer key : keys) {
            rbt.put(key, key);
            RBTreeChecker.CheckResult result = checker.check(rbt);
            Assert.assertTrue(result.getMsg(), result.isPass());
        }
    }

    @Test
    public void testInsertionKeyValue() {
        RBTree<Integer, Integer> rbt = new RBTree<>();
        List<Integer> keys = Arrays.asList(11, 2, 14, 1, 7, 15, 5, 8, 4);
        for (Integer key : keys) {
            rbt.put(key, key);
        }
        for (Integer key : keys) {
            Integer v = rbt.get(key);
            Assert.assertEquals("insert value error", key, v);
        }
    }

    @Test
    public void testSimpleDelete() {
        RBTreeChecker checker = new RBTreeChecker();
        RBTree<Integer, Integer> rbt = new RBTree<>();

        for (int i = 1; i <= 10; i++) {
            rbt.put(i, i);
        }
        assertRedBlack(checker, rbt);

        for (int i = 1; i <= 10; i++) {
            rbt.delete(i);
            Assert.assertEquals("key " + i + " is deleted, rbt should not contains it.", false, rbt.containsKey(i));
            assertRedBlack(checker, rbt);
        }

        Assert.assertEquals("must be empty after delete all keys", true, rbt.isEmpty());
    }

    @Test
    public void testSize() {
        List<Integer> valueList = randomList(1000);
        RBTree<Integer, Integer> rbt = new RBTree<>();
        int size = 0;
        for (Integer integer : valueList) {
            rbt.put(integer, integer);
            size++;
            Assert.assertEquals(size, rbt.size());
        }
        for (Integer integer : valueList) {
            rbt.put(integer, integer);
        }
        Assert.assertEquals(size, rbt.size());

        for (Integer integer : valueList) {
            rbt.delete(integer);
            size--;
            Assert.assertEquals(size, rbt.size());
        }
    }

    @Test
    public void testRandomDelete() {
        int times = 10;
        int nodeCount = 1000;
        for (int i = 0; i < times; i++) {
            doRandomDeleteTest(nodeCount);
        }
    }

    private void doRandomDeleteTest(int nodeCount) {
        RBTreeChecker checker = new RBTreeChecker();
        RBTree<Integer, Integer> rbt = new RBTree<>();
        List<Integer> valueList = randomList(nodeCount);

        for (Integer value : valueList) {
            rbt.delete(value);
            Assert.assertEquals("key " + value + " is deleted, rbt should not contains it.", false, rbt.containsKey(value));
            assertRedBlack(checker, rbt);
        }
        Assert.assertEquals("must be empty after delete all keys", true, rbt.isEmpty());
    }

    private List<Integer> randomList(int count) {
        Random rand = new Random();
        Set<Integer> values = new LinkedHashSet<>();
        while (values.size() < count) {
            values.add(rand.nextInt(100000));
        }

        return new ArrayList<>(values);
    }

    private void assertRedBlack(RBTreeChecker checker, RBTree<Integer, Integer> rbt) {
        RBTreeChecker.CheckResult result = checker.check(rbt);
        Assert.assertTrue(result.getMsg(), result.isPass());
    }
}