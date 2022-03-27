package heylichen.problem.network;

public class QueueDelay {
    private final int R;
    private final int L;

    public QueueDelay(int r, int l) {
        R = r;
        L = l;
    }

    public double intensity(int a) {
        return L * a / (double) R;
    }

    //I(L/R)(1-I)
    public double calculateQueueDelay(int a) {
        double intensity = intensity(a);
        return intensity * (L / (double) R) * (1 - intensity);
    }
}
