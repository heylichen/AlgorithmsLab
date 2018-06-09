package algorithms.strings.substring;

import algorithms.strings.alphabet.ABCAlphabet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class PracticeKMPSearcherTest {

  @Test
  public void constructDFATest() {
    PracticeKMPSearcher practiceKMPSearcher = new PracticeKMPSearcher();
    practiceKMPSearcher.setAlphabet(new ABCAlphabet());
    practiceKMPSearcher.compile("ABABAC");
    for (int[] ints : practiceKMPSearcher.getDfa()) {
      StringBuilder sb = new StringBuilder();
      for (int anInt : ints) {
        sb.append(anInt).append(" ");
      }
      log.info("{}",sb.toString());
    }
  }

  @Test
  public void searchTest() {
    PracticeKMPSearcher practiceKMPSearcher = new PracticeKMPSearcher();
    practiceKMPSearcher.setAlphabet(new ABCAlphabet());
    practiceKMPSearcher.compile("ABABAC");
    int index = practiceKMPSearcher.search("ACCABBBABABACCA");
    System.out.println(index);

    index = practiceKMPSearcher.search("ACCCAAABBBAA");
    System.out.println(index);
  }
}