package heylichen.searching.bst.rb.standard;

import lombok.Getter;
import lombok.Setter;

public class RBTree<K extends Comparable<K>, V> {
    private T<K, V> t = new T();
    private int size;

    public V get(K key) {
        RBNode<K, V> node = getRoot();
        while (!isNull(node)) {
            int compare = key.compareTo(node.getKey());
            if (compare < 0) {
                node = node.getLeft();
            } else if (compare > 0) {
                node = node.getRight();
            } else {
                break;
            }
        }
        return isNull(node) ? null : node.getValue();
    }

    public void put(K key, V value) {
        RBNode<K, V> z = newNode(key, value);
        RBNode<K, V> y = getNull();
        RBNode<K, V> x = getRoot();
        int compare = 0;
        while (!isNull(x)) {
            y = x;
            compare = key.compareTo(x.getKey());
            if (compare < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        z.setParent(y);
        if (isNull(y)) {
            setRoot(z);
        } else if (compare < 0) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }
        //insert fix up
        insertFixup(z);
    }

    private RBNode<K, V> newNode(K key, V value) {
        RBNode<K, V> z = RBNode.newRed(key, value);
        z.setLeft(getNull());
        z.setRight(getNull());
        return z;
    }

    private void insertFixup(RBNode<K, V> z) {
        while (z.getParent().isRed()) {
            //z must not be new root, and z must have grand parent
            if (z.getParent() == z.getGrandParent().getLeft()) {
                z = insertFixupLeft(z);
            } else {
                z = insertFixupRight(z);
            }
        }
        getRoot().setColor(RBColor.BLACK);
    }

    private RBNode<K, V> insertFixupLeft(RBNode<K, V> z) {
        //z.parent is red, left of grandpa
        RBNode<K, V> grandpa = z.getGrandParent();
        RBNode<K, V> uncle = grandpa.getRight();
        if (uncle.isRed()) {
            z.getParent().setColor(RBColor.BLACK);
            uncle.setColor(RBColor.BLACK);
            grandpa.setColor(RBColor.RED);
            return grandpa;
        }
        RBNode<K, V> parent = z.getParent();
        // uncle is black
        if (z == parent.getRight()) {
            z = parent;
            leftRotate(z);
            parent = z.getParent();
        }
        if (z == parent.getLeft()) {
            rightRotate(grandpa);
            parent.setColor(RBColor.BLACK);
            grandpa.setColor(RBColor.RED);
        }
        return z;
    }

    private RBNode<K, V> insertFixupRight(RBNode<K, V> z) {
        //z.parent is red, left of grandpa
        RBNode<K, V> grandpa = z.getGrandParent();
        RBNode<K, V> uncle = grandpa.getLeft();
        if (uncle.isRed()) {
            z.getParent().setColor(RBColor.BLACK);
            uncle.setColor(RBColor.BLACK);
            grandpa.setColor(RBColor.RED);
            return grandpa;
        }
        RBNode<K, V> parent = z.getParent();
        // uncle is black
        if (z == parent.getLeft()) {
            z = parent;
            rightRotate(z);
            parent = z.getParent();
        }
        if (z == parent.getRight()) {
            leftRotate(grandpa);
            parent.setColor(RBColor.BLACK);
            grandpa.setColor(RBColor.RED);
        }
        return z;
    }


    public void leftRotate(RBNode x) {
        RBNode y = x.getRight();
        x.setRight(y.getLeft());
        if (!isNull(y.getLeft())) {
            y.getLeft().setParent(x);
        }

        RBNode p = x.getParent();
        y.setParent(p);
        if (isNull(p)) {
            t.setRoot(y);
        } else if (x == p.getLeft()) {
            p.setLeft(y);
        } else {
            p.setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }

    public void rightRotate(RBNode y) {
        RBNode x = y.getLeft();
        y.setLeft(x.getRight());
        if (!isNull(x.getRight()))
            x.getRight().setParent(y);

        RBNode p = y.getParent();
        x.setParent(p);
        if (isNull(p)) {
            t.setRoot(x);
        } else if (y == p.getLeft()) {
            p.setLeft(x);
        } else {
            p.setRight(x);
        }

        x.setRight(y);
        y.setParent(x);
    }

    public boolean isNull(RBNode node) {
        return node == T.NULL;
    }

    public RBNode<K, V> getNull() {
        return T.NULL;
    }

    private void setRoot(RBNode<K, V> node) {
        t.setRoot(node);
    }

    public RBNode<K, V> getRoot() {
        return t.getRoot();
    }

    @Getter
    @Setter
    private static class T<K extends Comparable<K>, V> {
        private RBNode<K, V> root = NULL;
        public static final RBNode NULL = new RBNode();
    }
}
