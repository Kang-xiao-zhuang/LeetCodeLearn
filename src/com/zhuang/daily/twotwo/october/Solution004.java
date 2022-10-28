package com.zhuang.daily.twotwo.october;

import java.util.ArrayDeque;
import java.util.Deque;

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
}
