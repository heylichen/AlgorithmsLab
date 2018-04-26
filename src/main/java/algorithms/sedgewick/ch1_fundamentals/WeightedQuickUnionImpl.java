package algorithms.sedgewick.ch1_fundamentals;

/**
 * Created by Chen Li on 2018/4/26.
 */
public class WeightedQuickUnionImpl implements UnionFind {

  private int[] data;
  private int[] size;
  private int count;


  @Override
  public void init(int dataCount) {
    data = new int[dataCount];
    size = new int[dataCount];
    for (int i = 0; i < dataCount; i++) {
      data[i] = i;
      size[i] = 1;
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
    if (pId == qId) {
      return;
    }
    count--;
    int sizeP = size[pId];
    int sizeQ = size[qId];
    if (sizeP > sizeQ) {
      data[qId] = pId;
      size[pId] = sizeP + sizeQ;
    } else {
      data[pId] = qId;
      size[qId] = sizeP + sizeQ;
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
