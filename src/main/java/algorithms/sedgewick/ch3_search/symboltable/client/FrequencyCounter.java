package algorithms.sedgewick.ch3_search.symboltable.client;


import algorithms.sedgewick.ch3_search.symboltable.ST;
import algorithms.sedgewick.common.utils.TimeWatch;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Getter
@Setter
public class FrequencyCounter {
  private Logger logger = LoggerFactory.getLogger(FrequencyCounter.class);
  public static final String WORD_SEPARATOR = "\\s";
  public static final String TAG_COUNT_WORDS = "TAG_COUNT_WORDS";
  public static final String TAG_FIND_MAX = "TAG_FIND_MAX";
  public static final String TAG_OTHER = "TAG_OTHER";
  private ST<String, Integer> st;
  private TimeWatch timeWatch;

  public void count(String fileClassPath, int minLength) {
    logger.info("start.");
    timeWatch = new TimeWatch();
    st.enableDraw(true);
    countFile(fileClassPath, minLength);
    st.enableDraw(false);
    String maxKey = findMaxFrequencyKey(st);

    logger.info("minLength={} words, st size:{}, max frequency word is: \"{}\" which occurs {} times.",
        minLength, st.size(), maxKey, st.get(maxKey));
    logger.info("total: {} ms, countWords: {} ms, findMax: {} ms.",
        timeWatch.getTotalCostMs(),
        timeWatch.getTagCostMs(TAG_COUNT_WORDS),
        timeWatch.getTagCostMs(TAG_FIND_MAX));
  }

  private void countFile(String fileClassPath, int minLength) {
    logger.info("countFile begin");
    try (BufferedReader reader = newReader(fileClassPath)) {
      timeWatch.tag(TAG_OTHER);
      String line;
      int totalWordCount = 0;
      while ((line = reader.readLine()) != null) {
        totalWordCount += processLine(line, minLength);
      }
      timeWatch.tag(TAG_COUNT_WORDS);
      logger.info("total processed word count {}", totalWordCount);
    } catch (Exception e) {
      throw new IllegalStateException("read file error!", e);
    }
  }

  private int processLine(final String line, final int minLength) {
    String[] words = line.split(WORD_SEPARATOR);
    if (words == null || words.length == 0) {
      return 0;
    }
    int processedWordCount = 0;
    for (String word : words) {
      if (word.length() < minLength) {
        continue;
      }
      processedWordCount++;
      Integer frequency = st.get(word);
      if (frequency == null) {
        st.put(word, 1);
      } else {
        st.put(word, frequency + 1);
      }
    }
    return processedWordCount;
  }

  private String findMaxFrequencyKey(ST<String, Integer> st) {
    timeWatch.tag(TAG_OTHER);
    String max = "";
    st.put(max, -1);
    for (String word : st.keys())
      if (st.get(word).intValue() > st.get(max).intValue())
        max = word;
    timeWatch.tag(TAG_FIND_MAX);
    return max;
  }

  private BufferedReader newReader(String fileClassPath) {
    ClassPathResource cpr = new ClassPathResource(fileClassPath);
    try {
      return new BufferedReader(new FileReader(cpr.getFile()));
    } catch (IOException e) {
      throw new IllegalStateException("read file error!", e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    TimeWatch timeWatch = new TimeWatch();
    timeWatch.tag("1");
    Thread.sleep(800);
    timeWatch.tag("2");
    System.out.println(timeWatch.getTagCostMs("1"));
    System.out.println(timeWatch.getTagCostMs("2"));
  }
}