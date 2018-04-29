package algorithms.strings.sorts;

import java.util.List;

import algorithms.sorting.compare.datagen.CAPlatesGenerator;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/3/6.
 */
public class MSDSortTest {

  private CAPlatesGenerator generator = new CAPlatesGenerator();

  @Test
  public void sortTest() {
    List<String> lines = generator.randomPlate(10);
    String[] arr = lines.toArray(new String[lines.size()]);
    MSDSort sort = new MSDSort();
    sort.sort(arr);
    System.out.println(JSON.toJSONString(arr));
  }

  @Test
  public void cutoffTest() {
    int times = 20;
    List<String> lines = generator.randomPlate(100000);
    String[] arr = null;

    MSDSort msd = new MSDSort();
    msd.setCutoff(1);
    arr = lines.toArray(new String[lines.size()]);
    run(msd, arr, times);

    msd.setCutoff(10);
    arr = lines.toArray(new String[lines.size()]);
    run(msd, arr, times);

    msd.setCutoff(15);
    arr = lines.toArray(new String[lines.size()]);
    run(msd, arr, times);
  }

  private void run(MSDSort msd, String[] arr, int times) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < times; i++) {
      msd.sort(arr);
    }
    long end = System.currentTimeMillis();
    System.out.println((end - start) + " ms for " + times + " times.");
  }

}