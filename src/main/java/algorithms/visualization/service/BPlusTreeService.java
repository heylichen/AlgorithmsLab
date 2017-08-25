package algorithms.visualization.service;

import algorithms.sedgewick.ch3_search.symboltable.BPlusTree;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chen Li on 2017/8/13.
 */
@Service
public class BPlusTreeService {
  private Logger logger = LoggerFactory.getLogger(BPlusTreeService.class);
  public BPlusTree<Integer, Integer> init(int t, int nodeCount) {
    BPlusTree<Integer, Integer> BPlusTree = new BPlusTree<>(t);

    for (int i = 1; i <= nodeCount; i++) {
      BPlusTree.insert(i, i);
    }
    return BPlusTree;
  }

  public BPlusTree<Integer, Integer> random(int t, int nodeCount) {
    BPlusTree<Integer, Integer> BPlusTree = new BPlusTree<>(t);
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());
    List<Integer> keys = new ArrayList<>();

    for (int i = 1; i <= nodeCount; i++) {
      Integer key = random.nextInt(2000);
      keys.add(key);
      BPlusTree.insert(key, i);
    }
    logger.info("random keys :{}", JSON.toJSONString(keys));
    return BPlusTree;
  }

  public BPlusTree<Integer, Integer> addKey(BPlusTree<Integer, Integer> BPlusTree, Integer key) {
    BPlusTree.insert(key, key);
    return BPlusTree;
  }

  public BPlusTree<Integer, Integer> deleteKey(BPlusTree<Integer, Integer> BPlusTree, Integer key) {
//    BPlusTree.delete(key);
    return BPlusTree;
  }

}
