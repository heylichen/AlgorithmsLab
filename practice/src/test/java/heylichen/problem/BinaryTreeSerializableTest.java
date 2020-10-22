package heylichen.problem;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lichen
 * @date 2020/10/22 10:06
 * @desc
 */
public class BinaryTreeSerializableTest {

    @Test
    public void testPreOrderRecursive() {
        //same as DFS
        testSerialization(new PreOrderRecursiveSerializer());
    }

    @Test
    public void testPreOrderNonRecursive() {
        testSerialization(new PreOrderSerializer());
    }

    @Test
    public void testPostOrderRecursive() {
        testSerialization(new PostOrderRecursiveSerializer());
    }

    @Test
    public void testPostOrderNonRecursive() {
        testSerialization(new PostOrderSerializer());
    }

//    @Test
//    public void testInOrderRecursive() {
//        testSerialization(new InOrderRecursiveSerializer());
//    }


    @Test
    public void TestBFSSerializer() {
        testSerialization(new BFSSerializer());
    }

    private void testSerialization(BinaryTreeSerializable serializable) {
        Node<Integer> tree = createTree();
        String string = serializable.serialize(tree);
        System.out.println(string);
        Node<Integer> clone = serializable.deserialize(string);

        Assert.assertTrue(SerializeUtils.binaryTreeEquals(tree, clone));
    }

    private Node<Integer> createTree() {
        Node<Integer> n10 = new Node<>(10);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n4 = new Node<>(4);
        n10.setLeft(n5);
        n5.setLeft(n4);

        Node<Integer> n20 = new Node<>(20);
        Node<Integer> n30 = new Node<>(30);
        Node<Integer> n15 = new Node<>(15);
        Node<Integer> n12 = new Node<>(12);
        Node<Integer> n18 = new Node<>(18);

        n20.setChildren(n15, n30);
        n15.setChildren(n12, n18);
        n10.setRight(n20);
        return n10;
    }
}