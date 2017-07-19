package algorithms.sedgewick.ch3_search.symboltable;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

/**
 * Created by Chen Li on 2017/6/21.
 */
public class BSTTest extends OrderedSTTest {
  @Override
  public OrderedST<String, String> createOrderedST() {
    return new BST<>();
  }

  @Test
  public void deleteTest() throws Exception {
    String path = "algorithms/bst.json";
    ClassPathResource cpr = new ClassPathResource(path);
    String content = FileUtils.readFileToString(cpr.getFile(), StandardCharsets.UTF_8);
    Node node = JSON.parseObject(content, Node.class);

    BST<Integer, Integer> bst = new BST<>();
    bst.setRoot(node);

    bst.delete(678);

    System.out.println(bst);
  }


  @Test
  public void keysTest() throws Exception {
    String path = "algorithms/bst.json";
    ClassPathResource cpr = new ClassPathResource(path);
    String content = FileUtils.readFileToString(cpr.getFile(), StandardCharsets.UTF_8);
    Node node = JSON.parseObject(content, Node.class);

    BST<Integer, Integer> bst = new BST<>();
    bst.setRoot(node);

    for (Integer integer : bst.keys(null, null)) {
      System.out.print(integer + ", ");
    }
  }
}