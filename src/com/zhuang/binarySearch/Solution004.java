package com.zhuang.binarySearch;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Classname Solution004
 * @Description 二分查找
 * @Date 2021/8/9 8:54
 * @Author by Zhuang
 */
public class Solution004 {
    public static void main(String[] args) {
        //    int[] nums = {2, 2, 2, 0, 1};
        //  findMin(nums);
        //    findMin2(nums);

        int[] nums = {1, 3, 4, 3, 2};
        int[] arr1 = {4, 5, 8};
        int[] arr2 = {10, 9, 1, 8};
        // findDuplicate(nums);
        //findDuplicate2(nums);
        //findDuplicate3(nums);
        findTheDistanceValue(arr1, arr2, 2);
    }

    /**
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
     *
     * @param nums 数组
     * @return int
     */
    public static int findMin(int[] nums) {
        Arrays.sort(nums);
        System.out.println(nums[0]);
        return nums[0];
    }

    /**
     * 二分法
     *
     * @param nums 数组
     * @return int
     */
    public static int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        System.out.println(nums[left]);
        return nums[left];
    }

    /**
     * https://leetcode-cn.com/problems/find-the-duplicate-number/
     *
     * @param nums 数组
     * @return int
     */
    public static int findDuplicate(int[] nums) {
        int[] counter = new int[nums.length + 1];
        int res = 0;
        for (int num : nums) {
            counter[num]++;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] >= 2) {
                res = i;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 二分法
     *
     * @param nums 数组
     * @return int
     */
    public static int findDuplicate2(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        System.out.println(res);
        return res;
    }

    public static int findDuplicate3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (set.add(num)) {
                set.add(num);
            } else {
                res = num;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode.cn/problems/find-the-distance-value-between-two-arrays/
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @param d    整数
     * @return 距离值
     */
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        int cnt = 0;
        for (int i : arr1) {
            boolean ok = true;
            for (int j : arr2) {
                ok &= Math.abs(i - j) > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    /**
     * 二分法
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @param d    整数
     * @return 距离值
     */
    public static int findTheDistanceValue2(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int cnt = 0;
        for (int i : arr1) {
            int p = binarySearch(arr2, i);
            boolean ok = true;
            if (p < arr2.length) {
                ok &= arr2[p] - i > d;
            }
            if (p - 1 >= 0 && p - 1 <= arr2.length) {
                ok &= i - arr2[p - 1] > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    /**
     * 二分查找
     *
     * @param arr    数组
     * @param target 目标值
     * @return 索引
     */
    private static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (arr[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
