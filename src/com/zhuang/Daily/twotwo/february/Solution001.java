package com.zhuang.Daily.twotwo.february;

import java.util.*;

/**
 * @Classname Solution001
 * @Description 2022.2.1-2022.2.11 每日一题
 * @Date 2022/2/9 16:04
 * @Author by dell
 */

public class Solution001 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/longest-nice-substring/
     * 2.1
     *
     * @param s
     * @return
     */
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int maxPos = 0;
        int maxLen = 0;
        for (int i = 0; i < n; ++i) {
            int lower = 0;
            int upper = 0;
            for (int j = i; j < n; ++j) {
                if (Character.isLowerCase(s.charAt(j))) {
                    lower |= 1 << (s.charAt(j) - 'a');
                } else {
                    upper |= 1 << (s.charAt(j) - 'A');
                }
                if (lower == upper && j - i + 1 > maxLen) {
                    maxPos = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-prefix-of-word/
     * 2.2
     *
     * @param word 字符串
     * @param ch   字符
     * @return 结果字符串
     */
    public String reversePrefix(String word, char ch) {
        char[] cs = word.toCharArray();
        int n = cs.length, idx = -1;
        for (int i = 0; i < n && idx == -1; i++) {
            if (cs[i] == ch) idx = i;
        }
        int l = 0, r = idx;
        while (l < r) {
            char c = cs[l];
            cs[l++] = cs[r];
            cs[r--] = c;
        }
        return String.valueOf(cs);
    }

    /**
     * https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
     * 2.3
     *
     * @param k 数字
     * @return 次数
     */
    public int findMinFibonacciNumbers(int k) {
        List<Integer> f = new ArrayList<Integer>();
        f.add(1);
        int a = 1, b = 1;
        while (a + b <= k) {
            int c = a + b;
            f.add(c);
            a = b;
            b = c;
        }
        int ans = 0;
        for (int i = f.size() - 1; i >= 0 && k > 0; i--) {
            int num = f.get(i);
            if (k >= num) {
                k -= num;
                ans++;
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/number-of-rectangles-that-can-form-the-largest-square/
     * 2.4
     *
     * @param rectangles 数组
     * @return 矩形数目
     */
    public int countGoodRectangles(int[][] rectangles) {
        int res = 0, maxLen = 0;
        for (int[] rectangle : rectangles) {
            int l = rectangle[0], w = rectangle[1];
            int k = Math.min(l, w);
            if (k == maxLen) {
                ++res;
            } else if (k > maxLen) {
                res = 1;
                maxLen = k;
            }
        }
        return res;
    }


    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] grid;
    int m, n;
    int ans = 0;

    /**
     * https://leetcode-cn.com/problems/path-with-maximum-gold/
     * 2.5
     *
     * @param grid 方格
     * @return 数目
     */
    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != 0) {
                    dfs(i, j, 0);
                }
            }
        }
        return ans;
    }

    public void dfs(int x, int y, int gold) {
        gold += grid[x][y];
        ans = Math.max(ans, gold);

        int rec = grid[x][y];
        grid[x][y] = 0;

        for (int d = 0; d < 4; ++d) {
            int nx = x + dirs[d][0];
            int ny = y + dirs[d][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                dfs(nx, ny, gold);
            }
        }

        grid[x][y] = rec;
    }

    /**
     * https://leetcode-cn.com/problems/sum-of-unique-elements/submissions/
     * 2.6
     *
     * @param nums
     * @return
     */
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int num = entry.getKey(), c = entry.getValue();
            if (c == 1) {
                ans += num;
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/longest-happy-string/
     * 2.7
     *
     * @param a 字符串
     * @param b 字符串
     * @param c 字符串
     * @return 字符串
     */
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        if (a > 0) q.add(new int[]{0, a});
        if (b > 0) q.add(new int[]{1, b});
        if (c > 0) q.add(new int[]{2, c});
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 1) - 'a' == cur[0] && sb.charAt(n - 2) - 'a' == cur[0]) {
                if (q.isEmpty()) break;
                int[] next = q.poll();
                sb.append((char) (next[0] + 'a'));
                if (--next[1] != 0) q.add(next);
                q.add(cur);
            } else {
                sb.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0) q.add(cur);
            }
        }
        return sb.toString();
    }

    /**
     * https://leetcode-cn.com/problems/grid-illumination/
     * 2.8
     *
     * @param n       int
     * @param lamps   数组
     * @param queries 数组
     * @return 数组
     */
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<Integer, Integer>();
        Map<Integer, Integer> col = new HashMap<Integer, Integer>();
        Map<Integer, Integer> diagonal = new HashMap<Integer, Integer>();
        Map<Integer, Integer> antiDiagonal = new HashMap<Integer, Integer>();
        Set<Long> points = new HashSet<Long>();
        for (int[] lamp : lamps) {
            if (!points.add(hash(lamp[0], lamp[1]))) {
                continue;
            }
            row.put(lamp[0], row.getOrDefault(lamp[0], 0) + 1);
            col.put(lamp[1], col.getOrDefault(lamp[1], 0) + 1);
            diagonal.put(lamp[0] - lamp[1], diagonal.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            antiDiagonal.put(lamp[0] + lamp[1], antiDiagonal.getOrDefault(lamp[0] + lamp[1], 0) + 1);
        }
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0], c = queries[i][1];
            if (row.getOrDefault(r, 0) > 0) {
                ret[i] = 1;
            } else if (col.getOrDefault(c, 0) > 0) {
                ret[i] = 1;
            } else if (diagonal.getOrDefault(r - c, 0) > 0) {
                ret[i] = 1;
            } else if (antiDiagonal.getOrDefault(r + c, 0) > 0) {
                ret[i] = 1;
            }
            for (int x = r - 1; x <= r + 1; x++) {
                for (int y = c - 1; y <= c + 1; y++) {
                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        continue;
                    }
                    if (points.remove(hash(x, y))) {
                        row.put(x, row.get(x) - 1);
                        if (row.get(x) == 0) {
                            row.remove(x);
                        }
                        col.put(y, col.get(y) - 1);
                        if (col.get(y) == 0) {
                            col.remove(y);
                        }
                        diagonal.put(x - y, diagonal.get(x - y) - 1);
                        if (diagonal.get(x - y) == 0) {
                            diagonal.remove(x - y);
                        }
                        antiDiagonal.put(x + y, antiDiagonal.get(x + y) - 1);
                        if (antiDiagonal.get(x + y) == 0) {
                            antiDiagonal.remove(x + y);
                        }
                    }
                }
            }
        }
        return ret;
    }

    public long hash(int x, int y) {
        return (long) x + ((long) y << 32);
    }

    /**
     * https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k/
     * 2.9
     *
     * @param nums 整数数组
     * @param k 整数
     * @return 数目
     */
    public int countKDifference(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) ans++;
            }
        }
        return ans;
    }
}
