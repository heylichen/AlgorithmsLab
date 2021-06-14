package heylichen.leetcode;

import heylichen.utils.SquareTreePrinter;

public class TreeNodeHelper {
    private static final SquareTreePrinter<TreeNode> PRINTER = new SquareTreePrinter<>(
            treeNode -> String.valueOf(treeNode.val), treeNode -> treeNode.left, treeNode -> treeNode.right
    );

    public static final void print(TreeNode node) {
        System.out.println(PRINTER.print(node));
    }
}
