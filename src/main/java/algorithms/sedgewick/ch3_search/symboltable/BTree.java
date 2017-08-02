package algorithms.sedgewick.ch3_search.symboltable;

/**
 * Created by Chen Li on 2017/8/1.
 */
public class BTree<K, V> {
  private BTreeNode<K, V> root;

  public void init() {
    BTreeNode<K, V> node = createNode();
    node.setLeaf(true);
    root = node;
  }

  private BTreeNode<K, V> createNode(){
    return new BTreeNode<>();
  }


  public void insert(BTreeNode<K, V> node, K key) {

  }

  public void splitChild(BTreeNode<K, V> node, int i) {

  }

}
