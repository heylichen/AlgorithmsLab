package algorithms.strings.tries;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class TrieSTTest extends AbstractStringSTTest {

  @Autowired
  private Alphabet lowercaseAlphabet;

  @Override
  protected StringST<Integer> createST() {
    TrieST<Integer> st = new TrieST<>();
    st.setAlphabet(lowercaseAlphabet);
    return st;
  }

}