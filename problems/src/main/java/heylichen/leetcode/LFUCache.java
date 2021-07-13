package heylichen.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lichen
 * @date 2021/7/13 10:50
 * @desc leetcode 460
 * this is a O(1) LFU
 */
public class LFUCache {

    private int capacity;
    private Map<Integer, ItemNode<Integer>> map;
    private FreqNode freqHead;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        ItemNode<Integer> node = map.get(key);
        if (node == null) {
            return -1;
        }
        promoteItemNode(node);
        return node.value;
    }

    //move node to higher frequency node
    private void promoteItemNode(ItemNode<Integer> node) {
        FreqNode currentFreq = node.parent;
        FreqNode nextFreq = currentFreq.next;
        if (nextFreq == null || nextFreq.count != currentFreq.count + 1) {
            nextFreq = currentFreq.createNextFreqNode();
        }
        //move itemNode from current FreqNode to next
        removeItem(currentFreq, node);
        nextFreq.insert(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        ItemNode<Integer> node = map.get(key);
        if (node != null) {
            node.value = value;
            //since accessed
            promoteItemNode(node);
            return;
        }

        shrink();

        FreqNode freqNode = getOrCreateFreq1Node();

        ItemNode item = new ItemNode(key, value);
        map.put(key, item);
        freqNode.insert(item);
    }

    private FreqNode getOrCreateFreq1Node() {
        FreqNode freqNode;
        if (freqHead == null || freqHead.count != 1) {
            freqNode = new FreqNode(1);
            freqNode.next = freqHead;
            if (freqHead != null) {
                freqHead.prev = freqNode;
            }
            freqHead = freqNode;
        } else {
            freqNode = freqHead;
        }
        return freqNode;
    }

    private void shrink() {
        if (map.size() < capacity) {
            return;
        }

        map.remove(freqHead.tail.key);
        removeItem(freqHead, freqHead.tail);
    }

    private void removeItem(FreqNode freq, ItemNode item) {
        freq.remove(item);
        if (freq.isEmpty()) {
            removeFreqNode(freq);
        }
    }

    private void removeFreqNode(FreqNode freq) {
        if (freqHead == null) {
            return;
        }
        if (freq == freqHead) {
            freqHead = freq.next;
            if (freqHead != null) {
                freqHead.prev = null;
            }
            freq.next = null;
        } else {
            FreqNode prev = freq.prev;
            FreqNode next = freq.next;
            prev.next = next;
            next.prev = prev;
            freq.next = null;
            freq.prev = null;
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        FreqNode freqNode = freqHead;
        while (freqNode != null) {
            toString(sb,freqNode);
            freqNode = freqNode.next;
        }
        sb.append("\tmapKeys=");

        for (Map.Entry<Integer, ItemNode<Integer>> integerNodeEntry : map.entrySet()) {
            Integer key = integerNodeEntry.getKey();
            sb.append(key).append(",");
        }
        return sb.toString();
    }

    private void toString(StringBuilder sb, FreqNode node) {
        sb.append("[").append(node.count).append("]");
        ItemNode itemNode = node.head;
        while (itemNode != null) {
            sb.append(itemNode.key).append("->");
            itemNode = itemNode.next;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" ");
    }

    private static class FreqNode {
        private int count;
        private FreqNode prev;
        private FreqNode next;
        //head and tail to support LRU behaviour
        private ItemNode head;
        private ItemNode tail;

        public FreqNode(int count) {
            this.count = count;
        }

        public void remove(ItemNode item) {
            if (head == null) {
                return;
            }
            if (head == item && tail == item) {
                head = tail = null;
                return;
            }
            if (item == head) {
                head = item.next;
                head.prev = null;
                item.next = null;
            } else if (item == tail) {
                tail = item.prev;
                tail.next = null;
                item.prev = null;
            } else {
                ItemNode prev = item.prev;
                ItemNode next = item.next;
                prev.next = next;
                next.prev = prev;
                item.next = null;
                item.prev = null;
            }
        }

        public void insert(ItemNode item) {
            if (head == null) {
                head = tail = item;
            } else {
                item.next = head;
                head.prev = item;
                head = item;
                head.prev = null;
            }
            item.parent = this;
        }

        public FreqNode createNextFreqNode() {
            FreqNode newNextNode = new FreqNode(count + 1);
            newNextNode.next = this.next;
            if (this.next != null) {
                this.next.prev = newNextNode;
            }
            this.next = newNextNode;
            newNextNode.prev = this;
            return newNextNode;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    private static class ItemNode<T> {
        private Integer key;
        private T value;
        private FreqNode parent;
        private ItemNode prev;
        private ItemNode next;

        public ItemNode(Integer key, T value) {
            this.key = key;
            this.value = value;
        }
    }
}