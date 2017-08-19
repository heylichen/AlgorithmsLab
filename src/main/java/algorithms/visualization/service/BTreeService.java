package algorithms.visualization.service;

import algorithms.sedgewick.ch3_search.symboltable.BTree;
import org.springframework.stereotype.Service;

/**
 * Created by Chen Li on 2017/8/13.
 */
@Service
public class BTreeService {
  public BTree<Integer, Integer> initBtree(int t, int nodeCount) {
    BTree<Integer, Integer> btree = new BTree<>(t);
    btree.init();

    for (int i = 0; i < nodeCount; i++) {
      btree.insert(i);
    }
    return btree;
  }

  public BTree<Integer, Integer> addKey(BTree<Integer, Integer> btree, Integer key) {
    btree.insert(key);
    return btree;
  }

  public BTree<Integer, Integer> deleteKey(BTree<Integer, Integer> btree, Integer key) {
    btree.delete(key);
    return btree;
  }

}
