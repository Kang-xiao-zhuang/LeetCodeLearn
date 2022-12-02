package com.zhuang.daily.twotwo.december;

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
}
