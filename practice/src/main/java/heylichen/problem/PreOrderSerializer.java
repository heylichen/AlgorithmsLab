package heylichen.problem;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lichen
 * @date 2020/10/22 10:16
 * @desc pre-order non-recursive
 */
public class PreOrderSerializer implements BinaryTreeSerializable {
    private static Node<Integer> NOT_INITIALIZED = new Node<Integer>(-1000);

    @Override
    public String serialize(Node<Integer> node) {
        List<Integer> list = new LinkedList<>();
        Deque<Node<Integer>> stack = new LinkedList<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node<Integer> c = stack.pop();
            if (c == null) {
                list.add(null);
            } else {
                list.add(c.getData());
                stack.push(c.getRight());
                stack.push(c.getLeft());
            }
        }

        return SerializeUtils.toString(list);
    }

    @Override
    public Node<Integer> deserialize(String string) {
        List<Integer> list = SerializeUtils.parse(string);
        Deque<Node<Integer>> stack = new LinkedList<>();
        Node<Integer> result = null;
        for (Integer integer : list) {
            if (integer != null) {
                Node<Integer> c = new Node<>(integer, NOT_INITIALIZED, NOT_INITIALIZED);
                stack.push(c);
                continue;
            }
            Node<Integer> existed = stack.pop();
            boolean full = setRelation(existed, null);
            while (full) {
                if (stack.isEmpty()) {
                    result = existed;
                    break;
                }
                Node<Integer> p = stack.pop();
                if (p == null) {
                    throw new IllegalStateException("not valid");
                }
                full = setRelation(p, existed);
                existed = p;
            }
            stack.push(existed);
        }
        return result;
    }

    private static boolean setRelation(Node<Integer> existed, Node<Integer> c) {
        if (existed.getLeft() == NOT_INITIALIZED) {
            existed.setLeft(c);
            return false;
        } else if (existed.getRight() == NOT_INITIALIZED) {
            existed.setRight(c);
        }
        return true;
    }
}
