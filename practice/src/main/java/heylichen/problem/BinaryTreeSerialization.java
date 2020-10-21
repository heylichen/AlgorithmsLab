package heylichen.problem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeSerialization {

    public static void main(String[] args) {
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

        List<Integer> s1 = new LinkedList<>();
        serializePreRecursive(n10, s1);
        System.out.println(s1);

        Node<Integer> nodeCopy = deserializePreRecursive(s1);
        System.out.println();

        s1.clear();
        serializePre(n10, s1);
        System.out.println(s1);


        Node<Integer> root2 = deserializePre(s1);
        System.out.println();
    }

    public static final void serializePre(Node<Integer> node, List<Integer> list) {
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
    }

    private static Node<Integer> NOT_INITIALIZED = new Node<Integer>(-1000);

    public static Node<Integer> deserializePre(List<Integer> list) {
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
            existed.left = c;
            return false;
        } else if (existed.getRight() == NOT_INITIALIZED) {
            existed.right = c;
        }
        return true;
    }

    public static final void serializePreRecursive(Node<Integer> node, List<Integer> list) {
        if (node == null) {
            list.add(null);
            return;
        }
        list.add(node.data);
        serializePreRecursive(node.left, list);
        serializePreRecursive(node.right, list);
    }

    public static Node<Integer> deserializePreRecursive(List<Integer> list) {
        Integer d = list.remove(0);
        if (d == null) {
            return null;
        }
        Node<Integer> node = new Node<Integer>(d);
        node.left = deserializePreRecursive(list);
        node.right = deserializePreRecursive(list);
        return node;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        public void setChildren(Node<T> l, Node<T> r) {
            left = l;
            right = r;
        }
    }
}
