package heylichen.alg.graph.structure;

import heylichen.alg.graph.structure.weighted.WeightedEdge;
import heylichen.utils.FileHelper;

import java.io.IOException;
import java.util.List;

public class EdgeWeightedGraphSource extends AbstractGraphSource<WeightedEdge> {

    public EdgeWeightedGraphSource(List<String> lines) {
        super(lines);
    }

    public static final EdgeWeightedGraphSource create(String classpath) throws IOException {
        List<String> lines = FileHelper.readLines(classpath);
        EdgeWeightedGraphSource gs = new EdgeWeightedGraphSource(lines);
        gs.init();
        return gs;
    }

    @Override
    protected WeightedEdge parseEdge(String line) {
        List<String> pieces = SPLITTER.splitToList(line);
        if (pieces == null || pieces.size() != 3) {
            throw new IllegalArgumentException("weighted edge format error : " + line);
        }
        Integer v = Integer.valueOf(pieces.get(0));
        Integer w = Integer.valueOf(pieces.get(1));
        Double weight = Double.valueOf(pieces.get(2));
        return new WeightedEdge(v, w, weight);
    }


}
