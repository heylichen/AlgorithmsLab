package heylichen.alg.graph.structure;

import com.google.common.base.Splitter;
import heylichen.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ClasspathGraphSource implements GraphSource<Edge> {
    public static int vertexCount;
    public static final Splitter SPLITTER = Splitter.on(" ").omitEmptyStrings().trimResults();
    private List<Edge> edges;

    public ClasspathGraphSource(String classpath) {
        try {
            List<String> lines = FileHelper.readLines(classpath);
            init(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(List<String> lines) {
        if (lines == null || lines.size() < 1) {
            vertexCount = 0;
            return;
        }
        vertexCount = Integer.parseInt(lines.get(0));
        if (lines.size() < 3) {
            edges = Collections.emptyList();
            return;
        }
        edges = new LinkedList<>();
        for (int i = 2; i < lines.size(); i++) {
            List<String> pieces = SPLITTER.splitToList(lines.get(i));
            int v = Integer.parseInt(pieces.get(0));
            int w = Integer.parseInt(pieces.get(1));
            edges.add(new Edge(v, w));
        }
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public Iterable<Edge> getEdges() {
        return edges;
    }
}
