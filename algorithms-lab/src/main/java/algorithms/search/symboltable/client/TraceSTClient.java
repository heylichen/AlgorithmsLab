package algorithms.search.symboltable.client;

import java.util.Random;

import algorithms.search.ST;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2017/7/9.
 */
@Getter
@Setter
public class TraceSTClient {
  private ST<String, Integer> st;
  private Random random = new Random();
  private int valueSpace = 26;
  private char start = 'A';

  public void generateTableAndPrint(int size) {
    //generate
    for (int i = 0; i < size; i++) {
      st.put(nextRandomLetter(), i);
    }
    //print
    for (String s : st.keys()) {
      System.out.println(s + " " + st.get(s));
    }
  }

  private String nextRandomLetter() {
    int offset = random.nextInt(valueSpace);
    return String.valueOf((char) (start + offset));
  }
}
