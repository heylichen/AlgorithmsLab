package heylichen.sort.pq;

import heylichen.test.AppTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class PriorityQueueTest extends AppTestContext {
    @Autowired
    private PriorityComparator<Integer> minIntPriorityComparator;

    @Test
    public void name2() {
        int size = 16;
        Random rand = new Random();
        PriorityQueue<Integer> pq = new PriorityQueue(minIntPriorityComparator, size);

        pq.insert(13);
        pq.insert(34);
        pq.insert(14);
        pq.insert(17);
        pq.insert(5);
        pq.insert(3);

        Deque<Integer> stack = new LinkedList<>();
        while (!pq.isEmpty()) {
            stack.add(pq.delMin());
        }
        System.out.println(stack);
    }

    @Test
    public void name() {
        int size = 16;
        Random rand = new Random();
        PriorityQueue<Integer> pq = new PriorityQueue(minIntPriorityComparator, size);
        for (int i = 0; i < size; i++) {
            pq.insert(rand.nextInt(100));
        }

        Deque<Integer> stack = new LinkedList<>();
        while (!pq.isEmpty()) {
            stack.add(pq.delMin());
        }
        System.out.println(stack);
    }
}