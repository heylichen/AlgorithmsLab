package heylichen.dynamicprogram;

import heylichen.problem.Node;
import heylichen.utils.SquareTreePrinter;
import junit.framework.TestCase;

/**
 * @author lichen
 * @date 2020/11/2 10:40
 * @desc
 */
public class OptimalBinarySearchTreeTest extends TestCase {
    public void testName() {
        SquareTreePrinter<Node> treePrinter = new SquareTreePrinter<>(n -> n.getData().toString(), n -> n.getLeft(), n -> n.getRight());

        float[] p = new float[]{-1, 0.15f, 0.10f, 0.05f, 0.10f, 0.20f};
        float[] q = new float[]{0.05f, 0.10f, 0.05f, 0.05f, 0.05f, 0.10f};
        OptimalBinarySearchTree t = new OptimalBinarySearchTree(p, q, p.length - 1);
        t.build();
        Node<Integer> rootNode = t.getRootNode();
        String treeView = treePrinter.print(rootNode);
        System.out.println(treeView);
    }
}