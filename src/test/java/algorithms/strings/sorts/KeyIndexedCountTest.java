package algorithms.strings.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Splitter;
import com.heylichen.commons.resource.FileUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

/**
 * Created by Chen Li on 2017/12/21.
 */
public class KeyIndexedCountTest {
  @Test
  public void testCount() throws Exception {
    KeyIndexedCount count = new KeyIndexedCount();
    List<String> lines = FileUtils.readLines("algorithms/sedgewick/strings/sorts/keyIndexedCountingIn");
    Splitter splitter = Splitter.on(" ");
    List<IndexedEntry<String>> entries = new ArrayList<>();
    for (String line : lines) {
      List<String> fields = splitter.splitToList(line);
      if (CollectionUtils.isEmpty(fields)) {
        continue;
      }
      IndexedEntry<String> entry = new IndexedEntry<>(Integer.valueOf(fields.get(1)), fields.get(0));
      entries.add(entry);
    }
    System.out.println("------------before sorting");
    reportEntries(entries);


    List<IndexedEntry<String>> result = Arrays.asList(count.sort(entries.toArray(new IndexedEntry[entries.size()])));
    System.out.println("------------after sorting");
    reportEntries(result);
  }

  private void reportEntries(List<IndexedEntry<String>> result) {
    for (IndexedEntry<String> entry : result) {
      System.out.println(String.format("%1$-10s", entry.getItem()) + "\t" + entry.getKey());
    }
  }
}