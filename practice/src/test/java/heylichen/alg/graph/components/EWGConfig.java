package heylichen.alg.graph.components;

import heylichen.alg.graph.structure.directed.EdgeWeightedDigraph;
import heylichen.alg.graph.structure.directed.EdgeWeightedDigraphSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class EWGConfig {
    @Bean
    public EdgeWeightedDigraph tinyEWD() throws IOException {
        EdgeWeightedDigraphSource gs = EdgeWeightedDigraphSource.create("alg/graph/ewd/tinyEWD.txt");
        return new EdgeWeightedDigraph(gs);
    }

}
