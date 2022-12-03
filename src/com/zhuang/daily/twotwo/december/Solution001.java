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
}
