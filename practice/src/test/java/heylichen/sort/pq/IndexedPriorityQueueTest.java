package heylichen.sort.pq;

import heylichen.test.AppTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IndexedPriorityQueueTest extends AppTestContext {
    @Autowired
    private PriorityComparator<Integer> minIntPriorityComparator;

    @Test
    public void testIndexedPq() {
        IndexedPriorityQueue<Integer> indexedMinPq = new IndexedPriorityQueue(minIntPriorityComparator, 16);
        repopulate(indexedMinPq);

        Integer expected = 7;
        Assert.assertEquals("min error", expected, indexedMinPq.min());
        Assert.assertEquals("minId error", 9, indexedMinPq.minId());

        assertDeleteMin(indexedMinPq, 9);
        assertDeleteMin(indexedMinPq, 6);
        assertDeleteMin(indexedMinPq, 2);
        assertDeleteMin(indexedMinPq, 8);
        assertDeleteMin(indexedMinPq, 7);
        assertDeleteMin(indexedMinPq, 3);
        assertDeleteMin(indexedMinPq, 4);
        assertDeleteMin(indexedMinPq, 1);
        assertDeleteMin(indexedMinPq, 5);
        Assert.assertEquals("must be empty", true, indexedMinPq.isEmpty());
    }

    @Test
    public void testContains() {
        IndexedPriorityQueue<Integer> indexedMinPq = new IndexedPriorityQueue(minIntPriorityComparator, 16);
        repopulate(indexedMinPq);
        Assert.assertEquals("contains", true, indexedMinPq.contains(4));
        Assert.assertEquals("contains", false, indexedMinPq.contains(10));
    }

    @Test
    public void testDelete() {
        IndexedPriorityQueue<Integer> indexedMinPq = new IndexedPriorityQueue(minIntPriorityComparator, 16);
        repopulate(indexedMinPq);
        Assert.assertEquals("contains", true, indexedMinPq.contains(4));
        indexedMinPq.delete(4);
        Assert.assertEquals("contains", false, indexedMinPq.contains(4));
    }

    @Test
    public void testChange() {
        IndexedPriorityQueue<Integer> indexedMinPq = new IndexedPriorityQueue(minIntPriorityComparator, 16);
        repopulate(indexedMinPq);
        indexedMinPq.change(5, 6);
        int minId = indexedMinPq.delMin();
        Assert.assertEquals(5, minId);

        indexedMinPq.change(1, 10);

        minId = indexedMinPq.delMin();
        Assert.assertEquals(9, minId);
        minId = indexedMinPq.delMin();
        Assert.assertEquals(1, minId);
        minId = indexedMinPq.delMin();
        Assert.assertEquals(6, minId);
    }

    private void assertDeleteMin(IndexedPriorityQueue<Integer> indexedMinPq, int expectedMinId) {
        int minId = indexedMinPq.delMin();
        Assert.assertEquals("delMin", expectedMinId, minId);
    }

    private void repopulate(IndexedPriorityQueue<Integer> indexedMinPq) {
        while (!indexedMinPq.isEmpty()) {
            indexedMinPq.delMin();
        }
        indexedMinPq.insert(1, 87);
        indexedMinPq.insert(2, 13);
        indexedMinPq.insert(3, 56);
        indexedMinPq.insert(4, 78);
        indexedMinPq.insert(5, 92);
        indexedMinPq.insert(6, 12);
        indexedMinPq.insert(7, 34);
        indexedMinPq.insert(8, 17);
        indexedMinPq.insert(9, 7);
    }
}