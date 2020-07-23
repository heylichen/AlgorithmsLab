package heylichen.searching.bst.rb.standard;

public class RBTree<K extends Comparable<K>, V> {
    public static final RBNode NULL = new RBNode(RBColor.BLACK);
    private RBNode<K, V> root = NULL;
    private int size;

    public RBTree() {
        size = 0;
    }

    public V get(K key) {
        RBNode<K, V> node = getNode(key);
        return isNull(node) ? null : node.getValue();
    }

    public void put(K key, V value) {
        RBNode<K, V> y = getNull();
        RBNode<K, V> x = getRoot();
        int compare = 0;
        while (!isNull(x)) {
            y = x;
            compare = key.compareTo(x.getKey());
            if (compare == 0) {
                x.setValue(value);
                return;
            } else if (compare < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        size += 1;

        RBNode<K, V> z = newNode(key, value);
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

    public boolean containsKey(K key) {
        return !isNull(getNode(key));
    }

    public boolean isEmpty() {
        return isNull(getRoot());
    }

    public void delete(K key) {
        RBNode<K, V> node = getNode(key);
        if (isNull(node)) {
            return;
        }
        delete(node);
        size--;
    }

    public int size() {
        return size;
    }

    private RBNode getNode(K key) {
        RBNode<K, V> node = getRoot();
        int compared = 0;
        while (!isNull(node)) {
            compared = key.compareTo(node.getKey());
            if (compared < 0) {
                node = node.getLeft();
            } else if (compared > 0) {
                node = node.getRight();
            } else {
                break;
            }
        }
        return node;
    }

    private void transplant(RBNode<K, V> old, RBNode<K, V> newNode) {
        RBNode<K, V> parent = old.getParent();
        newNode.setParent(parent);
        if (isNull(parent)) {
            setRoot(newNode);
        } else if (old == parent.getLeft()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    }

    private void delete(RBNode<K, V> z) {
        RBNode<K, V> y = z;
        RBColor yOriginalColor = y.getColor();
        RBNode<K, V> x;
        if (isNull(z.getLeft())) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else if (isNull(z.getRight())) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        } else {
            y = getMinNode(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y != z.getRight()) {
                //transplant will make x.parent not null if x is NULL
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            } else {
                //make sure x.parent is not null if x is NULL node
                x.setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (yOriginalColor == RBColor.BLACK) {
            deleteFixUp(x);
        }
    }

    private void deleteFixUp(RBNode<K, V> x) {
        while (!isRoot(x) && x.isBlack()) {
            if (x == x.getParent().getLeft()) {
                x = deleteFixUpLeft(x);
            } else {
                x = deleteFixUpRight(x);
            }
        }
        x.setColor(RBColor.BLACK);
    }

    private RBNode<K, V> deleteFixUpLeft(RBNode<K, V> x) {
        //case1
        RBNode<K, V> w = x.getParent().getRight();
        if (w.isRed()) {
            x.getParent().setColor(RBColor.RED);
            w.setColor(RBColor.BLACK);
            leftRotate(x.getParent());
            w = x.getParent().getRight();
        }
        //here w is black
        //case 4
        if (w.getRight().isRed()) {
            w.setColor(x.getParent().getColor());
            x.getParent().setColor(RBColor.BLACK);
            w.getRight().setColor(RBColor.BLACK);
            leftRotate(x.getParent());
            return getRoot();
        }
        //here w.right is black
        if (w.getLeft().isBlack()) {
            //case 2
            w.setColor(RBColor.RED);
            return x.getParent();
        } else {
            //case 3
            w.setColor(RBColor.RED);
            w.getLeft().setColor(RBColor.BLACK);
            rightRotate(w);
            return x;
        }
    }


    private RBNode<K, V> deleteFixUpRight(RBNode<K, V> x) {
        //case1
        RBNode<K, V> w = x.getParent().getLeft();
        if (w.isRed()) {
            x.getParent().setColor(RBColor.RED);
            w.setColor(RBColor.BLACK);
            rightRotate(x.getParent());
            w = x.getParent().getLeft();
        }

        //here w is black
        //case 4
        if (w.getLeft().isRed()) {
            w.setColor(x.getParent().getColor());
            x.getParent().setColor(RBColor.BLACK);
            w.getLeft().setColor(RBColor.BLACK);
            rightRotate(x.getParent());
            return getRoot();
        }
        //here w.left is black
        if (w.getRight().isBlack()) {
            //case 2
            w.setColor(RBColor.RED);
            return x.getParent();
        } else {
            //case 3
            w.setColor(RBColor.RED);
            w.getRight().setColor(RBColor.BLACK);
            leftRotate(w);
            return x;
        }
    }

    private RBNode<K, V> getMinNode(RBNode<K, V> node) {
        while (!isNull(node) && !isNull(node.getLeft())) {
            node = node.getLeft();
        }
        if (isNull(node)) {
            return null;
        } else {
            return node;
        }
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
            setRoot(y);
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
            setRoot(x);
        } else if (y == p.getLeft()) {
            p.setLeft(x);
        } else {
            p.setRight(x);
        }

        x.setRight(y);
        y.setParent(x);
    }

    public boolean isNull(RBNode node) {
        return node == NULL;
    }

    public RBNode<K, V> getNull() {
        return NULL;
    }

    private void setRoot(RBNode<K, V> node) {
        this.root = node;
    }

    private boolean isRoot(RBNode<K, V> node) {
        return this.root == node;
    }

    public RBNode<K, V> getRoot() {
        return root;
    }
}
