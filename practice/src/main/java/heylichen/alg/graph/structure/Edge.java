package heylichen.alg.graph.structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Edge {
    private int left;
    private int right;

    public Edge(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
