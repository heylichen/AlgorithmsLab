package algorithms.sedgewick.sorting.heap.application;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.heylichen.commons.resource.FileUtils;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class TopMTest {

  @Test
  public void getTopTest() throws Exception{
    String cp = "algorithms/sorting/heap/application/article.txt";
    File f = FileUtils.getFile(cp);
    In in = new In(f);
    TopM topM = new TopM();
    String[] top10 = topM.getTop(in, 10);
    System.out.println(JSON.toJSONString(top10));
  }

}