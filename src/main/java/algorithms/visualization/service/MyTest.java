package algorithms.visualization.service;

import algorithms.sedgewick.ch3_search.symboltable.BPlusTreeNode;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Chen Li on 2017/8/14.
 */
public class MyTest {
  public static void main(String[] args) throws IOException {
    ClassPathResource cpr = new ClassPathResource("2.json");
    String json = FileUtils.readFileToString(cpr.getFile(), StandardCharsets.UTF_8);
    BPlusTreeNode node = JSON.parseObject(json, BPlusTreeNode.class);

    System.out.println("ok");
  }
}
