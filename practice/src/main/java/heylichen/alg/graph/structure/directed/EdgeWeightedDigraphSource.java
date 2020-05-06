package heylichen.alg.graph.structure.directed;

import heylichen.alg.graph.structure.AbstractGraphSource;
import heylichen.utils.FileHelper;

import java.io.IOException;
import java.util.List;

public class EdgeWeightedDigraphSource extends AbstractGraphSource<DirectedEdge> {

    public EdgeWeightedDigraphSource(List<String> lines) {
        super(lines);
    }

    public static final EdgeWeightedDigraphSource create(String classpath) throws IOException {
        List<String> lines = FileHelper.readLines(classpath);
        EdgeWeightedDigraphSource gs = new EdgeWeightedDigraphSource(lines);
        gs.init();
        return gs;
    }

    @Override
    protected DirectedEdge parseEdge(String line) {
        List<String> pieces = SPLITTER.splitToList(line);
        if (pieces == null || pieces.size() != 3) {
            throw new IllegalArgumentException("weighted edge format error : " + line);
        }
        Integer v = Integer.valueOf(pieces.get(0));
        Integer w = Integer.valueOf(pieces.get(1));
        Double weight = Double.valueOf(pieces.get(2));
        return new DirectedEdge(v, w, weight);
    }


}
