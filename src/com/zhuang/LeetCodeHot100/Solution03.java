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


        //uniquePaths(3, 7);
        //uniquePaths2(3, 7);

        //int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        //merge(intervals);

        //int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        //minPathSum(grid);

        //int[] nums = {2, 0, 2, 1, 1, 0};
        //sortColors(nums);

        int[] nums = {1, 2, 4};
        subsets(nums);
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

    /**
     * https://leetcode-cn.com/problems/unique-paths/
     * 动态规划
     *
     * @param m 宽
     * @param n 长
     * @return 总共有多少条不同的路径
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[m - 1][n - 1]);
        return dp[m - 1][n - 1];
    }

    /**
     * 动态规划+滚动数组
     *
     * @param m 宽
     * @param n 长
     * @return 总共有多少条不同的路径
     */
    public static int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int j = 1; j < m; j++) {
            for (int i = 1; i < n; i++) {
                dp[i] += dp[i - 1];
            }
        }
        System.out.println(dp[n - 1]);
        return dp[n - 1];
    }

    /**
     * https://leetcode-cn.com/problems/merge-intervals/
     *
     * @param intervals 若干区间的集合
     * @return 一个不重叠的区间数组
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * https://leetcode-cn.com/problems/minimum-path-sum/
     * 第64题
     *
     * @param grid m x n 网格 grid
     * @return 路径上的数字总和为最小
     */
    public static int minPathSum(int[][] grid) {
        // 上来先判断
        if (grid == null || grid[0].length == 0 || grid.length == 0) {
            return 0;
        }
        int rows = grid.length, cols = grid[0].length;
        // 二维动态数组
        int[][] dp = new int[rows][cols];
        // 初始化
        dp[0][0] = grid[0][0];
        // 行初始化
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 列初始化
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        System.out.println(dp[rows - 1][cols - 1]);
        return dp[rows - 1][cols - 1];
    }

    /**
     * https://leetcode-cn.com/problems/climbing-stairs/
     * 第70题
     *
     * @param n n阶楼梯
     * @return int 次数
     */
    public static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        System.out.println(r);
        return r;
    }

    /**
     * @param n n级台阶
     * @return int
     */
    public static int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
        return dp[n];
    }

    /**
     * 斐波那契数列 滚动数组
     *
     * @param n n阶楼梯
     * @return int
     */
    public static int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        System.out.println(second);
        return second;
    }

    /**
     * https://leetcode-cn.com/problems/sort-colors/
     * 第75题
     *
     * @param nums 数组
     */
    public static void sortColors(int[] nums) {
        int n = nums.length;
        // 定义一个指针
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }
        for (int i = p; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * @param nums 数组
     */
    public static void sortColors2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 2) {
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;
                right--;
            } else if (nums[i] == 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left++;
                i++;
            } else {
                i++;
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/subsets/
     * 第78题
     *
     * @param nums 数组
     * @return 集合
     */
    public static List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        System.out.println(res.toString());
        return res;
    }

}
