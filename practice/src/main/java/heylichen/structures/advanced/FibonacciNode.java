package heylichen.structures.advanced;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class FibonacciNode<K extends Comparable<K>, V> {
    private K key;
    private V value;
    //keep structure
    private FibonacciNode<K, V> p;
    private FibonacciNode<K, V> child;
    private FibonacciNode<K, V> left;
    private FibonacciNode<K, V> right;
    // number of children
    private int degree;
    // ever lost a child since it became a child of another node
    private boolean marked;

    public FibonacciNode() {
    }

    public FibonacciNode(K key, V value) {
        this.key = key;
        this.value = value;
        p = child = null;
        left = right = this;
        degree = 0;
        marked = false;
    }

    public void addSibling(FibonacciNode<K, V> another) {
        FibonacciNode<K, V> rightEnd = this.right;
        FibonacciNode<K, V> anotherLeftEnd = another.left;

        this.setRight(another);
        another.setLeft(this);

        rightEnd.setLeft(anotherLeftEnd);
        anotherLeftEnd.setRight(rightEnd);
    }

    public void cutAllLinks() {
        cutInwardLinks();
        cutOutwardLinks();
    }

    public void cutInwardLinks() {
        if (hasNoSibling()) {
            return;
        }
        FibonacciNode<K, V> left = this.left;
        FibonacciNode<K, V> right = this.right;
        left.right = right;
        right.left = left;
    }


    private void cutOutwardLinks() {
        this.left = this.right = this;
    }

    public int compareTo(FibonacciNode<K, V> another) {
        if (another == null || another.getKey() == null) {
            return 1;
        }
        return this.key.compareTo(another.getKey());
    }

    public void incrementDegree() {
        degree++;
    }

    public void decreaseDegree() {
        degree--;
    }

    public boolean hasNoSibling() {
        return this == right;
    }

    public List<FibonacciNode<K, V>> getAllSiblings() {
        List<FibonacciNode<K, V>> list = new LinkedList<>();
        list.add(this);
        FibonacciNode<K, V> n = this.right;
        while (n != this) {
            list.add(n);
            n = n.right;
        }
        return list;
    }

}
