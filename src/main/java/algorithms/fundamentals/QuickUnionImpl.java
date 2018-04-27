package algorithms.fundamentals;

/**
 * Created by Chen Li on 2018/4/26.
 */
public class QuickUnionImpl implements UnionFind {

  private int[] data;
  private int count;

  @Override
  public void init(int dataCount) {
    data = new int[dataCount];
    for (int i = 0; i < dataCount; i++) {
      data[i] = i;
    }
    count = dataCount;
  }

  @Override
  public int find(int p) {
    int t = data[p];
    while (t != p) {
      p = t;
      t = data[p];
    }
    return p;
  }

  @Override
  public void union(int p, int q) {
    int pId = find(p);
    int qId = find(q);
    if (pId != qId) {
      data[pId] = qId;
      count--;
    }
  }

  @Override
  public int count() {
    return count;
  }

  @Override
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
}
