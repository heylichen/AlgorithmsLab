package heylichen.alg.graph.structure;

import java.util.Map;

public class SymbolGraph {
    private Graph graph;
    private Map<String,Integer> nameToIndexMap;
    private String[] names;

    public SymbolGraph(Graph graph,  Map<String, Integer> nameKeyMap) {
        this.nameToIndexMap = nameKeyMap;
        this.graph = graph;
        this.names = new String[nameKeyMap.size()];
        for (Map.Entry<String, Integer> entry : nameToIndexMap.entrySet()) {
            String name = entry.getKey();
            Integer index = entry.getValue();
            names[index.intValue()] = name;
        }
    }


    public boolean contains(String key) {
        return nameToIndexMap.containsKey(key);
    }

    public int index(String key) {
        return nameToIndexMap.get(key);
    }

    public String name(int v) {
        return names[v];
    }

    public Graph getGraph() {
        return graph;
    }
}