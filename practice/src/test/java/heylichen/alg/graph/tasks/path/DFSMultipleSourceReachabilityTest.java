package heylichen.alg.graph.tasks.path;

import heylichen.alg.graph.structure.directed.Digraph;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class DFSMultipleSourceReachabilityTest extends AppTestContext {
    @Autowired
    private Digraph tinyDirectedGraph;

    @Test
    public void name() {
        Set<Integer> sources = new HashSet<>();
        sources.add(3);
        sources.add(9);
        MultipleSourceReachability reachability = new DFSMultipleSourceReachability(tinyDirectedGraph, sources);
        testReachaility(reachability);
    }

    private void testReachaility(MultipleSourceReachability reachability ) {
        StringBuilder sb = new StringBuilder();
        for (Integer reachableVertex : reachability.getReachableVertices()) {
            sb.append(" ").append(reachableVertex);
        }
        log.info("reachable from 3,9: {}", sb.toString());
    }
}