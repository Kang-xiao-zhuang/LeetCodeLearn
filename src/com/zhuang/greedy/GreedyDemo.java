package com.zhuang.greedy;

import java.util.Arrays;

/**
 * @Description 贪心例子
 * @Author Zhuang
 * @Date 2023/8/9 09:41
 * @Version 1.0
 **/
public class GreedyDemo {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode.cn/problems/assign-cookies/
     *
     * @param g 小孩数组
     * @param s 饼干数组
     * @return 满足的小孩数量
     */
    public int findContentChildren(int[] g, int[] s) {
        // 排序
        Arrays.sort(g);
        Arrays.sort(s);
        int start = s.length - 1;
        int count = 0;
        // 先满足大胃口
        for (int index = g.length - 1; index >= 0; index--) {
            if (start >= 0 && g[index] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }

    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                count++;
            }
        }
        return count;
    }
}
