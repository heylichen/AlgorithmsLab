package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CutoffMatrixMultiply implements MatrixMultiply{

  private int cutoffSize = 1;
  private String name;

  @Override
  public String toString() {
    return getName();
  }
}
