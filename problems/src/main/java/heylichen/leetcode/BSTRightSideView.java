package heylichen.leetcode;

import java.util.*;

public class BSTRightSideView {

    public List<Integer> rightSideView(TreeNode root)  {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> levels = init(root);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        queue.add(null);

        int levelIndex = 0;
        while (!queue.isEmpty()) {
            //process all nodes of the same level
            List<Integer> currentLevel = levels.get(levelIndex);
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

        List<Integer> result = new ArrayList<>();
        for (List<Integer> level : levels) {
            result.add(level.get(level.size() - 1));
        }
        return result;
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
