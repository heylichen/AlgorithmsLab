package heylichen.alg.graph.structure;

public interface GraphSource<E> {

    int getVertexCount();

    Iterable<E> getEdges();
}
