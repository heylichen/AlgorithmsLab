package heylichen.alg.graph.tasks.path;

public interface MultipleSourceReachability {

    boolean marked(int v);

    Iterable<Integer> getReachableVertices();
}
