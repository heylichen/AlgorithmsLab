package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.SymbolGraph;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class DegreesOfSeparationTest extends AppTestContext {

    @Autowired
    private SymbolGraph moviesSymbolGraph;

    @Test
    public void name() {
        DegreesOfSeparation degreesOfSeparation = new DegreesOfSeparation(moviesSymbolGraph, "Cruise, Tom");
        testViewPath(degreesOfSeparation, "Kidman, Nicole");
        testViewPath(degreesOfSeparation, "Carrey, Jim");
        testViewPath(degreesOfSeparation, "Jay, Tony");


    }

    private void testViewPath(DegreesOfSeparation degreesOfSeparation, String target) {
        log.info("---------path to {}", target);
        Iterable<String> path = degreesOfSeparation.shortestPathTo(target);
        viewPath(path);
    }

    private void viewPath(Iterable<String> path) {

        for (String s : path) {
            log.info("\t{}", s);
        }
    }
}