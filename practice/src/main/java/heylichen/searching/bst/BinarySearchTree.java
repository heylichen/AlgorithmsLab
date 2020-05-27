package heylichen.searching.bst;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lichen
 * @date 2020/5/25 15:48
 * @desc
 */
public class BinarySearchTree<K extends Comparable<K>, V>  implements SymbolTable<K, V>  {
    @Getter
    private Node<K, V> root;

    public V get(K key) {
        Node<K, V> node = get(root, key);
        return root == null ? null : node.getValue();
    }

    private Node<K, V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compared = key.compareTo(node.key);
        if (compared == 0) {
            return node;
        } else if (compared < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public int size() {
        return Node.getSize(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        iterate(root, keys);
        return keys;
    }

    private void iterate(Node<K, V> node, Set<K> keys) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            keys.add(node.getKey());
            return;
        }
        if (node.getLeft() != null) {
            iterate(node.left, keys);
        }
        keys.add(node.getKey());
        if (node.getRight() != null) {
            iterate(node.right, keys);
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            node = new Node<>(key, value);
            return node;
        }
        int compared = key.compareTo(node.key);
        if (compared == 0) {
            node.setValue(value);
            return node;
        } else if (compared < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.recalculateSize();
        return node;
    }

    public void deleteMin() {
        if (root == null) {
            return;
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.left = deleteMin(node.getLeft());
        node.recalculateSize();
        return node;
    }

    public void deleteMax() {
        if (root == null) {
            return;
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.getRight() == null) {
            return node.getLeft();
        }
        node.right = deleteMax(node.getRight());
        node.recalculateSize();
        return node;
    }

    public void delete(K key) {
        if (root == null) {
            return;
        }
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compared = key.compareTo(node.key);
        if (compared < 0) {
            node.left = delete(node.left, key);
        } else if (compared > 0) {
            node.right = delete(node.right, key);
        } else {
            node = deleteTheNode(node);
        }
        if (node != null) {
            node.recalculateSize();
        }
        return node;
    }

    private Node<K, V> deleteTheNode(Node<K, V> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        if (node.getRight() == null) {
            return node.getLeft();
        }

        Node<K, V> right = node.getRight();
        Node<K, V> successor = minNode(right);
        right = deleteMin(right);
        successor.setLeft(node.getLeft());
        successor.setRight(right);
        return successor;
    }

    //---------------------------order operations -------------
    public K min() {
        Node<K, V> node = minNode(root);
        return node == null ? null : node.getKey();
    }

    public Node<K, V> minNode(Node<K, V> node) {
        while (node != null && node.getLeft() != null) {
            node = node.left;
        }
        return node;
    }

    public K max() {
        Node<K, V> node = root;
        while (node != null && node.getRight() != null) {
            node = node.right;
        }
        return node == null ? null : node.getKey();
    }

    //largest key less than or equal to key
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
