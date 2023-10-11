package algorithms.fundamentals.sub3_collection.impl;


import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
  public static int rank(int key, int[] a) {
    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else return mid;
    }
    return -1;
  }

  public static void main(String[] args) {
    Random random = new Random();

    int count = 100;
    int[] whitelist = new int[count];
    for (int i = 0; i < count; i++) {
      whitelist[i] = random.nextInt(9999);
    }
    Arrays.sort(whitelist);
    for (int i = 0; i < count / 3; i++) {
      int key = random.nextInt(9999);
      if (rank(key, whitelist) == -1)
        StdOut.println(key);
    }
  }
}