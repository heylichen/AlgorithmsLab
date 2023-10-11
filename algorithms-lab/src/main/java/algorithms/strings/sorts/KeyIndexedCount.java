package algorithms.strings.sorts;

/**
 * Created by Chen Li on 2017/12/21.
 */
public class KeyIndexedCount {

  public IndexedEntry[] sort(IndexedEntry[] inputArray) {
    int radix = getRadix(inputArray);
    int[] counts = countFrequency(inputArray, radix);
    counts = calculateIndices(counts);

    return distribute(inputArray, counts);
  }

  private int getRadix(IndexedEntry[] inputArray) {
    int max = -1;
    for (IndexedEntry entry : inputArray) {
      if (entry.getKey() > max) {
        max = entry.getKey();
      }
    }
    return max + 1;
  }

  private int[] countFrequency(IndexedEntry[] inputArray, int r) {
    int[] counts = new int[r + 1];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }

    for (IndexedEntry entry : inputArray) {
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

  private IndexedEntry[] distribute(IndexedEntry[] inputArray, int[] counts) {
    IndexedEntry[] arr = new IndexedEntry[inputArray.length];
    for (IndexedEntry entry : inputArray) {
      int key = entry.getKey();

      int targetIndex = counts[key];
      arr[targetIndex] = entry;
      counts[key]++;
    }

    return arr;
  }
}
