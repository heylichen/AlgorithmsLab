package algorithms.sedgewick.sorting.heap.application;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.heylichen.commons.resource.FileUtils;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class MultiwayTest {

  @Test
  public void mergeTest() {
    In[] ins = new In[]{new In(FileUtils.getFile("algorithms/sorting/heap/application/line1.txt")),
                        new In(FileUtils.getFile("algorithms/sorting/heap/application/line2.txt")),
                        new In(FileUtils.getFile("algorithms/sorting/heap/application/line3.txt"))};
    Multiway multiway = new Multiway();
    List<String> result = multiway.merge(ins);
    System.out.println(JSON.toJSONString(result));

  }
}