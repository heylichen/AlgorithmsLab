package heylichen.leetcode;

import org.junit.Test;

public class KReverseLinkedListTest {

    @Test
    public void kReverse() {
        KReverseLinkedList kReverseLinkedList = new KReverseLinkedList();
        Node<Integer> node10 = LinkedListUtil.buildLinkedList(10);
        LinkedListUtil.print(node10);

        Node<Integer> rev = kReverseLinkedList.kReverse(node10, 6);
        LinkedListUtil.print(rev);
    }
}