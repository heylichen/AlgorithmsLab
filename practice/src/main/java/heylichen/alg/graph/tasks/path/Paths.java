package heylichen.alg.graph.tasks.path;

public interface Paths {

    boolean hasPathTo(int v);

    Iterable<Integer> pathTo(int v);
}
