package com.zhuang.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Deacription 数组刷题学习
 * @Author Zhuang
 * @Date 2021/7/22 10:58
 * @Version 1.0
 **/
public class Solution001 {
    public static void main(String[] args) {
        /*
        int[] nums ={-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray(nums);
         */
        /*
        int[] nums={3,2,2,3};
        removeElement(nums,3);
         */
        //     generate(5);
        /*
        int[] nums={1,3,5,6};
        searchInsert(nums,0);
         */
        getRow(0);
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
     *
     * https://leetcode-cn.com/problems/maximum-subarray/
     * @param nums
     * @return res
     */
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else
                sum = num;
            res = Math.max(res, sum);
        }
        System.out.println(res);
        return res;
    }

    /**
     * 给你一个数组 num和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * https://leetcode-cn.com/problems/remove-element/
     *
     * @param nums 数组
     * @param val 值
     * @return int
     */
    public static int removeElement(int[] nums, int val) {
        // 快慢指针
        int fastIndex = 0;
        int slowIndex;
        for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        System.out.println(slowIndex);
        return slowIndex;
    }

    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * <p>
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * <p>
     * https://leetcode-cn.com/problems/pascals-triangle/
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        System.out.println(ret.toString());
        return ret;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * <p>
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * https://leetcode-cn.com/problems/search-insert-position/
     *
     * @param nums 数组
     * @param target 目标值
     * @return int
     */
    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                System.out.println(i);
                return i;
            }
        }
        System.out.println(nums.length);
        return nums.length;
    }

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     * <p>
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和
     * <p>
     * https://leetcode-cn.com/problems/pascals-triangle-ii/
     *
     * @param rowIndex 数组
     * @param rowIndex 索引
     * @return list
     */
    public static List<Integer> getRow(int rowIndex) {
        ArrayList<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        System.out.println(ret.get(rowIndex));
        return ret.get(rowIndex);
    }
}
