package algorithms.sedgewick.strings.sorts;

import java.util.List;

import com.heylichen.commons.resource.FileUtils;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/19.
 */
public class LSDSortTest {

  @Test
  public void sortTest() {
    List<String> lines = FileUtils.readLines("algorithms/sedgewick/strings/sorts/lsdInput");
    String[] arr = lines.toArray(new String[lines.size()]);
    LSDSort lsdSort = new LSDSort();
    lsdSort.sort(arr, 7);
    System.out.println("ok");
  }
}