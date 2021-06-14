package heylichen.leetcode;

import com.alibaba.fastjson.JSONArray;
import heylichen.utils.FileHelper;
import org.junit.Test;

import java.io.IOException;

public class MaxSlidingWindowTest {

    private MaxSlidingWindow bean = new MaxSlidingWindow();

    @Test
    public void name() throws IOException {
        JSONArray ja = FileHelper.readJSONArray("maxSlidingWindow.json");
        int k = 26779;
        int[] nums = new int[ja.size()];
        for (int i = 0; i < ja.size(); i++) {
            nums[i] = ja.getInteger(i);
        }
        long start = System.currentTimeMillis();
        int[] result = bean.maxSlidingWindow(nums, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void name2() throws IOException {
        int k = 3;
        int[] nums = new int[]{1,3,1,2,0,5};

        long start = System.currentTimeMillis();
        int[] result = bean.maxSlidingWindow(nums, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}