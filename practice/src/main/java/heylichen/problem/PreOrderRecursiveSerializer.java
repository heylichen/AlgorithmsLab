package heylichen.problem;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lichen
 * @date 2020/10/22 9:40
 * @desc
 */
public class PreOrderRecursiveSerializer implements BinaryTreeSerializable {

    @Override
    public String serialize(Node<Integer> node) {
        List<Integer> list = new LinkedList<>();
        serialize(node, list);
        return SerializeUtils.toString(list);
    }

    private void serialize(Node<Integer> node, List<Integer> list) {
        if (node == null) {
            list.add(null);
            return;
        }
        list.add(node.getData());
        serialize(node.getLeft(), list);
        serialize(node.getRight(), list);
    }

    @Override
    public Node<Integer> deserialize(String string) {
        List<Integer> list = SerializeUtils.parse(string);
        return deserialize(list);
    }

    public static Node<Integer> deserialize(List<Integer> list) {
        Integer d = list.remove(0);
        if (d == null) {
            return null;
        }
        Node<Integer> node = new Node<Integer>(d);
        node.setLeft(deserialize(list));
        node.setRight(deserialize(list));
        return node;
    }
}
