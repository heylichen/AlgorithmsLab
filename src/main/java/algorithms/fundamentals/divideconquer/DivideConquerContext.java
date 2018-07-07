package algorithms.fundamentals.divideconquer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DivideConquerContext {

  @Bean
  public MatrixMultiply standardMM() {
    return new StandardMatrixMultiply();
  }

  @Bean
  public MatrixMultiply recursiveMM() {
    return new RecursiveMatrixMultiply();
  }

  @Bean
  public MatrixMultiplyDivideConquer strassenMethod() {
    return new StrassenMethod();
  }

  @Bean
  public MatrixMultiplyDivideConquer winogradMethod() {
    return new WinogradMethod();
  }

  @Bean
  @Scope(scopeName = "prototype")
  public MatrixMultiply standardStrassenMM() {
    StandardStrassenMM mm = new StandardStrassenMM();
    mm.setCutoffSize(1);
    mm.setMatrixMultiplyDivideConquer(strassenMethod());
    mm.setName("standardStrassenMM");
    return mm;
  }

  @Bean
  @Scope(scopeName = "prototype")
  public MatrixMultiply standardWinogradMM() {
    StandardStrassenMM mm = new StandardStrassenMM();
    mm.setCutoffSize(1);
    mm.setMatrixMultiplyDivideConquer(winogradMethod());
    mm.setName("standardWinogradMM");
    return mm;
  }

  @Bean
  @Scope(scopeName = "prototype")
  public MatrixMultiply dynamicPaddingStrassenMM() {
    DynamicPaddingStrassenMM mm = new DynamicPaddingStrassenMM();
    mm.setCutoffSize(1);
    mm.setMatrixMultiplyDivideConquer(strassenMethod());
    mm.setName("dynamicPaddingStrassenMM");
    return mm;
  }

  @Bean
  @Scope(scopeName = "prototype")
  public MatrixMultiply dynamicPeelingStrassenMM() {
    DynamicPeelingStrassenMM mm = new DynamicPeelingStrassenMM();
    mm.setCutoffSize(1);
    mm.setMatrixMultiplyDivideConquer(strassenMethod());
    mm.setName("dynamicPeelingStrassenMM");
    return mm;
  }

  @Bean
  @Scope(scopeName = "prototype")
  public MatrixMultiply dynamicPaddingWinogradMM() {
    DynamicPaddingStrassenMM mm = new DynamicPaddingStrassenMM();
    mm.setCutoffSize(1);
    mm.setMatrixMultiplyDivideConquer(winogradMethod());
    mm.setName("dynamicPaddingWinogradMM");
    return mm;
  }

  @Bean
  @Scope(scopeName = "prototype")
  public MatrixMultiply dynamicPeelingWinogradMM() {
    DynamicPeelingStrassenMM mm = new DynamicPeelingStrassenMM();
    mm.setCutoffSize(1);
    mm.setMatrixMultiplyDivideConquer(winogradMethod());
    mm.setName("dynamicPeelingWinogradMM");
    return mm;
  }
}
