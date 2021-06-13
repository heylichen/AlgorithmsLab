package heylichen.leetcode;

public class LinkedListUtil {

    public static final <T> void print(Node<T> root) {
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            sb.append(root.getData().toString()).append(" -> ");
            root = root.getNext();
        }

        System.out.println(sb.substring(0));
    }

    public static final Node<Integer> buildLinkedList(int size) {
        Node<Integer> head = null;
        Node<Integer> tail = null;
        for (int i = 0; i < size; i++) {
            Node<Integer> node = new Node<>(i);
            if (head == null) {
                head = tail = node;
            } else {
                tail.setNext(node);
                tail = node;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("ok");
    }
}
