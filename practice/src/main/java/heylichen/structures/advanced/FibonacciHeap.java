package heylichen.structures.advanced;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FibonacciHeap<K extends Comparable<K>, V> {
    private FibonacciNode<K, V> min;
    private final K minKey;
    private int size;

    public FibonacciHeap(K minKey) {
        this.minKey = minKey;
        makeHeap();
    }

    private void makeHeap() {
        min = null;
        size = 0;
    }

    public void insert(K key, V value) {
        FibonacciNode<K, V> node = new FibonacciNode<>(key, value);
        if (min == null) {
            min = node;
        } else {
            min.addSibling(node);
            if (node.compareTo(min) < 0) {
                min = node;
            }
        }
        size++;
    }

    public FibonacciNode<K, V> min() {
        return min;
    }

    public void delete(FibonacciNode<K, V> x) {
        decreaseKey(x, minKey);
        extractMin();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public FibonacciHeap<K, V> merge(FibonacciHeap<K, V> h) {
        if (h == null || h.isEmpty()) {
            return this;
        }
        FibonacciHeap<K, V> result = new FibonacciHeap(minKey);
        min.addSibling(h.min);
        result.min = min.compareTo(h.min) <= 0 ? min : h.min;
        result.size = this.size + h.size;
        return result;
    }

    public FibonacciNode<K, V> extractMin() {
        FibonacciNode<K, V> z = min;
        if (z == null) {
            return null;
        }
        FibonacciNode<K, V> child = z.getChild();
        if (child != null) {
            //attention to iterate
            for (FibonacciNode<K, V> allSibling : child.getAllSiblings()) {
                allSibling.setP(null);
            }
            z.addSibling(child);
        }
        z.cutInwardLinks();
        if (z == z.getRight()) {
            min = null;
        } else {
            min = z.getRight();
            consolidate();
        }
        size--;
        return z;
    }

    private void consolidate() {
        int maxD = getMaxDegree();
        FibonacciNode<K, V>[] newRoots = new FibonacciNode[maxD];
        FibonacciNode<K, V> y;
        int d;
        //attention to get all root nodes
        for (FibonacciNode<K, V> x : min.getAllSiblings()) {
            //attention, what if didn't do this step?
            x.cutAllLinks();
            //x and y are root node
            d = x.getDegree();
            y = newRoots[d];
            //why stop when y!=null? no newRoot to link. no duplicate node in newRoots
            while (y != null) {
                if (x.compareTo(y) > 0) {
                    FibonacciNode<K, V> t = x;
                    x = y;
                    y = t;
                }
                link(x, y);
                newRoots[d] = null;
                d++;
                y = newRoots[d];
            }
            newRoots[d] = x;
        }

        //choose new min
        min = null;
        FibonacciNode<K, V> x = null;
        for (int i = 0; i < maxD; i++) {
            x = newRoots[i];
            if (x == null) {
                continue;
            }
            if (min == null) {
                min = x;
            } else {
                min.addSibling(x);
                if (x.compareTo(min) < 0) {
                    min = x;
                }
            }
        }
    }

    private void link(FibonacciNode<K, V> x, FibonacciNode<K, V> y) {
        if (x.getChild() == null) {
            x.setChild(y);
        } else {
            x.getChild().addSibling(y);
        }
        x.incrementDegree();
        y.setP(x);
        y.setMarked(false);
    }

    public void decreaseKey(FibonacciNode<K, V> x, K key) {
        if (x.getKey().compareTo(key) <= 0) {
            throw new IllegalArgumentException("new key must be smaller than current key");
        }
        x.setKey(key);
        FibonacciNode<K, V> y = x.getP();
        if (y != null && x.compareTo(y) < 0) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.compareTo(min) < 0) {
            min = x;
        }
    }

    //cut x from it's parent y
    private void cut(FibonacciNode<K, V> x, FibonacciNode<K, V> y) {
        removeChild(x, y);
        y.decreaseDegree();
        x.setMarked(false);
        x.setP(null);
        //add x to root list
        min.addSibling(x);
    }

    private void cascadingCut(FibonacciNode<K, V> y) {
        FibonacciNode<K, V> z = y.getP();
        if (z == null) {
            return;
        }
        if (!y.isMarked()) {
            y.setMarked(true);
        } else {
            cut(y, z);
            cascadingCut(z);
        }
    }

    private void removeChild(FibonacciNode<K, V> x, FibonacciNode<K, V> y) {
        if (x.hasNoSibling()) {
            y.setChild(null);
            return;
        }
        if (x == y.getChild()) {
            y.setChild(x.getRight());
        }
        x.cutInwardLinks();
    }

    private int getMaxDegree() {
        return (int) Math.floor(Math.log(size) / Math.log(1.618)) + 1;
    }
}