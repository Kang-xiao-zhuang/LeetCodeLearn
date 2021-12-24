package com.zhuang.Daily.December;

import java.util.PriorityQueue;

/**
 * @Classname Solution004
 * @Description 2021.12.24-2021.12.31每日一题
 * @Date 2021/12/24 22:16
 * @Author by dell
 */

public class Solution004 {

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/
     * 12.24
     *
     * @param apples 数组
     * @param days   天数
     * @return 最大数目
     */
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;
        while (time < n || !q.isEmpty()) {
            if (time < n && apples[time] > 0) q.add(new int[]{time + days[time] - 1, apples[time]});
            while (!q.isEmpty() && q.peek()[0] < time) q.poll();
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if (--cur[1] > 0 && cur[0] > time) q.add(cur);
                ans++;
            }
            time++;
        }
        return ans;
    }
}
