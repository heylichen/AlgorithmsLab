package algorithms.strings.sorts;

/**
 * Created by Chen Li on 2018/2/19.
 */
public class LSDSort {

  private int radix = 256;
  private String[] aux;

  public void sort(String[] array, int fixedLength) {
    aux = new String[array.length];
    for (int i = fixedLength - 1; i >= 0; i--) {
      keyIndexedCounting(array, i);
    }
  }

  public void keyIndexedCounting(String[] inputArray, int keyIndex) {
    int[] counts = countFrequency(inputArray, keyIndex);
    counts = calculateIndices(counts);

    distribute(inputArray, counts, keyIndex);
  }

  private int[] countFrequency(String[] inputArray, int keyIndex) {
    int[] counts = new int[radix + 1];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }

    for (String entry : inputArray) {
      int key = entry.charAt(keyIndex);
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

  private void distribute(String[] inputArray, int[] counts, int keyIndex) {

    for (String entry : inputArray) {
      int key = entry.charAt(keyIndex);
      int targetIndex = counts[key];
      aux[targetIndex] = entry;
      counts[key]++;
    }
    System.arraycopy(aux, 0, inputArray, 0, aux.length);
  }

}
