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
     * https://leetcode-cn.com/problems/maximum-subarray/
     * @param nums 数组1
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
     * https://leetcode-cn.com/problems/pascals-triangle/
     *
     * @param numRows 数组
     * @return 集合
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
