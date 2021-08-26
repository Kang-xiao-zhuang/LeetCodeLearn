package com.zhuang.Daily.August;

import java.util.*;
import java.util.stream.LongStream;

/**
 * @Classname Solution001
 * @Description 2021.8.17-2021.8.25每日一题
 * @Date 2021/8/17 11:23
 * @Author by Zhuang
 */

public class Solution001 {

    public static void main(String[] args) {
        // String s = "PPALLP";
        //  checkRecord(s);
        //  checkRecord(2);

        //    String s = "leetcode";
        //   reverseVowels(s);

        //  String a = "abcdefg";
        // reverseStr(a, 2);

        // char[] chars = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        //  compress(chars);
        // compress2(chars);

        // int[][] ghost = {{1, 0}, {0, 3}};
        // int[] target = {0, 1};
        // escapeGhosts(ghost, target);

        // getMaximumGenerated(7);

        //int[][] filights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        //findCheapestPrice(3, filights, 0, 2, 1);

        int[][] graph = {{1, 2}, {3}, {3}, {}};
        allPathsSourceTarget(graph);

    }

    /**
     * https://leetcode-cn.com/problems/student-attendance-record-i/
     * 8.17
     *
     * @param s 字符串
     * @return 布尔
     */
    public static boolean checkRecord(String s) {
        int absent = 0, late = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                absent++;
                if (absent >= 2) {
                    return false;
                }
            }
            if (c == 'L') {
                late++;
                if (late >= 3) {
                    return false;
                }
            } else {
                late = 0;
            }
        }
        System.out.println(true);
        return true;
    }


    /**
     * https://leetcode-cn.com/problems/student-attendance-record-ii/
     * 8.18
     *
     * @param n 整数n
     * @return int
     */

    public static int checkRecord(int n) {
        int MOD = 1000000007;
        long[][] dp = new long[2][6];
        // 初始值
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][3] = 1;

        for (int i = 1; i < n; i++) {
            // 当前使用的下标
            int cur = i & 1;
            // 上一次使用的下标
            int last = (i - 1) & 1;
            dp[cur][0] = (dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][1] = dp[last][0];
            dp[cur][2] = dp[last][1];
            dp[cur][3] = (dp[last][3] + dp[last][4] + dp[last][5] + dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][4] = dp[last][3];
            dp[cur][5] = dp[last][4];
        }
        System.out.println((int) (LongStream.of(dp[(n - 1) & 1]).sum() % MOD));
        return (int) (LongStream.of(dp[(n - 1) & 1]).sum() % MOD);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
     * 8.19
     *
     * @param s 字符串
     * @return String
     */
    public static String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;

        char[] chars = s.toCharArray();
        while (left < right) {
            if (isVowel(chars[left]) && isVowel(chars[right])) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
            if (!isVowel(chars[left])) {
                left++;
            }
            if (!isVowel(chars[right])) {
                right--;
            }
        }
        System.out.println(Arrays.toString(chars));
        return new String(chars);
    }


    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }


    /**
     * https://leetcode-cn.com/problems/reverse-string-ii/
     * 8.20
     *
     * @param s 字符串
     * @param k 前k个字符
     * @return 字符串
     */
    public static String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = s.length();
        //每次前进2k，但是只交换前k个字符
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        System.out.println(Arrays.toString(arr));
        return new String(arr);
    }

    /**
     * 辅助函数 反转字符串
     *
     * @param arr   数组
     * @param left  左指针
     * @param right 右指针
     */
    public static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            /*
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
             */
            arr[left] ^= arr[right];
            arr[right] ^= arr[left];
            arr[left] ^= arr[right];
            left++;
            right--;
        }
    }

    /**
     * https://leetcode-cn.com/problems/string-compression/
     * 8.21 解法1
     *
     * @param chars 数组
     * @return int 长度
     */
    public static int compress(char[] chars) {
        int n = chars.length;
        int i = 0, j = 0;
        while (i < n) {
            int index = i;
            while (index < n && chars[index] == chars[i]) {
                index++;
            }
            int distance = index - i;
            // 指定插入的位置
            chars[j++] = chars[i];
            if (distance > 1) {
                int start = j;
                int end = start;
                while (distance != 0) {
                    chars[end++] = (char) ((distance % 10) + '0');
                    distance /= 10;
                }
                reverse(chars, start, end - 1);
                j = end;
            }
            i = index;
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(j);
        return j;
    }

    /**
     * 8.21 解法2
     *
     * @param chars 数组
     * @return 长度
     */
    public static int compress2(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(write);
        return write;
    }

    /**
     * https://leetcode-cn.com/problems/escape-the-ghosts/
     * 8.22每日一题
     *
     * @param ghosts 阻碍者
     * @param target 目的地
     * @return 布尔
     */
    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
        // 计算出人走的距离
        int human_path = Math.abs(target[0]) + Math.abs(target[1]);
        // 有几个鬼 那就遍历几次
        for (int i = 0; i < ghosts.length; i++) {
            // 鬼走的距离，通过曼哈顿距离来求出  distance=|x1,x2|+|y1-y2|
            int ghost_path = Math.abs(ghosts[i][0] - target[0]) + Math.abs(ghosts[i][1] - target[1]);
            // 两个距离比较，人距离小，成功，否则GG
            if (human_path >= ghost_path) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(true);
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/get-maximum-in-generated-array/
     * 8.23
     *
     * @param n 整数n
     * @return 新数组中的最大值
     */
    public static int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 0; i < n; i++) {
            if (2 * i <= n) {
                nums[2 * i] = nums[i];
            }
            if (2 * i + 1 <= n) {
                nums[2 * i + 1] = nums[i] + nums[i + 1];
            }
        }
        int res = 0;
        for (int num : nums) {
            res = Math.max(res, num);
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
     * 8.24
     *
     * @param n       n个城市
     * @param flights 飞机数组
     * @param src     出发城市
     * @param dst     目的地
     * @param k       站台数
     * @return 最便宜的价格
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; ++i) {
            Arrays.fill(f[i], INF);
        }
        f[0][src] = 0;
        for (int t = 1; t <= k + 1; ++t) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int t = 1; t <= k + 1; ++t) {
            ans = Math.min(ans, f[t][dst]);
        }
        System.out.println(ans == INF ? -1 : ans);
        return ans == INF ? -1 : ans;
    }

    /**
     * https://leetcode-cn.com/problems/all-paths-from-source-to-target/
     * 8.25
     *
     * @param graph 二维数组
     * @return 集合
     */

    static List<List<Integer>> ans = new ArrayList<List<Integer>>();
    static Deque<Integer> stack = new ArrayDeque<Integer>();

    /**
     * https://leetcode-cn.com/problems/all-paths-from-source-to-target/
     * 8.25
     *
     * @param graph 二维数组
     * @return List集合
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 栈加入0
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        System.out.println(ans.toString());
        return ans;
    }

    /**
     * dfs搜索方法
     *
     * @param graph 二维数组
     * @param x     int
     * @param n     int
     */
    public static void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            ans.add(new ArrayList<Integer>(stack));
            return;
        }
        for (int y : graph[x]) {
            stack.offerLast(y);
            dfs(graph, y, n);
            stack.pollLast();
        }
    }
}
