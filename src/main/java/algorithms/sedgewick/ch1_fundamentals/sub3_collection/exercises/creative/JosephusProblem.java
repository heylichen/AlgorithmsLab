package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.creative;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.Node;

/**
 * Created by lc on 2016/4/12.
 */
public class JosephusProblem {
    private static class CycleLinkedList<T> {
        private Node<T> head = null;
        private Node<T> tail = null;

        public void add(T item) {
            Node<T> node = new Node<>();
            node.setItem(item);
            if (head == null) {
                head = node;
                tail = node;
                tail.setNext(head);
            } else {
                tail.setNext(node);
                tail = node;
                tail.setNext(head);
            }
        }

        public void deleteNextOf(Node<T> node) {
            if (node != null) {
                Node<T> current = node;
                current.setNext(current.getNext().getNext());
            }
        }

        public boolean isLastNode(Node<T> node) {
            if (node == null || node.getNext() == node) {
                return true;
            } else {
                return false;
            }
        }

        public Node<T> getHead() {
            return head;
        }

        public boolean isEmpty() {
            return head == null;
        }

    }

    private static final class NodeFootPrint<T> {
        private final Node<T> previous;
        private final Node<T> current;

        public NodeFootPrint(Node<T> previous, Node<T> current) {
            this.previous = previous;
            this.current = current;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public Node<T> getCurrent() {
            return current;
        }
    }

    public void resolveProblem(int N, int M) {
        CycleLinkedList<Integer> cycle = new CycleLinkedList<>();
        for (int i = 0; i < N; i++) {
            cycle.add(i);
        }

        Node<Integer> current = cycle.getHead();
        int steps = M - 1;
        while (!cycle.isLastNode(current)) {
            NodeFootPrint<Integer> nodes = stepThrough(current, steps);
          System.out.print(nodes.getCurrent().getItem() + " ");
            current = nodes.getCurrent().getNext();
            cycle.deleteNextOf(nodes.getPrevious());
        }
       System.out.print(current.getItem() + " ");

    }

    public  static void main(String[] a){
        JosephusProblem p = new JosephusProblem();
        long start = System.currentTimeMillis();
        p.resolveProblem(20,3);
        long end = System.currentTimeMillis();
        System.out.print( "\n use "+(end - start) + " ms ");
    }

    private <T> NodeFootPrint<T> stepThrough(Node<T> node, int steps) {
        if (steps <= 0) {
            throw new IllegalArgumentException("steps must be larger than 0!");
        }
        Node<T> current = node;
        int tryStep = steps - 1;
        for (int i = 0; i < tryStep; i++) {
            current = current.getNext();
        }
        return new NodeFootPrint(current, current.getNext());
    }

}
