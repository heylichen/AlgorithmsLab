package heylichen.utils;

import heylichen.problem.Node;

public class TreeComparator {

    public static <T extends Comparable<T>> boolean isEqualTree(Node<T> node, Node<T> node2) {
        if (node == node2) {
            return true;
        }
        boolean aNull = node == null ? true : node.getData() == null;
        boolean bNull = node2 == null ? true : node2.getData() == null;
        if (aNull != bNull) {
            return false;
        }

        boolean dataEquals = node.getData().equals(node2.getData());
        if (!dataEquals) {
            return false;
        }
        return isEqualTree(node.getLeft(), node2.getLeft()) && isEqualTree(node.getRight(), node2.getRight());
    }

}
