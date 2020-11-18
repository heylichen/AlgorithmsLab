package heylichen.structures.advanced;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FibonacciHeapTest {

    private Random random = new Random();

    @Test
    public void name() {
        FibonacciHeap<Integer, Integer> h = new FibonacciHeap<>(Integer.MIN_VALUE);
        List<Integer> list = createList();
        System.out.println(list);
        for (Integer integer : list) {
            h.insert(integer, integer);
        }
        while (!h.isEmpty()) {
            System.out.println(h.extractMin().getKey());
        }
    }



    private List<Integer> createList() {
        List<Integer> raw = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            raw.add(10 - i);
        }
        return raw;
    }
}