package algorithms.fundamentals;

/**
 * Created by Chen Li on 2018/4/26.
 */
public interface UnionFind {

  void init(int count);

  int find(int p);

  void union(int p, int q);

  boolean connected(int p, int q);

  int count();
}
