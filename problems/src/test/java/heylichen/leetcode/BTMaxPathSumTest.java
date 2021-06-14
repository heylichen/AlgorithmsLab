package heylichen.leetcode;

import org.junit.Test;

public class BTMaxPathSumTest {

    @Test
    public void name() {
        BTMaxPathSum s = new BTMaxPathSum();
        System.out.println(s.maxPathSum(build()));

        System.out.println(s.maxPathSum(build2()));
        //错误，未考虑到的case, null不能参与计算
        System.out.println(s.maxPathSum(new TreeNode(-3)));
        //未考虑到的case, 局部最长与参与全局计算的最长，场景要区分
        System.out.println(s.maxPathSum(build3()));
    }

    private TreeNode build() {
        TreeNode n1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        return n1;
    }

    private TreeNode build2() {
        TreeNode n20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        return new TreeNode(-10, new TreeNode(9), n20);
    }

    private TreeNode build3() {
        TreeNode n1 = new TreeNode(1, new TreeNode(-1), null);
        TreeNode n_2 = new TreeNode(-2,  n1, new TreeNode(3));
        TreeNode n_3 = new TreeNode(-3, new TreeNode(-2), null);
        return new TreeNode(1, n_2, n_3);
    }


}