package algorithms.sedgewick.ch1_fundamentals;

/**
 * Created by Chen Li on 2018/4/26.
 */
public class QuickFindImpl implements UnionFind {

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
  public void union(int p, int q) {
    int pId = find(p);
    int qId = find(q);
    if (pId == qId) {
      return;
    }
    for (int i = 0; i < data.length; i++) {
      if (data[i] == qId) {
        data[i] = pId;
      }
    }
    count--;
  }

  @Override
  public int find(int p) {
    return data[p];
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
