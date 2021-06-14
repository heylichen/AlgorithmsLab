package heylichen.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {

    //按官方解法，单调队列法
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] result = new int[nums.length - k + 1];
        result[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            while (!deque.isEmpty()) {
                int maxIndex = deque.peekFirst();
                if (maxIndex >= i - k + 1) {
                    result[i - k + 1] = nums[maxIndex];
                    break;
                } else {
                    deque.pollFirst();
                }
            }
        }
        return result;
    }

    //第一版优化，算法复杂度不变，常数变小
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        int lastStart = nums.length - k;
        int maxIndex = -1;
        for (int i = 0; i <= lastStart; i++) {
            if (i <= maxIndex) {
                int currentWindowsEnd = i + k - 1;
                if (nums[currentWindowsEnd] > result[i - 1]) {
                    result[i] = nums[currentWindowsEnd];
                    maxIndex = currentWindowsEnd;
                } else {
                    result[i] = result[i - 1];
                }
                continue;
            }

            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                    maxIndex = j;
                }
            }
            result[i] = max;
        }
        return result;
    }

    public int[] basicMaxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        int lastStart = nums.length - k;

        for (int i = 0; i <= lastStart; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[i] = max;
        }
        return result;
    }
}
