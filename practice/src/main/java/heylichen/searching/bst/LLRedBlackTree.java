package heylichen.searching.bst;

import lombok.Getter;
import lombok.Setter;

public class LLRedBlackTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public V get(K key) {
        Node<K, V> targetNode = getNode(root, key);
        return targetNode == null ? null : targetNode.getValue();
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        int compared = key.compareTo(node.key);
        if (compared == 0) {
            return node;
        } else if (compared > 0) {
            return getNode(node.getRight(), key);
        } else {
            return getNode(node.getLeft(), key);
        }
    }

    public void put(K key, V value) {
        root = putNode(root, key, value);
        root.setColor(NodeColor.BLACK);
        root.recalculateSize();
    }

    private Node<K, V> putNode(Node<K, V> node, K key, V value) {
        //insert or update key value
        if (node == null) {
            node = new Node<>(key, value);
            return node;
        }
        int compared = key.compareTo(node.getKey());
        if (compared == 0) {
            node.setValue(value);
            return node;
        } else if (compared < 0) {
            node.left = putNode(node.getLeft(), key, value);
        } else {
            node.right = putNode(node.getRight(), key, value);
        }

        //rotate to keep the left leaning read black tree properties
        Node<K, V> newRoot = node;
        if (isRed(newRoot.getRight())) {
            newRoot = leftRotate(newRoot);
        }
        if (isRed(newRoot.getLeft()) && isRed(newRoot.getLeft().getLeft())) {
            newRoot = rightRotate(newRoot);
        }
        if (isRed(newRoot.getLeft()) && isRed(newRoot.getRight())) {
            flipColor(newRoot);
        }
        return newRoot;
    }


    private Node<K, V> leftRotate(Node<K, V> node) {
        Node<K, V> oldRight = node.getRight();
        Node<K, V> rl = oldRight.getLeft();
        node.setRight(rl);
        oldRight.setLeft(node);

        NodeColor oldColor = node.color;
        node.setColor(NodeColor.RED);
        oldRight.setColor(oldColor);

        node.recalculateSize();
        oldRight.recalculateSize();
        return oldRight;
    }

    private Node<K, V> rightRotate(Node<K, V> node) {
        Node<K, V> oldLeft = node.getLeft();
        Node<K, V> lr = oldLeft.getRight();
        node.setLeft(lr);
        oldLeft.setRight(node);

        NodeColor oldColor = node.color;
        node.setColor(NodeColor.RED);
        oldLeft.setColor(oldColor);

        node.recalculateSize();
        oldLeft.recalculateSize();
        return oldLeft;
    }

    private void flipColor(Node<K, V> node) {
        node.getLeft().setColor(NodeColor.BLACK);
        node.getRight().setColor(NodeColor.BLACK);
        node.setColor(NodeColor.RED);
    }

    private static boolean isRed(Node node) {
        return node == null ? false : node.isRed();
    }

    @Getter
    @Setter
    public static final class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;

        private int size;

        private NodeColor color;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 0;
            this.color = NodeColor.RED;
            left = null;
            right = null;
        }

        public boolean isRed() {
            return color.isRed();
        }

        public void recalculateSize() {
            this.size = getSize(left) + getSize(right) + 1;
        }

        public static final int getSize(Node node) {
            return node == null ? 0 : node.getSize();
        }
    }
}