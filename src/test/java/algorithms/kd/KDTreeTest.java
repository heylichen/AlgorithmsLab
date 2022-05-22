package algorithms.kd;

import algorithms.kd.dist.Distance;
import algorithms.kd.dist.SquareEuclidDistance;
import com.heylichen.algorithm.util.visualize.SquareTreePrinter;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class KDTreeTest {
  private SquareTreePrinter<KDNode> printer =
      new SquareTreePrinter<>((KDNode n) -> n.toString(), (KDNode n) -> n.getLeft(), (KDNode n) -> n.getRight());
  static java.util.Random rand = new java.util.Random();

  static double[] makeSample(int dims) {
    double[] rv = new double[dims];
    for (int j = 0; j < dims; ++j) {
      rv[j] = rand.nextDouble();
    }
    return rv;
  }


  static List<double[]> sampleData() {
    List<double[]> l = new ArrayList<double[]>();

    l.add(new double[]{1.0, 2.0});
    l.add(new double[]{1.0, 2.5});
    l.add(new double[]{1.0, 3.0});
    l.add(new double[]{1.0, 3.5});
    l.add(new double[]{1.0, 4.0});

    l.add(new double[]{0.5, 2.0});
    l.add(new double[]{0.5, 2.5});
    l.add(new double[]{0.5, 3.0});
    l.add(new double[]{0.5, 3.5});
    l.add(new double[]{0.5, 4.0});

    return l;
  }

  @Test
  public void testNearestNeighborWithSampleData() {
    int dimensions = 2;
    KDTree<Long> tree = new KDTree<Long>(dimensions);
    List<double[]> sampleData = sampleData();
    long i = 0;
    for (double[] doubles : sampleData) {
      tree.insert(new Point(doubles), ++i);
    }

    printer.print(tree.getRoot());

    Entry<Long> nbrs;
//    nbrs = tree.getNearestNeighbor(new double[]{1.5, 0.5});
//    nbrs = tree.getNearestNeighbor(new double[]{1.5, 3});
//    nbrs = tree.getNearestNeighbor(new double[]{0.5, 1});
    nbrs = tree.getNearestNeighbor(new double[]{0.8, 3.8}, SquareEuclidDistance.INSTANCE);
    System.out.println();
  }

  @Test
  public void testKNNWithSampleData() {
    int dimensions = 2;
    KDTree<Long> tree = new KDTree<Long>(dimensions);
    List<double[]> sampleData = sampleData();
    long i = 0;
    for (double[] doubles : sampleData) {
      tree.insert(new Point(doubles), ++i);
    }

    printer.print(tree.getRoot());

    List<Entry<Long>> knnList;
//    knnList = tree.getNearestNeighbor(new double[]{1.5, 0.5}, SquareEuclidDistance.INSTANCE, 3);
    knnList = tree.getNearestNeighbor(new double[]{0.8, 3.8}, SquareEuclidDistance.INSTANCE, 3);
//    nbrs = tree.getNearestNeighbor(new double[]{1.5, 3});
//    nbrs = tree.getNearestNeighbor(new double[]{0.5, 1});
//    nbrs = tree.getNearestNeighbor(new double[]{0.8, 3.8}, SquareEuclidDistance.INSTANCE);
    System.out.println();
  }


  @Test
  public void testNearestNeighbor() {
    for (int i = 0; i < 10; i++) {
      doTestRandomNearestNeighbor(3, 300);
    }
  }

  private void doTestRandomNearestNeighbor(int dimensions, int points) {
    KDTree<Integer> kt = new KDTree<>(dimensions);
    double[] target = makeSample(dimensions);

    int min_index = 0;
    double min_value = Double.MAX_VALUE;
    for (int i = 0; i < points; ++i) {
      double[] keys = makeSample(dimensions);
      kt.insert(new Point(keys), new Integer(i));

      double dist = SquareEuclidDistance.INSTANCE.getDistance(target, keys);
      if (dist < min_value) {
        min_value = dist;
        min_index = i;
      }
    }

    Entry<Integer> result = kt.getNearestNeighbor(target, SquareEuclidDistance.INSTANCE);
    Assert.assertEquals(result.getValue().intValue(), min_index);
  }

  @Test
  public void testKNearestNeighbor() {
    for (int i = 0; i < 10; i++) {
      doTestRandomKNearestNeighbor(3, 300);
    }
  }

  private void doTestRandomKNearestNeighbor(int dimensions, int points) {
    Distance distance = SquareEuclidDistance.INSTANCE;

    KDTree<Integer> kt = new KDTree<>(dimensions);
    double[] target = makeSample(dimensions);

    int count = 10;
    PriorityQueue<Entry<Integer>> pq = new PriorityQueue<>(count, Comparator.comparingDouble(a -> distance.getDistance(a.getKey().getCoordinates(), target)));
    for (int i = 0; i < points; ++i) {
      double[] keys = makeSample(dimensions);
      Point key = new Point(keys);
      Integer value = i;
      Entry<Integer> entry = new Entry<>(key, value);
      kt.insert(key, value);
      pq.add(entry);
    }

    List<Integer> expected = new ArrayList<>(count);
    while (!pq.isEmpty() && expected.size() < count) {
      expected.add(pq.poll().getValue());
    }
    List<Entry<Integer>> result = kt.getNearestNeighbor(target, SquareEuclidDistance.INSTANCE, count);
    Assert.assertEquals(expected, result.stream().map(Entry::getValue).collect(Collectors.toList()));
  }


  @Test
  public void testBuild() {
    KDTree<Integer> t = buildKdTree();
    String view = printer.printAsString(t.getRoot());
    System.out.println(view);
  }

  @Test
  public void testGet() {
    KDTree<Integer> t = buildKdTree();
    String view = printer.printAsString(t.getRoot());
    Integer pData = t.get(new double[]{50, 50});
    Assert.assertEquals(50, pData.intValue());

    pData = t.get(new double[]{60, 80});
    Assert.assertEquals(60, pData.intValue());
    System.out.println(view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInsert() {
    Point p = new Point(new double[]{65, 65});
    KDTree<Integer> t = buildKdTree();
    Integer pData = t.get(p.getCoordinates());
    Assert.assertTrue(pData == null);

//    printer.print(t.getRoot());

    t.insert(p, -65);
    pData = t.get(p.getCoordinates());
    Assert.assertTrue(pData != null);
    Assert.assertTrue(pData.equals(-65));

    t.insert(p, -65);
//    printer.print(t.getRoot());
  }

  @Test
  public void testRange() {
    int dimensions = 2;
    KDTree<Long> tree = new KDTree<Long>(dimensions);
    List<double[]> sampleData = sampleData();
    long i = 0;
    for (double[] doubles : sampleData) {
      tree.insert(new Point(doubles), ++i);
    }
    printer.print(tree.getRoot());

    List<Entry<Long>> list = tree.getByRange(new HyperRectangle(new double[]{0.5, 3}, new double[]{1, 3.5}));
    System.out.println(list);

    list = tree.getByRange(new HyperRectangle(new double[]{0.6, 3.1}, new double[]{1.1, 3.6}));
    System.out.println(list);

    list = tree.getByRange(new HyperRectangle(new double[]{1.1, 0}, new double[]{2, 4}));
    System.out.println(list);
    //partial range
    list = tree.getByRange(new HyperRectangle(new double[]{1, Double.MIN_VALUE}, new double[]{1, Double.MAX_VALUE}));
    System.out.println(list);
    //partial range
    list = tree.getByRange(new HyperRectangle(new double[]{Double.MIN_VALUE, 3}, new double[]{Double.MAX_VALUE, 3}));
    System.out.println(list);
    //exact
    list = tree.getByRange(new HyperRectangle(new double[]{0.5, 3}, new double[]{0.5, 3}));
    System.out.println(list);
  }

  private KDTree<Integer> buildKdTree() {
    List<double[]> pointsArr = Arrays.asList(new double[]{1, 10},
        new double[]{51, 75}, new double[]{10, 30}, new double[]{25, 40}, new double[]{50, 50}
        , new double[]{70, 70}, new double[]{60, 80}, new double[]{35, 90}, new double[]{50, 60});

    List<Entry<Integer>> points = new ArrayList<>();
    for (double[] doubles : pointsArr) {
      Entry<Integer> p = new Entry<>(new Point(doubles), (int) doubles[0]);
      points.add(p);
    }

    KDTree<Integer> t = new KDTree<>(2);
    t.build(points);
    return t;
  }
}