package heylichen.problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSerializer implements BinaryTreeSerializable {

    @Override
    public String serialize(Node<Integer> node) {
        Queue<Node<Integer>> q = new LinkedList<>();
        q.offer(node);
        List<Integer> list = new LinkedList<>();
        while (!q.isEmpty()) {
            Node<Integer> c = q.poll();
            if (c == null) {
                list.add(null);
            } else {
                list.add(c.getData());
                q.offer(c.getLeft());
                q.offer(c.getRight());
            }
        }

        return SerializeUtils.toString(list);
    }

    @Override
    public Node<Integer> deserialize(String string) {
        List<Integer> list = SerializeUtils.parse(string);
        Queue<Node<Integer>> innerNodes = new LinkedList<>();

        Integer rootV = list.remove(0);
        Node<Integer> root = new Node<>(rootV);
        innerNodes.offer(root);

        while (!innerNodes.isEmpty()) {
            int levelSize = innerNodes.size();
            for (int i = 0; i < levelSize; i++) {
                Node<Integer> n = innerNodes.poll();

                Integer l = list.remove(0);

                if (l == null) {
                    n.setLeft(null);
                } else{
                    Node<Integer> lNode = new Node<>(l);
                    n.setLeft(lNode);
                    innerNodes.offer(lNode);
                }

                Integer r = list.remove(0);
                if (r == null) {
                    n.setRight(null);
                } else{
                    Node<Integer> rNode = new Node<>(r);
                    n.setRight(rNode);
                    innerNodes.offer(rNode);
                }
            }
        }

        return root;
    }

}
