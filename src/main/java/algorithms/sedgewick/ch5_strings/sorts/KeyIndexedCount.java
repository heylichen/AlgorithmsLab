package algorithms.sedgewick.ch5_strings.sorts;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Li on 2017/12/21.
 */
public class KeyIndexedCount {
  public <I> List<IndexedEntry<I>> sort(List<IndexedEntry<I>> inputArray) {
    int radix = getRadix(inputArray);
    int[] counts = countFrequency(inputArray, radix);
    counts = calculateIndices(counts);
    List<IndexedEntry<I>> result = distribute(inputArray, counts);
    return result;
  }

  private <I> int getRadix(List<IndexedEntry<I>> inputArray) {
    int max = -1;
    for (IndexedEntry<I> entry : inputArray) {
      if (entry.getKey() > max) {
        max = entry.getKey();
      }
    }
    return max;
  }

  private <I> int[] countFrequency(List<IndexedEntry<I>> inputArray, int r) {
    int[] counts = new int[r + 2];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (IndexedEntry<I> entry : inputArray) {
      int key = entry.getKey();
      counts[key + 1]++;
    }
    return counts;
  }

  private int[] calculateIndices(int[] counts) {
    if (counts.length <= 2) {
      return counts;
    }
    for (int i = 2; i < counts.length; i++) {
      counts[i] = counts[i] + counts[i - 1];
    }
    return counts;
  }

  private <I> List<IndexedEntry<I>> distribute(List<IndexedEntry<I>> inputArray, int[] counts) {

    IndexedEntry[] arr = (IndexedEntry[]) new IndexedEntry[inputArray.size()];
    for (IndexedEntry<I> entry : inputArray) {
      int key = entry.getKey();

      int targetIndex = counts[key];
      arr[targetIndex] = entry;
      counts[key]++;
    }

    return Arrays.asList(arr);
  }
}
