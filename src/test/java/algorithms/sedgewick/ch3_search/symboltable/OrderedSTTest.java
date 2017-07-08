package algorithms.sedgewick.ch3_search.symboltable;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chen Li on 2017/6/22.
 */
public abstract class OrderedSTTest extends SearchSTTest {


  @Test
  public void testEmptyKeys() throws Exception {
    OrderedST<String, String> st = createOrderedST();
    assertEquals("empty st, keys hasNext should be false", false, st.keys().iterator().hasNext());
  }


  @Test
  public void testLargeLowKeys() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    assertEquals("empty st, keys hasNext should be false", false, st.keys("7", "7").iterator().hasNext());
  }

  @Test
  public void testRight() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String left = "4";
    String right = "6";

    Iterator<String> it = st.keys(left, right).iterator();
    assertEquals("should has next", true, it.hasNext());
    assertEquals("should has next", left, it.next());
    assertEquals("should has next", true, it.hasNext());
    assertEquals("should has next", right, it.next());
    assertEquals("should has next", false, it.hasNext());
  }

  @Test
  public void testRightLess() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String left = "4";
    String right = "5";

    Iterator<String> it = st.keys(left, right).iterator();
    assertEquals("should has next", true, it.hasNext());
    assertEquals("should has next", left, it.next());
    assertEquals("should has next", false, it.hasNext());
  }

  @Test
  public void testRightLessStart() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String left = "0";
    String right = "0";

    Iterator<String> it = st.keys(left, right).iterator();
    assertEquals("should has next", true, it.hasNext());
    assertEquals("should has next", left, it.next());
    assertEquals("should has next", false, it.hasNext());
  }

  @Test
  public void testRightLessStartLess() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String left = "-1";
    String right = "-1";

    Iterator<String> it = st.keys(left, right).iterator();
    assertEquals("should has next", false, it.hasNext());
  }

  @Test
  public void testRankRight() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    int rank = st.rank("7");
    assertEquals("", 4, rank);
  }

  @Test
  public void testRankRight1() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    int rank = st.rank("6");
    assertEquals("", 3, rank);
  }

  @Test
  public void testRankLeft() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    int rank = st.rank("-1");
    assertEquals("", 0, rank);
  }

  @Test
  public void testRankLeft1() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    int rank = st.rank("0");
    assertEquals("", 0, rank);
  }

  @Test
  public void testRankMiddle() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    int rank = st.rank("3");
    assertEquals("", 2, rank);
  }

  @Test
  public void testFloor() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String floorKey = st.floor("6");
    assertEquals("", "6", floorKey);
  }

  @Test
  public void testFloor1() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String floorKey = st.floor("5");
    assertEquals("", "4", floorKey);
  }

  @Test
  public void testFloor2() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String floorKey = st.floor("0");
    assertEquals("", "0", floorKey);
  }

  @Test
  public void testFloor3() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String floorKey = st.floor("-1");
    assertEquals("", null, floorKey);
  }
  //
  @Test
  public void testFloor4() throws Exception {
    OrderedST<String, String> st = createOrderedST();
    String floorKey = st.floor("0");
    assertEquals("", null, floorKey);
  }

  @Test
  public void testFloor5() throws Exception {
    OrderedST<String, String> st = createOrderedSTWithValues();
    String floorKey = st.floor("-1");
    assertEquals("", null, floorKey);
  }


  protected OrderedST<String, String> createOrderedSTWithValues() {
    OrderedST<String, String> st = createOrderedST();
    st.put("0", "0");
    st.put("2", "2");
    st.put("4", "4");
    st.put("6", "6");
    return st;
  }

  @Override
  public ST<String, String> createST() {
    return createOrderedST();
  }

  public abstract OrderedST<String, String> createOrderedST();

  public static void main(String[] args) {
    System.out.println("1".compareTo("0"));

  }
}
