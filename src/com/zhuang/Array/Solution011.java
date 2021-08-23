package com.zhuang.Array;

import java.util.PriorityQueue;

/**
 * @Classname Solution011
 * @Description 数组学习
 * @Date 2021/8/21 21:29
 * @Author by Zhuang
 */

public class Solution011 {
    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 2, 2, 3};
        //removeDuplicates(nums);
        //removeDuplicates2(nums);
        // removeDuplicates3(nums);

        // int[] arr = {3, 2, 1, 5, 6, 4};
        // findKthLargest(arr, 2);

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
       // maxArea(height);
        maxArea2(height);
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
     *
     * @param nums 数组
     * @return int
     */
    public static int removeDuplicates(int[] nums) {
        int len = 0;
        for (int num : nums) {
            if (len < 2 || nums[len - 2] != num) {
                nums[len++] = num;
            }
        }
        System.out.println(len);
        return len;
    }

    /**
     * 双指针法
     *
     * @param nums 数组
     * @return int
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        // 定义两个指针，目标指针j，待移动指针i从1开始，计数器count
        int j = 1;
        int count = 1;
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 与前一个元素比较
            if (nums[i] == nums[i - 1]) {
                // 相同count++
                count++;
            } else {
                //否则置为1
                count = 1;
            }
            // 如果计数器小于等于2,将i处的值赋给j处
            if (count <= 2) {
                nums[j] = nums[i];
                // j自增到下个位置
                j++;
            }
        }
        System.out.println(j);
        return j;
    }

    /**
     * 双指针法官解
     *
     * @param nums 数组
     * @return int
     */
    public static int removeDuplicates3(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int slow = 2;
        int fast = 2;
        while (fast < nums.length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        System.out.println(slow);
        return slow;
    }

    /**
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     *
     * @param nums 数组
     * @param k    第K个大的元素
     * @return int
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        System.out.println(queue.poll());
        return queue.poll();
    }

    /**
     * https://leetcode-cn.com/problems/container-with-most-water/
     * 暴力法
     *
     * @param height 高度
     * @return 盛水最多的最大值
     */
    public static int maxArea(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 双指针法
     *
     * @param height 高度
     * @return 盛水最多的最大值
     */
    public static int maxArea2(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            // 计算水的容量
            int maxArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, maxArea);
            // 判断移动指针
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(res);
        return res;
    }
}
