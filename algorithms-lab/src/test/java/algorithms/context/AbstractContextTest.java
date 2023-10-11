package algorithms.context;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public abstract class AbstractContextTest {

  @Autowired
  private ApplicationContext appContext;

  public <T> T getBean(String name) {
    return (T) appContext.getBean(name);
  }
}
