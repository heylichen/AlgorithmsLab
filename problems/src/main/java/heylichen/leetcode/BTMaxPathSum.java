package heylichen.leetcode;

public class BTMaxPathSum {
    private int localMax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode node) {
        localMax = Integer.MIN_VALUE;
        int max = doMaxSum(node);
        return Math.max(max, localMax);
    }

    private int doMaxSum(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("not null");
        }
        int current = node.val;
        int currentMax = current;
        int currentLocalMax = current;

        int leftMax = 0;
        if (node.left != null) {
            leftMax = doMaxSum(node.left);
            currentLocalMax = Math.max(currentLocalMax, leftMax);
            currentLocalMax = Math.max(currentLocalMax, leftMax + current);
            currentMax = Math.max(currentMax, leftMax + current);
        }

        int rightMax = 0;
        if (node.right != null) {
            rightMax = doMaxSum(node.right);
            currentLocalMax = Math.max(currentLocalMax, rightMax);
            currentLocalMax = Math.max(currentLocalMax, rightMax + current);
            currentMax = Math.max(currentMax, rightMax + current);
        }

        if (node.left != null && node.right != null) {
            currentLocalMax = Math.max(currentLocalMax, leftMax + rightMax + current);
        }
        localMax = Math.max(localMax, currentLocalMax);
        return currentMax;
    }
}
