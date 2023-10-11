import java.util.LinkedList;
import java.util.Queue;

public class LevelPrintBinaryTree {
    private static final FakeNode SEPARATOR = new FakeNode(null);
    private static final char SPACE = ' ';
    private static final char NEW_LINE = '\n';
    private static boolean isAVL = true;

    public static void main(String[] args) {
        Node n1 = new Node(8);
        Node n2 = new Node(24);
        Node n3 = new Node(16, n1, n2);


        Node n11 = new Node(36);
        Node n12 = new Node(52);
        Node n13 = new Node(48, n11, n12);

        Node root = new Node(32, n3, n13);
        print(root);

        height(root);
        System.out.println(isAVL);

        n1.left = new Node(7);
        height(root);
        System.out.println(isAVL);

        n1.left.left = new Node(6);
        height(root);
        System.out.println(isAVL);
    }

    private static int height(Node node) {
        if (!isAVL || node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int heightDiff = leftHeight - rightHeight;
        if (heightDiff < 0) {
            heightDiff = -heightDiff;
        }
        if (heightDiff > 1) {
            LevelPrintBinaryTree.isAVL = false;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static void print(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        queue.offer(SEPARATOR);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == null) {
                sb.append("NULL").append(SPACE);
                continue;
            }
            if (current != SEPARATOR) {
                sb.append(current.data).append(SPACE);
                queue.offer(current.left);
                queue.offer(current.right);
                continue;
            }

            //current == SEPARATOR
            if (queue.isEmpty()) {
                break;
            }
            sb.append(NEW_LINE);
            queue.offer(SEPARATOR);
        }
        System.out.println(sb.toString());
    }

    private static class Node {
        private Integer data;
        private Node left;
        private Node right;

        public Node(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node(Integer data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private static class FakeNode extends Node {
        public FakeNode(Integer data) {
            super(data);
        }

        public FakeNode(Integer data, Node left, Node right) {
            super(data, left, right);
        }
    }
}
