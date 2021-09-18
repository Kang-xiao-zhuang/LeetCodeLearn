package com.zhuang.LeetCodeHot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname Solution02
 * @Description LeetCode 热题 HOT 6-10
 * @Date 2021/9/18 14:41
 * @Author by Zhuang
 */
public class Solution02 {
    public static void main(String[] args) {
        //int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        //maxArea(height);

        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums);
    }


    /**
     * https://leetcode-cn.com/problems/container-with-most-water/
     * 第7题
     *
     * @param height 高度
     * @return 最大容量
     */
    public static int maxArea(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        // 定义两个指针
        int left = 0;
        int right = len - 1;
        while (left < right) {
            // 计算水的容量
            int maxArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, maxArea);
            // 根据条件来调整指针
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     *
     * @param nums 数组
     * @return 所有和为0的不重复三元组
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return res;
        }
        // 数组先排序
        Arrays.sort(nums);

        // 循环遍历数组
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            // 定义双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                // 如果等于目标值，那么就加入到集合中
                if (nums[left] + nums[right] == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    // nums[left] == nums[left - 1]时，左指针后移
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // nums[right] == nums[right + 1]时，右指针前移
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    // 和小于目标值，左指针后移
                } else if (nums[left] + nums[right] < target) {
                    left++;
                    // 和大于目标值，右指针前移
                } else {
                    right--;
                }
            }
        }
        System.out.println(res.toString());
        return res;
    }
}
