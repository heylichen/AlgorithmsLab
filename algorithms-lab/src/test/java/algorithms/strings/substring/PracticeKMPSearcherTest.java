package algorithms.strings.substring;

import algorithms.context.AppTestContext;
import algorithms.strings.alphabet.Alphabet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestContext.class)
public class PracticeKMPSearcherTest {

  @Autowired
  private Alphabet abcAlphabet;

  @Test
  public void constructDFATest() {
    PracticeKMPSearcher practiceKMPSearcher = new PracticeKMPSearcher();
    practiceKMPSearcher.setAlphabet(abcAlphabet);
    practiceKMPSearcher.compile("ABABAC");
    for (int[] ints : practiceKMPSearcher.getDfa()) {
      StringBuilder sb = new StringBuilder();
      for (int anInt : ints) {
        sb.append(anInt).append(" ");
      }
      log.info("{}", sb.toString());
    }
  }

  @Test
  public void searchTest() {
    PracticeKMPSearcher practiceKMPSearcher = new PracticeKMPSearcher();
    practiceKMPSearcher.setAlphabet(abcAlphabet);
    practiceKMPSearcher.compile("ABABAC");
    int index = practiceKMPSearcher.search("ACCABBBABABACCA");
    System.out.println(index);

    index = practiceKMPSearcher.search("ACCCAAABBBAA");
    System.out.println(index);
  }
}