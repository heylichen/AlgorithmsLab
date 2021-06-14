package heylichen.leetcode;

import java.util.*;

public class BSTZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = init(root);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        queue.add(null);

        int levelIndex = 0;
        while (!queue.isEmpty()) {
            //process all nodes of the same level
            List<Integer> currentLevel = result.get(levelIndex);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                if (node == null) {
                    break;
                } else {
                    currentLevel.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            if (queue.isEmpty()) {
                break;
            }
            queue.add(null);
            levelIndex++;
        }

        reverse(result);
        return result;
    }

    private void reverse(List<List<Integer>> result) {
        if (result.size() <= 1) {
            return;
        }
        for (int i = 1; i < result.size(); i+=2) {
            List<Integer> list = result.get(i);
            if (list.size() == 1) {
                continue;
            }
            int n = list.size() / 2;
            for (int i1 = 0; i1 < n; i1++) {
                int j = list.size() - 1 - i1;

                int tmp = list.get(i1);
                list.set(i1,list.get(j));
                list.set(j, tmp);
            }
        }
    }

    private List<List<Integer>> init(TreeNode root) {
        int height = getTreeHeight(root);
        List<List<Integer>> result = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            result.add(new ArrayList<>());
        }
        return result;
    }

    private int getTreeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = getTreeHeight(node.left);
        int right = getTreeHeight(node.right);
        return 1 + Math.max(left, right);
    }
}
