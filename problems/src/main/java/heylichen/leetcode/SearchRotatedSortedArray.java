package heylichen.leetcode;

public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int k = findReverseIndex(nums, 0, nums.length - 1);
        if (k == -1) {
            //even if not rotated
            k = nums.length - 1;
        }
        int index = findTarget(nums, 0, k, target);
        if (index != -1) {
            return index;
        }
        return findTarget(nums, k + 1, nums.length - 1, target);
    }

    private int findTarget(int[] nums, int from, int to, int target) {
        if (from > to) {
            return -1;
        }
        if (from == to) {
            return nums[from] == target ? from : -1;
        }
        int middle = (from + to) / 2;
        int index = findTarget(nums, from, middle, target);
        if (index != -1) {
            return index;
        }
        return findTarget(nums, middle + 1, to, target);
    }

    private int findReverseIndex(int[] nums, int from, int to) {
        if (from + 1 == to) {
            if (nums[from] > nums[to]) {
                return from;
            }
        }
        int middle = (from + to) / 2;
        int index = -1;
        if (nums[middle] > nums[middle + 1]) {
            return middle;
        }

        if (from < middle) {
            index = findReverseIndex(nums, from, middle);
            if (index >= 0) {
                return index;
            }
        }
        if (middle + 1 < to) {
            index = findReverseIndex(nums, middle + 1, to);
            if (index >= 0) {
                return index;
            }
        }
        return index;
    }
}
