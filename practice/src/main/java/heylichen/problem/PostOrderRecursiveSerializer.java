package heylichen.problem;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lichen
 * @date 2020/10/22 9:40
 * @desc
 */
public class PostOrderRecursiveSerializer implements BinaryTreeSerializable {

    @Override
    public String serialize(Node<Integer> node) {
        Deque<Integer> stack = new LinkedList<>();
        serialize(node, stack);
        return SerializeUtils.toString(stack);
    }

    private void serialize(Node<Integer> node,  Deque<Integer> stack) {
        if (node == null) {
            stack.push(null);
            return;
        }
        stack.push(node.getData());
        serialize(node.getLeft(), stack);
        serialize(node.getRight(), stack);
    }

    @Override
    public Node<Integer> deserialize(String string) {
        List<Integer> list = SerializeUtils.parse(string);
        return deserialize(list);
    }

    public static Node<Integer> deserialize(List<Integer> list) {
        Integer d = list.remove(list.size()-1);
        if (d == null) {
            return null;
        }
        Node<Integer> node = new Node<>(d);
        node.setLeft(deserialize(list));
        node.setRight(deserialize(list));
        return node;
    }
}
