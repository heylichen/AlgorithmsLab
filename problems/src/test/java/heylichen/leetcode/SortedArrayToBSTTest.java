package heylichen.leetcode;

import org.junit.Test;

public class SortedArrayToBSTTest {
    private SortedArrayToBST bean = new SortedArrayToBST();

    @Test
    public void name() {
        TreeNode node = bean.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        TreeNodeHelper.print(node);

        node = bean.sortedArrayToBST(new int[]{1,3});
        TreeNodeHelper.print(node);

    }
}