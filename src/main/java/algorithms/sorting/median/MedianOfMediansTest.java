package algorithms.sorting.median;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lichen
 * @date 2022-5-12 16:44
 */
public class MedianOfMediansTest {

    @Test
    public void testLargeScaleRandom() {
        Random rand = new Random();
        int N = 1000;
        int MAX = N - 1;
        List<Integer> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            list.add(MAX - i);
        }
        //shuffle
        int times = N - N / 5;
        for (int i = 0; i < times; i++) {
            int randI = rand.nextInt(N);
            Integer v = list.get(randI);
            list.set(randI, list.get(0));
            list.set(0, v);
        }

        for (Integer integer : list) {
            Integer k = MedianOfMedians.findK(list, integer);
            Assert.assertEquals("error", integer, k);
        }
    }

    @Test
    public void testSmallScale() {
        List<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(9 - i);
        }

        for (Integer integer : list) {
            Integer k = MedianOfMedians.findK(list, integer);
            Assert.assertEquals("error", integer, k);
        }
    }
}
