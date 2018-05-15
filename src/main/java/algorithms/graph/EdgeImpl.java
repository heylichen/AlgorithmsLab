package algorithms.graph;

import java.util.Objects;

/**
 * Created by Chen Li on 2018/5/11.
 */
public class EdgeImpl implements Edge {

  private final Integer vertexA;
  private final Integer vertexB;
  private final Double weight;

  public EdgeImpl(Integer vertexA, Integer vertexB, Double weight) {
    this.vertexA = vertexA;
    this.vertexB = vertexB;
    this.weight = weight;
  }

  @Override
  public Integer either() {
    return vertexA;
  }

  @Override
  public Integer theOther(Integer v) {
    return vertexA.equals(v) ? vertexB : vertexA;
  }

  @Override
  public Double weight() {
    return weight;
  }

  public int compareTo(Edge that) {
    if (this.weight() < that.weight()) {
      return -1;
    } else if (this.weight() > that.weight()) {
      return +1;
    } else {
      return 0;
    }
  }

  public String toString() {
    return String.format("%d-%d %.2f", vertexA, vertexB, weight);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EdgeImpl edge = (EdgeImpl) o;
    return Objects.equals(vertexA, edge.vertexA) &&
           Objects.equals(vertexB, edge.vertexB);
  }

  @Override
  public int hashCode() {

    return Objects.hash(vertexA, vertexB);
  }
}
