package heylichen.searching.bst.rb.standard;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lichen
 * @date 2020/6/3 11:46
 * @desc delete method from introduction to algorithms
 */
public class BinarySearchTreeV2<K extends Comparable<K>, V> {
    private Node<K, V> root;
    private int size;

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node<K, V> getNode(K key) {
        if (root == null) {
            return null;
        }
        Node<K, V> node = root;
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

    public void put(K key, V value) {
        if (root == null) {
            size++;
            root = new Node<>(key, value);
            return;
        }

        Node<K, V> node = root;
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared == 0) {
                node.setValue(value);
                return;
            }
            if (compared < 0) {
                if (node.left == null) {
                    size++;
                    node.left = new Node<>(key, value, node);
                    return;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    size++;
                    node.right = new Node<>(key, value, node);
                    return;
                } else {
                    node = node.right;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Deque<Object> stack = new ArrayDeque<>();
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

    public void delete(K key) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            return;
        }
        size--;
        if (node.getLeft() == null) {
            transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            transplant(node, node.getLeft());
        } else {
            Node<K, V> successor = min(node.right);
            if (successor != node.right) {
                transplant(successor, successor.getRight());
                successor.bindRight(node.right);
            }
            transplant(node, successor);
            successor.bindLeft(node.left);
        }
        node.clearLinks();
    }

    private void transplant(Node<K, V> oldChild, Node<K, V> child) {
        Node<K, V> parent = oldChild.getParent();
        if (parent == null) {
            root = child;
        } else {
            if (parent.left == oldChild) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        if (child != null) {
            child.setParent(parent);
        }
    }

    public K min(K key) {
        Node<K, V> minNode = min(root);
        return minNode == null ? null : minNode.key;
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public K max(K key) {
        Node<K, V> node = max(root);
        return node == null ? null : node.key;
    }

    private Node<K, V> max(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return min(node.right);
        }
        Node<K, V> parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return max(node.left);
        }
        Node<K, V> parent = node.parent;
        while (parent != null && node == parent.left) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    @Getter
    @Setter
    private static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public void clearLinks() {
            parent = null;
            left = null;
            right = null;
        }

        public void bindRight(Node<K, V> newChild) {
            right = newChild;
            if (newChild != null) {
                newChild.parent = this;
            }
        }

        public void bindLeft(Node<K, V> newChild) {
            left = newChild;
            if (newChild != null) {
                newChild.parent = this;
            }
        }
    }
}