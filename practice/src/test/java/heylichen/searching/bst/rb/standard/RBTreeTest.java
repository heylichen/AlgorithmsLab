package heylichen.searching.bst.rb.standard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        RBTreeChecker checker = new RBTreeChecker();
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

}