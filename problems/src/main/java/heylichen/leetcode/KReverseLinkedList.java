package heylichen.leetcode;

/**
 * problem description :https://blog.csdn.net/Jas000/article/details/117399858
 * leetcode no: 25
 * 本题的目标非常清晰易懂，不涉及复杂的算法，但是实现过程中需要考虑的细节比较多，容易写出冗长的代码。主要考查面试者设计的能力。
 *
 * @param <T>
 */
public class KReverseLinkedList<T> {
    private Node<T> newHead;
    private Node<T> previousKTail;
    private Node<T> kHead;
    private Node<T> kTail;

    public Node<T> kReverse(Node<T> root, int k) {
        if (k == 0) {
            return root;
        }

        Node<T> current = root;
        int kSize = 0;
        while (current != null) {
            if (kHead == null) {
                kHead = kTail = current;
                kSize++;
                Node<T> next = current.getNext();
                current.setNext(null);
                current = next;
            } else if (kSize < k) {
                Node<T> next = current.getNext();
                current.setNext(kHead);
                kHead = current;
                current = next;
                kSize++;
            } else if (kSize == k) {
                if (newHead == null) {
                    newHead = kHead;
                }
                if (previousKTail != null) {
                    previousKTail.setNext(kHead);
                }
                previousKTail = kTail;
                kHead = null;
                kTail = null;
                kSize = 0;
            } else {
                System.err.println("error");
            }
        }

        kTail.setNext(null);
        if (kSize < k) {
            //count of last list < k, reverse again, to original order
            current = kHead;
            kHead = null;
            while (current != null) {
                Node<T> next = current.getNext();
                if (kHead == null) {
                    kHead = current;
                    current.setNext(null);
                    current = next;
                    continue;
                }
                current.setNext(kHead);
                kHead = current;
                current = next;
            }
        }
        if (previousKTail == null) {
            newHead = kHead;
        } else {
            previousKTail.setNext(kHead);
        }
        return newHead;
    }
}
