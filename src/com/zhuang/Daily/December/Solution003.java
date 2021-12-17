package com.zhuang.Daily.December;

import java.util.*;

/**
 * @Classname Solution003
 * @Description 2021.12.15-2021.12.22每日一题
 * @Date 2021/12/15 10:26
 * @Author by dell
 */
public class Solution003 {
    public static void main(String[] args) {
        numWaterBottles(9,3);
    }

    /**
     * https://leetcode-cn.com/problems/loud-and-rich/
     * 12.15
     *
     * @param richer 数组
     * @param quiet  安静值
     * @return 整数数组
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] in = new int[n];
        for (int[] r : richer) {
            int a = r[0], b = r[1];
            List<Integer> list = map.getOrDefault(a, new ArrayList<>());
            list.add(b);
            map.put(a, list);
            in[b]++;
        }
        Deque<Integer> d = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
            if (in[i] == 0) {
                d.addLast(i);
            }
        }
        while (!d.isEmpty()) {
            int t = d.pollFirst();
            for (int u : map.getOrDefault(t, new ArrayList<>())) {
                if (quiet[ans[t]] < quiet[ans[u]]) {
                    ans[u] = ans[t];
                }
                if (--in[u] == 0) {
                    d.addLast(u);
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-visible-points/
     * 12.16
     *
     * @param points   数组
     * @param angle    整数
     * @param location 位置
     * @return 看到的点的最大数目
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int x = location.get(0), y = location.get(1);
        List<Double> list = new ArrayList<>();
        int cnt = 0;
        double pi = Math.PI, t = angle * pi / 180;
        for (List<Integer> p : points) {
            int a = p.get(0), b = p.get(1);
            if (a == x && b == y && ++cnt >= 0) {
                continue;
            }
            list.add(Math.atan2(b - y, a - x));
        }
        Collections.sort(list);
        int n = list.size(), max = 0;
        for (int i = 0; i < n; i++) {
            list.add(list.get(i) + 2 * pi);
        }
        for (int i = 0, j = 0; j < 2 * n; j++) {
            while (i < j && list.get(j) - list.get(i) > t) {
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return cnt + max;
    }

    /**
     * https://leetcode-cn.com/problems/water-bottles/
     * 12.17
     *
     * @param numBottles  酒瓶
     * @param numExchange 空酒瓶
     * @return 总数
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int bottle = numBottles, ans = numBottles;
        while (bottle >= numExchange) {
            bottle -= numExchange;
            ++ans;
            ++bottle;
        }
        System.out.println(ans);
        return ans;
    }
}
