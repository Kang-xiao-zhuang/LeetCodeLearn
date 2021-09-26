package com.zhuang.LeetCodeHot100;

import java.util.*;

/**
 * @Classname Solution03
 * @Description LeetCode 热题 HOT
 * @Date 2021/9/26 9:41
 * @Author by Zhuang
 */
public class Solution03 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        //permute(nums);
        //permute2(nums);
    }


    /**
     * https://leetcode-cn.com/problems/permutations/
     * 第46题
     *
     * @param nums 数组
     * @return 所有可能的全排列
     */
    public static List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> output = new ArrayList<>();

        for (int num : nums) {
            output.add(num);
        }
        backtrack(nums.length, output, res, 0);
        System.out.println(res.toString());
        return res;
    }

    /**
     * 回溯
     *
     * @param length 数组长度
     * @param output 输出数组
     * @param res    结果数组
     * @param index  索引
     */
    private static void backtrack(int length, ArrayList<Integer> output, ArrayList<List<Integer>> res, int index) {
        if (index == length) {
            res.add(new ArrayList<>(output));
        }
        for (int i = index; i < length; i++) {
            // 交换数组中的值
            Collections.swap(output, index, i);
            // 递归执行下一个 数
            backtrack(length, output, res, index + 1);
            // 撤销操作
            Collections.swap(output, index, i);
        }
    }

    /**
     * @param nums 数组
     * @return 所有可能的全排列
     */
    public static List<List<Integer>> permute2(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        // 布尔数组，判断数字是否被使用
        boolean[] used = new boolean[nums.length];
        dfs(nums, nums.length, 0, path, used, res);
        System.out.println(res.toString());
        return res;
    }

    /**
     * 深度优先搜索
     *
     * @param nums   数组
     * @param length 数组长度
     * @param depth  深度
     * @param path   路径
     * @param used   布尔数组
     * @param res    结果集合
     */
    private static void dfs(int[] nums, int length, int depth, Deque<Integer> path, boolean[] used, ArrayList<List<Integer>> res) {
        // 如果深度等于长度
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            // 开始遍历，深度+1
            dfs(nums, length, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }
    }
}
