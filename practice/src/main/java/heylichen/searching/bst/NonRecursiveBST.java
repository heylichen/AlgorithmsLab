package heylichen.searching.bst;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author lichen
 * @date 2020/5/25 15:48
 * @desc
 */
public class NonRecursiveBST<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    @Getter
    private Node<K, V> root;

    @Override
    public V get(K key) {
        Node<K, V> node = get(root, key);
        return root == null ? null : node.getValue();
    }

    private Node<K, V> get(Node<K, V> node, K key) {
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared == 0) {
                return node;
            } else if (compared < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return Node.getSize(root);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            return;
        }

        Node<K, V> node = root;
        List<Node<K, V>> parents = new ArrayList<>();
        int compared = 0;
        while (node != null) {
            compared = key.compareTo(node.key);
            if (compared == 0) {
                node.setValue(value);
                return;
            }
            parents.add(node);
            if (compared < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> parent = parents.get(parents.size() - 1);
        if (compared < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        recalculateSize(parents);
    }

    private void recalculateSize(List<Node<K, V>> parents) {
        if (parents.isEmpty()) {
            return;
        }
        for (int i = parents.size() - 1; i >= 0; i--) {
            parents.get(i).recalculateSize();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Deque<Object> stack = new ArrayDeque<>(root.size);
        stack.push(root);

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj instanceof Node) {
                Node<K, V> node = (Node<K, V>) obj;
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                stack.push(node.getKey());
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
            } else {
                keys.add((K) obj);
            }
        }
        return keys;
    }


    @Override
    public void deleteMin() {
        if (root == null) {
            return;
        }
        DeletedInfo<K, V> deletedInfo = deleteMin(root);
        if (deletedInfo.isRootNodeDeleted()) {
            root = deletedInfo.getNewRoot();
        }
    }

    /**
     * delete the min node in the tree rooted at node
     *
     * @param node
     * @return not null, if the deleted min node is root node of the tree. returned node is the new root node.
     * null if the deleted min node is not the root node of the tree.
     */
    private DeletedInfo<K, V> deleteMin(Node<K, V> node) {
        List<Node<K, V>> parents = new ArrayList<>();
        Node<K, V> deletingNode = node;
        while (deletingNode.getLeft() != null) {
            parents.add(deletingNode);
            deletingNode = deletingNode.left;
        }
        DeletedInfo<K, V> deletedInfo = deleteMinOrMax(deletingNode, parents);
        return deletedInfo;
    }

    @Override
    public void deleteMax() {
        if (root == null) {
            return;
        }
        List<Node<K, V>> parents = new ArrayList<>();
        Node<K, V> deletingNode = root;
        while (deletingNode.getRight() != null) {
            parents.add(deletingNode);
            deletingNode = deletingNode.right;
        }
        deleteMinOrMax(deletingNode, parents);
    }

    private DeletedInfo<K, V> deleteMinOrMax(Node<K, V> node, List<Node<K, V>> parents) {
        DeletedInfo<K, V> deletedInfo = deleteFromParent(node, parents);
        if (deletedInfo.isRootNodeDeleted()) {
            root = deletedInfo.getNewRoot();
        }
        recalculateSize(parents);
        return deletedInfo;
    }

    /**
     * delete from parent
     *
     * @param node
     * @param parents
     * @return not null if parents is not empty
     * null if parents is empty
     */
    private DeletedInfo<K, V> deleteFromParent(Node<K, V> node, List<Node<K, V>> parents) {
        Node<K, V> child = node.getLeft() == null ? node.getRight() : node.getLeft();
        if (parents.isEmpty()) {
            return new DeletedInfo<>(node, child, true);
        }
        Node<K, V> parent = parents.get(parents.size() - 1);
        if (node == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
        return new DeletedInfo<>(node, child, false);
    }

    @Override
    public void delete(K key) {
        if (root == null) {
            return;
        }
        List<Node<K, V>> parents = new ArrayList<>();
        Node<K, V> node = root;
        Node<K, V> deleteNode = null;
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared == 0) {
                deleteNode = node;
                break;
            }
            parents.add(node);
            if (compared < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (deleteNode == null) {
            return;
        }
        deleteNode(deleteNode, parents);
    }

    private void deleteNode(Node<K, V> deleteNode, List<Node<K, V>> parents) {
        if (deleteNode.getLeft() == null || deleteNode.getRight() == null) {
            DeletedInfo<K, V> deletedInfo = deleteFromParent(deleteNode, parents);
            if (deletedInfo.isRootNodeDeleted()) {
                root = deletedInfo.getNewRoot();
            } else {
                recalculateSize(parents);
            }
            return;
        }
        deleteNodeWithFullChildren(deleteNode, parents);
    }

    private void deleteNodeWithFullChildren(Node<K, V> deleteNode, List<Node<K, V>> parents) {
        DeletedInfo<K, V> deletedInfo = deleteMin(deleteNode.right);
        if (deletedInfo.isRootNodeDeleted()) {
            deleteNode.setRight(deletedInfo.getNewRoot());
        }
        Node<K, V> successor = deletedInfo.deletedNode;
        deleteNode.setKey(successor.key);
        deleteNode.setValue(successor.getValue());
        deleteNode.recalculateSize();
        recalculateSize(parents);
    }

    //---------------------------order operations -------------
    @Override
    public K min() {
        Node<K, V> node = minNode(root);
        return node == null ? null : node.getKey();
    }

    private Node<K, V> minNode(Node<K, V> node) {
        while (node != null && node.getLeft() != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public K max() {
        Node<K, V> node = root;
        while (node != null && node.getRight() != null) {
            node = node.right;
        }
        return node == null ? null : node.getKey();
    }

    //largest key less than or equal to key
    @Override
    public K floor(K key) {
        K candidate = null;
        Node<K, V> node = root;
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared == 0) {
                return key;
            } else if (compared < 0) {
                node = node.left;
            } else {
                candidate = node.key;
                node = node.right;
            }
        }
        return candidate;
    }

    @Override
    public K ceiling(K key) {
        K candidate = null;
        Node<K, V> node = root;
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared == 0) {
                return key;
            } else if (compared < 0) {
                candidate = node.key;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return candidate;
    }

    //number of keys less than key
    @Override
    public int rank(K key) {
        int rank = 0;
        Node<K, V> node = root;
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared == 0) {
                return rank;
            } else if (compared < 0) {
                node = node.left;
            } else {
                rank += Node.getSize(node.left) + 1;
                node = node.right;
            }
        }
        return rank;
    }

    //key of rank k
    @Override
    public K select(int rank) {
        Node<K, V> node = root;
        while (node != null) {
            int leftSize = Node.getSize(node.left);
            if (rank == leftSize) {
                return node.key;
            } else if (rank < leftSize) {
                node = node.left;
            } else {
                rank -= (leftSize + 1);
                node = node.right;
            }
        }
        return node == null ? null : node.key;
    }

    @Getter
    private static class DeletedInfo<K extends Comparable<K>, V> {
        private final Node<K, V> deletedNode;
        private final Node<K, V> newRoot;
        private final boolean rootNodeDeleted;

        public DeletedInfo(Node<K, V> deletedNode, Node<K, V> newRoot, boolean rootNodeDeleted) {
            this.deletedNode = deletedNode;
            this.newRoot = newRoot;
            this.rootNodeDeleted = rootNodeDeleted;
        }
    }

    @Getter
    @Setter
    static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private int size;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }

        public static int getSize(Node node) {
            return node == null ? 0 : node.getSize();
        }

        public void recalculateSize() {
            size = getSize(left) + getSize(right) + 1;
        }
    }
}