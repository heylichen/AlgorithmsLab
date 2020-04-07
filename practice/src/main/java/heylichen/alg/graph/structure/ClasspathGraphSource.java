package heylichen.alg.graph.structure;

import heylichen.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class ClasspathGraphSource extends AbstractGraphSource<Edge> {

    public ClasspathGraphSource(List<String> lines) {
        super(lines);
    }

    public static final ClasspathGraphSource create(String classpath) throws IOException {
        List<String> lines = FileHelper.readLines(classpath);
        ClasspathGraphSource gs = new ClasspathGraphSource(lines);
        gs.init();
        return gs;
    }

    protected Edge parseEdge(String line) {
        List<String> pieces = SPLITTER.splitToList(line);
        int v = Integer.parseInt(pieces.get(0));
        int w = Integer.parseInt(pieces.get(1));
        return new Edge(v, w);
    }
}
