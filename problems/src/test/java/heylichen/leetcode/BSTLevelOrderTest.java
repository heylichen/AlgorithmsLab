package heylichen.leetcode;

import org.junit.Test;

import java.util.List;

public class BSTLevelOrderTest {
    private BSTLevelOrder bean = new BSTLevelOrder();

    @Test
    public void name() {
        TreeNode node = createTree();
        TreeNodeHelper.print(node);

        List<List<Integer>> levels = bean.levelOrder(node);
        System.out.println(levels);
    }


    private TreeNode createTree() {
        TreeNode n10 = new TreeNode(10);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        n10.left = (n5);
        n5.left = (n4);

        TreeNode n20 = new TreeNode(20);
        TreeNode n30 = new TreeNode(30);
        TreeNode n15 = new TreeNode(15);
        TreeNode n12 = new TreeNode(12);
        TreeNode n18 = new TreeNode(18);

        n20.left = n15;
        n20.right = n30;
        n15.left = n12;
        n15.right = n18;
        n10.right = n20;
        return n10;
    }
}