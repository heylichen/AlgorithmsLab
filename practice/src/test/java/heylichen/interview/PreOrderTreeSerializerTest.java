package heylichen.interview;

import heylichen.problem.Node;
import heylichen.utils.SquareTreePrinter;
import heylichen.utils.TreeComparator;
import org.junit.Test;

public class PreOrderTreeSerializerTest {
    @Test
    public void name() {
        PreOrderTreeSerializer serializer = new PreOrderTreeSerializer();
        Node<Integer> node1 = createTree();
        String string = serializer.serialize(node1);


        SquareTreePrinter<Node> treePrinter = new SquareTreePrinter<>(n -> n.getData().toString(), n -> n.getLeft(), n -> n.getRight());
        System.out.println(treePrinter.print(node1));
        System.out.println(string);

        Node<Integer> node2 = serializer.deserialize(string);
        System.out.println(treePrinter.print(node2));

        System.out.println(TreeComparator.isEqualTree(node1,node2));
    }

    private Node<Integer> createTree() {
        Node<Integer> n10 = new Node<>(10);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n4 = new Node<>(4);
        n10.setLeft(n5);
        n5.setLeft(n4);

        Node<Integer> n20 = new Node<>(20);
        Node<Integer> n30 = new Node<>(30);
        Node<Integer> n31 = new Node<>(31);
        Node<Integer> n15 = new Node<>(15);
        Node<Integer> n12 = new Node<>(12);
        Node<Integer> n18 = new Node<>(18);

        n20.setChildren(n15, n30);
        n15.setChildren(n12, n18);
        n10.setRight(n20);
        n30.setRight(n31);
        return n10;
    }
}