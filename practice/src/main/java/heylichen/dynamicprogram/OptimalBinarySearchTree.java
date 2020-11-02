package heylichen.dynamicprogram;

import heylichen.problem.Node;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptimalBinarySearchTree {
    private final float[] p;
    private final float[] q;
    private final int n;
    private int[][] root;
    private float[][] w;
    private float[][] e;
    private Node<Integer> rootNode;
    public static final float NONE = -1000f;

    public OptimalBinarySearchTree(float[] p, float[] q, int n) {
        this.p = p;
        this.q = q;
        this.n = n;
    }

    public void build() {
        root = new int[n + 2][n + 1];
        w = new float[n + 2][n + 1];
        e = new float[n + 2][n + 1];
        calculateW();
        initE();
        buildTree(1, n);
        rootNode = constructTree(1, n);
    }

    private Node<Integer> constructTree(int from, int to) {
        if (from > to) {
            return null;
        }
        if (from == to) {
            return new Node<>(from, null, null);
        }
        int rootIndex = root[from][to];
        Node<Integer> left = constructTree(from, rootIndex - 1);
        Node<Integer> right = constructTree(rootIndex + 1, to);
        return new Node<>(rootIndex, left, right);
    }

    private void calculateW() {
        for (int i = 1, i1; i <= n + 1; i++) {
            i1 = i - 1;
            w[i][i1] = q[i1];
        }

        for (int l = 0; l <= n - 1; l++) {
            for (int i = 1; i <= n - l; i++) {
                int j = i + l;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
            }
        }
    }

    private void initE() {
        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            for (int j = i; j <= n; j++) {
                e[i][j] = NONE;
            }
        }
    }

    private float buildTree(int i, int j) {
        float cacheValue = e[i][j];
        if (cacheValue != NONE) {
            return cacheValue;
        }

        float minCost = Float.MAX_VALUE;
        float tmp;
        int bestRoot = 0;
        for (int r = i; r <= j; r++) {
            tmp = buildTree(i, r - 1) + buildTree(r + 1, j);
            if (tmp < minCost) {
                minCost = tmp;
                bestRoot = r;
            }
        }
        root[i][j] = bestRoot;
        return e[i][j] = w[i][j] + minCost;
    }

    public static void main(String[] args) {
        float[] p = new float[]{-1, 0.15f, 0.10f, 0.05f, 0.10f, 0.20f};
        float[] q = new float[]{0.05f, 0.10f, 0.05f, 0.05f, 0.05f, 0.10f};
        OptimalBinarySearchTree t = new OptimalBinarySearchTree(p, q, p.length - 1);
        t.build();

    }
}
