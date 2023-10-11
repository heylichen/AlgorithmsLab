package algorithms.search.symboltable;

import java.util.Random;

/**
 * Created by Chen Li on 2017/7/25.
 */
public class ReadBlackBSTTest {
  public static void main(String[] args) {
    Random rand = new Random();

    RedBlackBST<Integer, Integer> bst = new RedBlackBST<>();

    BST<Integer, Integer> oldBst = new BST<>();

    for (int i = 0; i < 256; i++) {
      Integer v = rand.nextInt(1000) + 1;
      bst.put(v, v);
      oldBst.put(v, v);
    }
    System.out.println(oldBst);
    System.out.println(bst);

  }
}