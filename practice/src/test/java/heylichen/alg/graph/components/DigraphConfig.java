package heylichen.alg.graph.components;

import heylichen.alg.graph.structure.ClasspathGraphSource;
import heylichen.alg.graph.structure.Edge;
import heylichen.alg.graph.structure.GraphSource;
import heylichen.alg.graph.structure.directed.Digraph;
import heylichen.alg.graph.structure.directed.SimpleDigraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DigraphConfig {

    @Bean
    public Digraph tinyDirectedGraph() throws IOException {
        return createSimpleDigraph("alg/graph/digraph/tinyDG.txt");
    }

    @Bean
    public Digraph tinyDAG() throws IOException {
        return createSimpleDigraph("alg/graph/digraph/tinyDAG.txt");
    }

    private Digraph createSimpleDigraph(String path) throws IOException {
        GraphSource<Edge> gs = ClasspathGraphSource.create(path);
        SimpleDigraph simpleDigraph = new SimpleDigraph(gs.getVertexCount());
        for (Edge edge : gs.getEdges()) {
            simpleDigraph.addEdge(edge.getLeft(), edge.getRight());
        }
        return simpleDigraph;
    }
}
