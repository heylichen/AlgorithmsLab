package algorithms.search.symboltable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

/**
 * Created by Chen Li on 2017/8/19.
 */
public class BPlusTreeTest {
  @Test
  public void testInsert() throws Exception {

    BPlusTree<Integer, Integer> bPlusTree = new BPlusTree<>(2);

    for (int i = 1; i <= 10; i++) {
      bPlusTree.insert(i, i);
    }

    System.out.println(JSON.toJSONString(bPlusTree.getRoot(), SerializerFeature.DisableCircularReferenceDetect));
  }
}