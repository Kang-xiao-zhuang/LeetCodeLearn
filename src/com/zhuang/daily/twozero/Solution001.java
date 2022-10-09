package com.zhuang.daily.twozero;

/**
 * description: Solution001
 * date: 2022/10/9 9:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
     * 2020.4.1
     *
     * @param seq 有效括号字符串
     * @return 将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小
     */
    public int[] maxDepthAfterSplit(String seq) {
        // 定义深度
        int depth = 0;
        int len = seq.length();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                ans[i] = depth % 2;
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }
        return ans;
    }
}
