package algorithms.strings.substring;

import algorithms.strings.alphabet.Alphabet;
import algorithms.strings.alphabet.EnumerateAlphabet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class PracticeKMPSearcherTest {

  private Alphabet alphabet = new EnumerateAlphabet("ABC");
  @Test
  public void constructDFATest() {
    PracticeKMPSearcher practiceKMPSearcher = new PracticeKMPSearcher();
    practiceKMPSearcher.setAlphabet(alphabet);
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
    practiceKMPSearcher.setAlphabet(alphabet);
    practiceKMPSearcher.compile("ABABAC");
    int index = practiceKMPSearcher.search("ACCABBBABABACCA");
    System.out.println(index);

    index = practiceKMPSearcher.search("ACCCAAABBBAA");
    System.out.println(index);
  }
}