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

    @Bean
    public EdgeWeightedDigraph mediumEWD() throws IOException {
        EdgeWeightedDigraphSource gs = EdgeWeightedDigraphSource.create("alg/graph/ewd/mediumEWD.txt");
        return new EdgeWeightedDigraph(gs);
    }


    @Bean
    public EdgeWeightedDigraph ewd1000() throws IOException {
        EdgeWeightedDigraphSource gs = EdgeWeightedDigraphSource.create("alg/graph/ewd/1000EWD.txt");
        return new EdgeWeightedDigraph(gs);
    }

}
