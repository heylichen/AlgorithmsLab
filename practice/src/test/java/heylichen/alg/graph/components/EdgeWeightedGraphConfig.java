package heylichen.alg.graph.components;

import heylichen.alg.graph.structure.EdgeWeightedGraphSource;
import heylichen.alg.graph.structure.weighted.EdgeWeightedGraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author lichen
 * @date 2020/4/8 16:12
 * @desc
 */
@Configuration
public class EdgeWeightedGraphConfig {
    @Bean
    public EdgeWeightedGraph tinyEWG() throws IOException {
        EdgeWeightedGraphSource gs = EdgeWeightedGraphSource.create("alg/graph/weighted/tinyEWG.txt");
        return new EdgeWeightedGraph(gs);
    }

    @Bean
    public EdgeWeightedGraph mediumEWG() throws IOException {
        EdgeWeightedGraphSource gs = EdgeWeightedGraphSource.create("alg/graph/weighted/mediumEWG.txt");
        return new EdgeWeightedGraph(gs);
    }
}
