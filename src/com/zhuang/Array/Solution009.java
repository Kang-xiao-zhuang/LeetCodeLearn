package com.zhuang.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 数组学习
 * @Author Zhuang
 * @Date 2021/8/5 13:31
 * @Version 1.0
 **/
public class Solution009 {

    public static void main(String[] args) {
        //   int[] nums = {3, 2, 3};
        //    majorityElement(nums);
        //    majorityElement2(nums);

        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums);
    }

    /**
     * https://leetcode-cn.com/problems/majority-element/
     *
     * @param nums 数组
     * @return int
     */
    public static int majorityElement(int[] nums) {
        int temp = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                temp = nums[i];
            }
            if (temp == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        System.out.println(temp);
        return temp;
    }

    public static int majorityElement2(int[] nums) {
        int res = nums.length / 2;
        Arrays.sort(nums);
        System.out.println(nums[res]);
        return nums[res];
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     *
     * @param nums 数组
     * @return List集合
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return list;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    list.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(list.toString());
        return list;
    }
}
