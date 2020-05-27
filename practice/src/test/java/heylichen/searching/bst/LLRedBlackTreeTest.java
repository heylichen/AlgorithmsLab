package heylichen.searching.bst;

import org.junit.Test;

public class LLRedBlackTreeTest {

    @Test
    public void name() {
        LLRedBlackTree<Integer, Integer> rbt = new LLRedBlackTree<>();
        for (int i = 0; i < 6; i++) {
            rbt.put(i, i);
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(rbt.get(i));
        }
        System.out.println();
    }
}