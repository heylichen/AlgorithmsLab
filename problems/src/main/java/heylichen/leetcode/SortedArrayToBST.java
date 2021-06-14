package heylichen.leetcode;

public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int from, int to) {
        if (from > to) {
            return null;
        }
        if (from == to) {
            return new TreeNode(nums[from]);
        }
        int middle = (from + to) / 2;
        TreeNode node = new TreeNode(nums[middle]);
        TreeNode left = build(nums, from, middle - 1);
        TreeNode right = build(nums, middle + 1, to);
        node.left = left;
        node.right = right;
        return node;
    }
}
