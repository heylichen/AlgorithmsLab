package heylichen.alg.graph.tasks.path;

import java.util.Collection;

public interface MultipleSourceReachability {

    boolean marked(int v);

    Collection<Integer> getReachableVertices();
}
