package algorithms.visualization.service;

import algorithms.sedgewick.ch3_search.symboltable.BTreeNode;
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
    ClassPathResource cpr = new ClassPathResource("1.json");
    String json = FileUtils.readFileToString(cpr.getFile(), StandardCharsets.UTF_8);
    BTreeNode node = JSON.parseObject(json, BTreeNode.class);

    System.out.println("ok");
  }
}
