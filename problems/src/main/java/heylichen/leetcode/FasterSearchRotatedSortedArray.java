package heylichen.leetcode;

public class FasterSearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        return find(nums, 0, nums.length - 1, target);
    }

    private int find(int[] nums, int from, int to, int target) {
        if (from > to) {
            return -1;
        }
        if (from == to) {
            return nums[from] == target ? from : -1;
        }
        int middle = (from + to) / 2;
        if (nums[from] < nums[middle]) {
            if (target >= nums[from] && target <= nums[middle]) {
                return find(nums, from, middle, target);
            } else {
                return find(nums, middle + 1, to, target);
            }
        } else {
            if (target >= nums[middle + 1] && target <= nums[to]) {
                return find(nums, middle + 1, to, target);
            } else {
                return find(nums, from, middle, target);
            }
        }
    }
}
