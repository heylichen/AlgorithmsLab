package heylichen.interview;

import heylichen.problem.Node;
import lombok.Getter;
import lombok.Setter;

public class PreOrderTreeSerializer {
    public static final String NULL = "&";
    public static final String SEPARATOR = ",";
    public static final String EMPTY = "";

    public String serialize(Node<Integer> node) {
        if (node == null) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        serialize(node, sb);
        return sb.substring(1);
    }

    public void serialize(Node<Integer> node, StringBuilder sb) {
        if (node == null) {
            sb.append(SEPARATOR).append(NULL);
            return;
        }
        sb.append(SEPARATOR).append(node.getData().toString());
        serialize(node.getLeft(), sb);
        serialize(node.getRight(), sb);
    }

    public Node<Integer> deserialize(String string) {
        if (string.equals(EMPTY)) {
            return null;
        }
        String[] pieces = string.split(SEPARATOR);

        WritableIndex index = new WritableIndex();
        index.setIndex(0);

        return parse(pieces, index);
    }

    private Node<Integer> parse(String[] pieces, WritableIndex index) {
        String data = pieces[index.next()];
        if (data.equals(NULL)) {
            return null;
        }
        Node<Integer> node = new Node<>(Integer.valueOf(data));
        Node<Integer> left = parse(pieces, index);
        Node<Integer> right = parse(pieces, index);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }

    @Getter
    @Setter
    private static class WritableIndex {
        private int index;

        public int next() {
            return index++;
        }
    }
}
