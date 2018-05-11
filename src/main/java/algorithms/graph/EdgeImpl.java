package algorithms.graph;

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
}
