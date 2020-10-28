package heylichen.dynamicprogram;

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
        System.out.println();
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

    public static void main(String[] args) {
        float[] p = new float[]{-1, 0.15f, 0.10f, 0.05f, 0.10f, 0.20f};
        float[] q = new float[]{0.05f, 0.10f, 0.05f, 0.05f, 0.05f, 0.10f};
        OptimalBinarySearchTree t = new OptimalBinarySearchTree(p, q, p.length - 1);
        t.build();

    }
}
