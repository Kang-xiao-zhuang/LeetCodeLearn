package com.zhuang.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 数组刷题学习
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
         */
        getRow(0);
    }

    /**
     * https://leetcode-cn.com/problems/maximum-subarray/
     *
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
     * 快慢指针法
     *
     * @param nums 数组
     * @param val  值
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

    //暴力解法
    public static int removeElement2(int[] nums, int val) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < n; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                n--;
            }
        }
        return n;
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
        System.out.println(ret);
        return ret;
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
        return ret.get(rowIndex);
    }
}
