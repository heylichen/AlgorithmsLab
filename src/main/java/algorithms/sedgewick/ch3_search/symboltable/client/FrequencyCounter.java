package algorithms.sedgewick.ch3_search.symboltable.client;


import algorithms.sedgewick.ch3_search.symboltable.ST;
import algorithms.sedgewick.ch3_search.symboltable.SequentialSearchST;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Getter
@Setter
public class FrequencyCounter {
  private Logger logger = LoggerFactory.getLogger(FrequencyCounter.class);

  private ST<String, Integer> st;

  public void count(String fileClassPath, int minLength) {
    long start = System.currentTimeMillis();
    ClassPathResource cpr = new ClassPathResource(fileClassPath);
    Path filePath = null;
    Stream<String> lines = null;
    try {
      filePath = cpr.getFile().toPath();
      lines = Files.lines(filePath);
    } catch (IOException e) {
      logger.error("read lines error!", e);
      return;
    }
    long readFile = System.currentTimeMillis();

    AtomicInteger ai = new AtomicInteger(0);
    lines.forEach(
        line -> {
          String[] words = line.split("\\s");
          if (words == null || words.length == 0) {
            return;
          }
          for (String word : words) {
            if (word.length() < minLength) {
              continue;
            }
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
          }
        }
    );
    long countWords = System.currentTimeMillis();

    String maxKey = findMaxFrequencyKey(st);
    long findMax = System.currentTimeMillis();

    logger.info("minLength={} words, st size:{}, max frequency word is: \"{}\" which occurs {} times.", minLength, st.size(), maxKey, st.get(maxKey));
    logger.info("total: {} ms, readFile:{}, countWords:{}, findMax:{}.",
        findMax - start, readFile - start, countWords - readFile, findMax - countWords);

  }

  private String findMaxFrequencyKey(ST<String, Integer> st) {
    String max = "";
    st.put(max, 0);
    for (String word : st.keys())
      if (st.get(word) > st.get(max))
        max = word;
    return max;
  }


  public static void main(String[] args) {
    FrequencyCounter fc = new FrequencyCounter();
    ST<String, Integer> st = new SequentialSearchST<>();
    fc.setSt(st);
    fc.count("algorithms/sedgewick/ch3_search/symboltable/client/tale.txt", 2);
  }
}