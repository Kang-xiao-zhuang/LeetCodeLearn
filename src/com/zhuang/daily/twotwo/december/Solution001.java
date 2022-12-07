package com.zhuang.daily.twotwo.december;

import java.util.*;

/**
 * description: Solution001
 * date: 2022/12/1 19:16
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.numDifferentIntegers("leet1234code234");
    }

    /**
     * https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
     * 2022.12.1
     *
     * @param x      整数
     * @param y      整数
     * @param points 数组
     * @return 距离你当前位置 曼哈顿距离 最近的 有效 点的下标
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDis = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < points.length; i++) {
            int px = points[i][0];
            int py = points[i][1];
            if (x == px || y == py) {
                int dis = Math.abs(x - px) + Math.abs(y - py);
                if (dis < minDis) {
                    minDis = dis;
                    ans = i;
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
     * 2022.12.2
     *
     * @param boxes 二进制字符串
     * @return 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数
     */
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int sm = 0;
            for (int j = 0; j < n; j++) {
                if (boxes.charAt(j) == '1') {
                    sm += Math.abs(j - i);
                }
            }
            res[i] = sm;
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/second-largest-digit-in-a-string/
     * 2022.12.3
     *
     * @param s 混合字符串
     * @return 返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1
     */
    public int secondHighest(String s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch) && !list.contains(ch - '0')) {
                list.add(ch - '0');
            }
        }
        if (list.isEmpty() || list.size() == 1) {
            return -1;
        }
        Collections.sort(list);
        return list.get(list.size() - 2);
    }

    public int secondHighest2(String s) {
        int first = -1;
        int second = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                if (num > first) {
                    second = first;
                    first = num;
                } else if (num < first && num > second) {
                    second = num;
                }
            }
        }
        return second;
    }


    int res;

    /**
     * https://leetcode.cn/problems/closest-dessert-cost/
     * 2022.12.4
     *
     * @param baseCosts    整数数组
     * @param toppingCosts 整数数组
     * @param target       整数
     * @return 返回最接近 target 的甜点成本。如果有多种方案，返回 成本相对较低 的一种
     */
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        res = Arrays.stream(baseCosts).min().getAsInt();
        for (int b : baseCosts) {
            dfs(toppingCosts, 0, b, target);
        }
        return res;
    }

    public void dfs(int[] toppingCosts, int p, int curCost, int target) {
        if (Math.abs(res - target) < curCost - target) {
            return;
        } else if (Math.abs(res - target) >= Math.abs(curCost - target)) {
            if (Math.abs(res - target) > Math.abs(curCost - target)) {
                res = curCost;
            } else {
                res = Math.min(res, curCost);
            }
        }
        if (p == toppingCosts.length) {
            return;
        }
        dfs(toppingCosts, p + 1, curCost + toppingCosts[p] * 2, target);
        dfs(toppingCosts, p + 1, curCost + toppingCosts[p], target);
        dfs(toppingCosts, p + 1, curCost, target);
    }

    /**
     * https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports/
     * 2022.12.5
     *
     * @param boxes      箱子数组
     * @param portsCount 整数
     * @param maxBoxes   整数
     * @param maxWeight  整数
     * @return 返回将所有箱子送到相应码头的 最少行程 次数
     */
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] p = new int[n + 1];
        int[] w = new int[n + 1];
        int[] neg = new int[n + 1];
        long[] W = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            p[i] = boxes[i - 1][0];
            w[i] = boxes[i - 1][1];
            if (i > 1) {
                neg[i] = neg[i - 1] + (p[i - 1] != p[i] ? 1 : 0);
            }
            W[i] = W[i - 1] + w[i];
        }

        Deque<Integer> opt = new ArrayDeque<>();
        opt.offerLast(0);
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            while (i - opt.peekFirst() > maxBoxes || W[i] - W[opt.peekFirst()] > maxWeight) {
                opt.pollFirst();
            }

            f[i] = g[opt.peekFirst()] + neg[i] + 2;

            if (i != n) {
                g[i] = f[i] - neg[i + 1];
                while (!opt.isEmpty() && g[i] <= g[opt.peekLast()]) {
                    opt.pollLast();
                }
                opt.offerLast(i);
            }
        }

        return f[n];
    }

    /**
     * https://leetcode.cn/problems/number-of-different-integers-in-a-string/
     * 2022.12.6
     *
     * @param word 字符串
     * @return 返回对 word 完成替换后形成的 不同 整数的数目
     */
    public int numDifferentIntegers(String word) {
        HashSet<String> set = new HashSet<>();
        String[] split = word.split("[a-z]+");
        for (String s : split) {
            if (s.length() > 0) {
                set.add(s.replaceAll("^0+", ""));
            }
        }
        return set.size();
    }

    public int numDifferentIntegers2(String word) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                while (i < word.length() && word.charAt(i) == '0') {
                    ++i;
                }
                int j = i;
                while (j < word.length() && Character.isDigit(word.charAt(j))) {
                    ++j;
                }
                set.add(word.substring(i, j));
                i = j;
            }
        }
        return set.size();
    }

    /**
     * https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/
     *
     * @param nums1 整数数组
     * @param nums2 整数数组
     * @return 返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数
     */
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if (6 * n < m || 6 * m < n) {
            return -1;
        }
        int[] cnt1 = new int[7];
        int[] cnt2 = new int[7];
        int diff = 0;
        for (int i : nums1) {
            ++cnt1[i];
            diff += i;
        }
        for (int i : nums2) {
            ++cnt2[i];
            diff -= i;
        }
        if (diff == 0) {
            return 0;
        }
        if (diff > 0) {
            return help(cnt2, cnt1, diff);
        }
        return help(cnt1, cnt2, -diff);
    }

    public int help(int[] h1, int[] h2, int diff) {
        int[] h = new int[7];
        for (int i = 1; i < 7; ++i) {
            h[6 - i] += h1[i];
            h[i - 1] += h2[i];
        }
        int res = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            int t = Math.min((diff + i - 1) / i, h[i]);
            res += t;
            diff -= t * i;
        }
        return res;
    }

}
