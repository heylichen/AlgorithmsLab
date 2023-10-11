package algorithms.sorting.heap.application;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class Multiway {

  public List<String> merge(In[] streams) {
    int streamCount = streams.length;
    IndexMinPQ<String> pq = new IndexMinPQ<>(streamCount);
    for (int i = 0; i < streamCount; i++) {
      if (!streams[i].isEmpty()) {
        pq.insert(i, streams[i].readString());
      }
    }
    //
    List<String> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      result.add(pq.minKey());
      int i = pq.delMin();
      if (!streams[i].isEmpty()) {
        pq.insert(i, streams[i].readString());
      }
    }
    return result;
  }
}
