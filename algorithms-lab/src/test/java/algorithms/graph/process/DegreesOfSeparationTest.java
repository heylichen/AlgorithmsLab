package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/6.
 */
public class DegreesOfSeparationTest extends AbstractContextTest {

  @Resource(name = "airportDegreesOfSeparation")
  private DegreesOfSeparation airportDegreesOfSeparation;

  @Test
  public void report() {
    airportDegreesOfSeparation.report("LAS");
    airportDegreesOfSeparation.report("DFW");
  }
}