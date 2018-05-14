package algorithms.graph;

import java.util.Objects;


public class DirectedEdgeImpl implements DirectedEdge {

  private final Double weight;
  private final Integer from;
  private final Integer to;

  public DirectedEdgeImpl(Double weight, Integer from, Integer to) {
    this.weight = weight;
    this.from = from;
    this.to = to;
  }

  @Override
  public Double weight() {
    return weight;
  }

  @Override
  public Integer from() {
    return from;
  }

  @Override
  public Integer to() {
    return to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DirectedEdgeImpl that = (DirectedEdgeImpl) o;
    return Objects.equals(from, that.from) &&
           Objects.equals(to, that.to);
  }

  @Override
  public int hashCode() {

    return Objects.hash(from, to);
  }
}
