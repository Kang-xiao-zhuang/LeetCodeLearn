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
        //int[] nums = {1, 2, 3};
        //permute(nums);
        //permute2(nums);

        //int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //rotate(matrix);

        //String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        //groupAnagrams(strs);
        //groupAnagrams2(strs);

        //int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //maxSubArray(nums);
        //maxSubArray2(nums);
        //int[] nums = {3, 2, 1, 0, 4};
        //canJump(nums);
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

    /**
     * https://leetcode-cn.com/problems/rotate-image/
     * 第48题
     *
     * @param matrix 二维数组
     */
    public static void rotate(int[][] matrix) {
        // 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置
        int n = matrix.length;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * https://leetcode-cn.com/problems/group-anagrams/
     * 第49题
     * 排序
     *
     * @param strs 字符串组
     * @return 按任意顺序返回结果列表
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            // 对数组排序
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        System.out.println(new ArrayList<List<String>>(map.values()).toString());
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 计数
     *
     * @param strs 字符串组
     * @return 按任意顺序返回结果列表
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int len = str.length();
            for (int i = 0; i < len; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            //将每个出现次数大于0的字母和出现次数按顺序拼接字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) 'a' + i);
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        System.out.println(new ArrayList<List<String>>(map.values()).toString());
        return new ArrayList<List<String>>(map.values());
    }


    /**
     * https://leetcode-cn.com/problems/maximum-subarray/
     * 第53题
     *
     * @param nums 整数数组
     * @return 最大和
     */
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        System.out.println(res);
        return res;
    }

    /**
     * 动态规划
     *
     * @param nums 整数数组
     * @return 最大和
     */
    public static int maxSubArray2(int[] nums) {
        int pre = 0, maxSum = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxSum = Math.max(maxSum, pre);
        }
        System.out.println(maxSum);
        return maxSum;
    }

    /**
     * https://leetcode-cn.com/problems/jump-game/
     * 第55题
     *
     * @param nums 非负整数数组
     * @return 是否能够到达最后一个下标
     */
    public static boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                System.out.println(false);
                return false;
            }
            reach = Math.max(i + nums[i], reach);
        }
        System.out.println(true);
        return true;
    }
}
