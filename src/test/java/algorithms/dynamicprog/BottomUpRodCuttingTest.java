package algorithms.dynamicprog;

/**
 * Created by Chen Li on 2018/7/20.
 */
public class BottomUpRodCuttingTest extends AbstractRodCuttingTest{

  @Override
  protected RodCutting getInstance() {
    return new BottomUpRodCutting();
  }

}