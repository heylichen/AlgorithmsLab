package heylichen.alg.graph.components;

import heylichen.alg.graph.structure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GraphConfig {

    @Bean
    public Graph tinyUndirectedGraph() throws IOException {
        GraphSource<Edge> gs = ClasspathGraphSource.create("/alg/graph/tinyG.txt");
        return new UndirectedGraph(gs);
    }

    @Bean
    public Graph mediumUndirectedGraph() throws IOException {
        GraphSource<Edge> gs = ClasspathGraphSource.create("alg/graph/mediumG.txt");
        return new UndirectedGraph(gs);
    }


    @Bean
    public SymbolGraph routesSymbolGraph() {
        return SymbolGraphBuilder.build("alg/graph/routes.txt", " ");
    }

    @Bean
    public SymbolGraph moviesSymbolGraph() {
        return SymbolGraphBuilder.build("alg/graph/movies-top-grossing.txt", "/");
    }
}
