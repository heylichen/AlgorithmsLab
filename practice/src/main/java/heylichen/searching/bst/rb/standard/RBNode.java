package heylichen.searching.bst.rb.standard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * reb black node
 *
 * @param <K> key
 * @param <V> value
 */
@Getter
@Setter
@NoArgsConstructor
public class RBNode<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private RBColor color;
    private RBNode<K, V> left;
    private RBNode<K, V> right;
    private RBNode<K, V> parent;

    public static final <K extends Comparable<K>, V> RBNode<K, V> newRed(K key, V value) {
        return  new RBNode<>(key, value, RBColor.RED);
    }

    public RBNode(K key, V value, RBColor color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }

    public RBNode(RBColor color) {
        this.color = color;
    }

    public void clearLinks() {
        parent = null;
        left = null;
        right = null;
    }

    public void bindRight(RBNode<K, V> newChild) {
        right = newChild;
        if (newChild != null) {
            newChild.parent = this;
        }
    }

    public void bindLeft(RBNode<K, V> newChild) {
        left = newChild;
        if (newChild != null) {
            newChild.parent = this;
        }
    }

    public boolean isRed() {
        return color == RBColor.RED;
    }

    public boolean isBlack() {
        return color == RBColor.BLACK;
    }

    public RBNode<K, V> getGrandParent() {
        return parent == null ? null : parent.getParent();
    }
}