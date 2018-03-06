package algorithms.sedgewick.strings.sorts;

import lombok.Getter;
import lombok.Setter;

public class MSDSort {

  @Getter
  @Setter
  private int radix = 256; // radix
  @Getter
  @Setter
  private int cutoff = 15; // cutoff for small subarrays
  private String[] aux; // auxiliary array for distribution
  private SubstringInsertionSort insertionSort = new SubstringInsertionSort();


  public void sort(String[] a) {
    int N = a.length;
    aux = new String[N];
    sort(a, 0, N - 1, 0);
  }

  private int charAt(String s, int d) {
    if (d < s.length()) {
      return s.charAt(d);
    } else {
      return -1;
    }
  }

  private void sort(String[] a, int lo, int hi,
                    int d) { // Sort from a[lo] to a[hi], starting at the dth character.
    if (hi <= lo + cutoff) {
      insertionSort.sort(a, lo, hi, d);
      return;
    }
    int[] count = new int[radix + 2]; // Compute frequency counts.
    for (int i = lo; i <= hi; i++) {
      count[charAt(a[i], d) + 2]++;
    }
    for (int r = 0; r < radix + 1; r++) // Transform counts to indices.
    {
      count[r + 1] += count[r];
    }
    for (int i = lo; i <= hi; i++) // Distribute.
    {
      aux[count[charAt(a[i], d) + 1]++] = a[i];
    }
    for (int i = lo; i <= hi; i++) // Copy back.
    {
      a[i] = aux[i - lo];
    }
    // Recursively sort for each character value.
    for (int r = 0; r < radix; r++) {
      sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
  }
}