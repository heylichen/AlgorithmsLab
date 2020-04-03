package heylichen.alg.graph.tasks;

public interface CC {
    boolean connected(int v, int w);

    int getComponentsCount();

    int getComponentId(int v);
}
