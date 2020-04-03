package heylichen.alg.graph.structure;

import com.google.common.base.Splitter;
import heylichen.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

@Slf4j
public class SymbolGraphBuilder {

    public static final SymbolGraph build(String cp, String separator) {
        try {
            List<String> lines = FileHelper.readLines(cp);
            return build(lines, separator);
        } catch (Exception e) {
            log.error("failed to init SymbolGraph", e);
            throw new RuntimeException("failed to build graph", e);
        }
    }

    private static SymbolGraph build(List<String> lines, String separator) {
        Splitter splitter = Splitter.on(separator).trimResults();
        Map<String, Integer> nameKeyMap = new HashMap<>();
        Map<String, List<String>> adjacentMap = new HashMap<>();

        for (String line : lines) {
            List<String> verticesOfLine = splitter.splitToList(line);
            if (CollectionUtils.isEmpty(verticesOfLine)) {
                continue;
            }
            for (String v : verticesOfLine) {
                nameKeyMap.putIfAbsent(v, nameKeyMap.size());
            }
            if (verticesOfLine.size() <= 1) {
                continue;
            }
            String fromV = verticesOfLine.get(0);
            List<String> adjacentList = adjacentMap.computeIfAbsent(fromV, (String k) -> new ArrayList<>());
            for (int i = 1; i < verticesOfLine.size(); i++) {
                adjacentList.add(verticesOfLine.get(i));
            }
        }

        UndirectedGraph g = createGraph(nameKeyMap, adjacentMap);
        return new SymbolGraph(g, nameKeyMap);
    }

    private static UndirectedGraph createGraph(Map<String, Integer> nameKeyMap, Map<String, List<String>> adjacentMap) {
        int vertexCount = nameKeyMap.size();
        UndirectedGraph g = new UndirectedGraph(vertexCount);
        for (Map.Entry<String, List<String>> entry : adjacentMap.entrySet()) {
            String fromV = entry.getKey();
            List<String> adjacentList = entry.getValue();
            Integer fromVKey = nameKeyMap.get(fromV);
            for (String s : adjacentList) {
                Integer toKey = nameKeyMap.get(s);
                g.addEdge(fromVKey, toKey);
            }
        }
        return g;
    }
}
