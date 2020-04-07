package heylichen.alg.graph.structure;

import com.google.common.base.Splitter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraphSource<E> implements GraphSource<E> {
    protected static final Splitter SPLITTER = Splitter.on(" ").omitEmptyStrings().trimResults();
    protected List<String> lines;
    protected int vertexCount;
    private List<E> edges;

    public AbstractGraphSource(List<String> lines) {
        this.lines = lines;
    }

    public AbstractGraphSource init() {
        if (lines == null || lines.size() < 1) {
            vertexCount = 0;
            return this;
        }
        vertexCount = Integer.parseInt(lines.get(0));
        if (lines.size() < 3) {
            edges = Collections.emptyList();
            return this;
        }
        edges = new LinkedList<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            E edge = parseEdge(line);
            edges.add(edge);
        }
        return this;
    }

    protected abstract E parseEdge(String line);

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public Iterable<E> getEdges() {
        return edges;
    }
}
