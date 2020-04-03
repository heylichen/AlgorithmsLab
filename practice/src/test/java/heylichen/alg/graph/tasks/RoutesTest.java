package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.SymbolGraph;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RoutesTest extends AppTestContext {

    @Autowired
    private SymbolGraph routesSymbolGraph;

    @Test
    public void name() {
        listReachableAirports("JFK");

        listReachableAirports("LAX");
    }

    private void listReachableAirports(String fromAirport) {
        log.info(" listReachableAirports from {} ", fromAirport);
        int fromV = routesSymbolGraph.index(fromAirport);
        for (Integer adjacentVertex : routesSymbolGraph.getGraph().getAdjacentVertices(fromV)) {
            log.info("\t{}", routesSymbolGraph.name(adjacentVertex));
        }
    }
}
