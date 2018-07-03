package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatrixBlocks {

  private Matrix at11;
  private Matrix at12;
  private Matrix at21;
  private Matrix at22;

  public MatrixBlocks(Matrix at11, Matrix at12, Matrix at21, Matrix at22) {
    this.at11 = at11;
    this.at12 = at12;
    this.at21 = at21;
    this.at22 = at22;
  }
}
