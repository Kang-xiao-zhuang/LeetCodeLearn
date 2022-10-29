package com.zhuang.daily.twotwo.october;

import java.util.*;

/**
 * description: Solution004
 * date: 2022/10/28 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution004 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/sum-of-subarray-minimums/
     * 2022.10.28
     *
     * @param arr 整数数组
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        Deque<Integer> monoStack = new ArrayDeque<Integer>();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && arr[i] <= arr[monoStack.peek()]) {
                monoStack.pop();
            }
            left[i] = i - (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }
        monoStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && arr[i] < arr[monoStack.peek()]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek()) - i;
            monoStack.push(i);
        }
        long ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) left[i] * right[i] * arr[i]) % MOD;
        }
        return (int) ans;
    }

    /**
     * https://leetcode.cn/problems/count-items-matching-a-rule/
     * <p>
     * 2022.10.29
     *
     * @param items     类型 颜色 名称
     * @param ruleKey   字符串
     * @param ruleValue 字符串
     * @return 匹配检索规则的物品数量
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("type", 0);
        map.put("color", 1);
        map.put("name", 2);
        int count = 0;
        for (List<String> item : items) {
            if (item.get(map.get(ruleKey)).equals(ruleValue)) {
                count++;
            }
        }
        return count;
    }
}
