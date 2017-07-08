package algorithms.sedgewick.ch3_search.symboltable;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chen Li on 2017/6/17.
 */
public abstract class SearchSTTest {
  @Test
  public void testPut() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("1", "3");
    assertEquals("size wrong!", st.size(), 2);
  }

  @Test
  public void testGet() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("1", "3");
    assertEquals("get wrong", st.get("1"), "3");
  }

  @Test
  public void testGetNonExist() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("1", "3");
    assertEquals("get wrong", st.get("4"), null);
  }

  @Test
  public void testContains() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("1", "3");
    assertEquals("get wrong", st.contains("1"), true);
  }

  @Test
  public void testDeleteFirst() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("3", "3");
    st.delete("1");

    assertEquals("delete wrong", 2, st.size());
    assertEquals("delete wrong", "2", st.get("2"));
    assertEquals("delete wrong", "3", st.get("3"));
  }

  @Test
  public void testDeleteSecond() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("3", "3");
    st.delete("2");

    assertEquals("delete wrong", st.size(), 2);
    assertEquals("delete wrong", st.get("1"), "1");
    assertEquals("delete wrong", st.get("3"), "3");
  }

  @Test
  public void testDeleteNonExist() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("3", "3");
    st.delete("4");

    assertEquals("delete wrong", st.size(), 3);
  }

  @Test
  public void testSizeEmpty() throws Exception {
    ST<String,String> st = createST();
    assertEquals("size wrong", st.size(), 0);
  }

  @Test
  public void testSizeNonEmpty() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    st.put("2", "2");
    st.put("1", "3");
    assertEquals("size wrong", st.size(), 2);
  }

  @Test
  public void testIsEmptyFirst() throws Exception {
    ST<String,String> st = createST();
    assertEquals("IsEmpty wrong", st.isEmpty(), true);
  }

  @Test
  public void testIsEmptyFalse() throws Exception {
    ST<String,String> st = createST();
    st.put("1", "1");
    assertEquals("IsEmpty wrong", st.isEmpty(), false);
  }

  @Test
  public void testEmptyKeys() throws Exception {
    ST<String,String> st = createST();
    Iterable keys = st.keys();
    Iterator keysIterator = keys.iterator();
    assertEquals("keys wrong", keysIterator.hasNext(), false);
  }

  @Test
  public void testKeys() throws Exception {
    ST<String,String> st = createST();
    List<String> originalKeys = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      String key = String.valueOf(i);
      st.put(key, key);
      originalKeys.add(key);
    }

    List<String> gotKeys = new ArrayList<>();
    for (String key : st.keys()) {
      gotKeys.add(key);
    }
    boolean equalKeys = CollectionUtils.isEqualCollection(originalKeys, gotKeys);
    assertEquals("keys wrong", equalKeys, true);
  }

  public abstract ST<String, String> createST();
}
