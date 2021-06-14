package heylichen.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class SearchRotatedSortedArrayTest {
    private SearchRotatedSortedArray searchRotatedSortedArray = new SearchRotatedSortedArray();

    @Test
    public void testOne() {
        int[] nums = new int[]{1};
        Assert.assertEquals(0, searchRotatedSortedArray.search(nums, 1));
        Assert.assertEquals(-1, searchRotatedSortedArray.search(nums, 0));
    }

    @Test
    public void testOne2() {
        int[] nums = new int[]{6,1,2,3,4,5};
        Assert.assertEquals(-1, searchRotatedSortedArray.search(nums, 0));
        Assert.assertEquals(0, searchRotatedSortedArray.search(nums, 6));
        Assert.assertEquals(5, searchRotatedSortedArray.search(nums, 5));
    }

    @Test
    public void testOn3() {
        int[] nums = new int[]{2,3,4,5,6,1};
        Assert.assertEquals(-1, searchRotatedSortedArray.search(nums, 0));
        Assert.assertEquals(0, searchRotatedSortedArray.search(nums, 2));
        Assert.assertEquals(1, searchRotatedSortedArray.search(nums, 3));
        Assert.assertEquals(5, searchRotatedSortedArray.search(nums, 1));
    }

    @Test
    public void testOn4() {
        int[] nums = new int[]{5,6,7,1,2,3,4};
        Assert.assertEquals(-1, searchRotatedSortedArray.search(nums, 0));
        Assert.assertEquals(0, searchRotatedSortedArray.search(nums, 5));
        Assert.assertEquals(6, searchRotatedSortedArray.search(nums, 4));
        Assert.assertEquals(3, searchRotatedSortedArray.search(nums, 1));
    }

    @Test
    public void testOn5() {
        int[] nums = new int[]{1,3};
        Assert.assertEquals(-1, searchRotatedSortedArray.search(nums, 0));
        Assert.assertEquals(0, searchRotatedSortedArray.search(nums, 1));
        Assert.assertEquals(1, searchRotatedSortedArray.search(nums, 3));
    }
}