package algorithms.graph;

public interface Edge extends Comparable<Edge> {

  Integer either();

  Integer theOther(Integer v);

  Double weight();
}
