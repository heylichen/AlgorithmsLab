package heylichen.leetcode;

import java.util.HashMap;
import java.util.Map;
// 146
public class LRUCache {
    private Node head;
    private Node tail;
    private final int capacity;
    private final Map<Integer, Node> map;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node n = head;
        while (n != null) {
            sb.append(n.key).append("->");
            n = n.next;
        }
        sb.append("\t");

        for (Map.Entry<Integer, Node> integerNodeEntry : map.entrySet()) {
            Integer key = integerNodeEntry.getKey();
            sb.append(key).append(",");
        }
        sb.append(".");
        return sb.toString();
    }

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node existed = map.get(key);
        if (existed != null) {
            existed.value = value;
            moveToHead(existed);
            return;
        }
        Node node = new Node(key, value);
        if (map.size() >= capacity) {
            dropEldest();
        }
        map.put(key, node);
        prepend(node);
    }

    private void dropEldest() {
        if (tail == null) {
            return;
        }
        map.remove(tail.key);
        Node pre = tail.previous;
        if (pre == null) {
            head = tail = null;
            return;
        }
        tail.previous = null;
        pre.next = null;
        tail = pre;

    }

    private void prepend(Node node) {
        if (head == null && tail == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head.previous = node;
        head = node;
    }

    private void moveToHead(Node node) {
        if (head == node) {
            return;
        }
        if (node == tail) {
            tail = node.previous;
        }

        Node l = node.previous;
        Node r = node.next;
        l.next = r;
        if (r != null) {
            r.previous = l;
        }

        node.next = head;
        head.previous = node;

        head = node;
        head.previous = null;
    }

    private static class Node {
        private final int key;
        private int value;
        private Node previous;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}