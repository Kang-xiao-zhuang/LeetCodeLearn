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
        Solution004 solution004 = new Solution004();
        solution004.letterCasePermutation("a1b2");

        solution004.magicalString(6);
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

    /**
     * https://leetcode.cn/problems/letter-case-permutation/
     * 2022.10.30
     *
     * @param s 字符串
     * @return 所有可能得到的字符串集合
     */
    public List<String> letterCasePermutation(String s) {
        ArrayList<String> list = new ArrayList<>();
        dfs(s.toCharArray(), 0, list);
        return list;
    }

    private void dfs(char[] arr, int pos, ArrayList<String> list) {
        while (pos < arr.length && Character.isDigit(arr[pos])) {
            pos++;
        }
        if (pos == arr.length) {
            list.add(new String(arr));
            return;
        }

        arr[pos] ^= 32;
        dfs(arr, pos + 1, list);
        arr[pos] ^= 32;
        dfs(arr, pos + 1, list);
    }

    public List<String> letterCasePermutation2(String s) {
        ArrayList<String> list = new ArrayList<>();
        Queue<StringBuilder> queue = new ArrayDeque<>();
        queue.offer(new StringBuilder());
        while (!queue.isEmpty()) {
            StringBuilder temp = queue.peek();
            if (temp.length() == s.length()) {
                list.add(temp.toString());
                queue.poll();
            } else {
                int pos = temp.length();
                if (Character.isLetter(s.charAt(pos))) {
                    StringBuilder sb = new StringBuilder(temp);
                    sb.append((char) (s.charAt(pos) ^ 32));
                    queue.offer(sb);
                }
                temp.append(s.charAt(pos));
            }
        }
        return list;
    }

    /**
     * https://leetcode.cn/problems/magical-string/
     * 2022.10.31
     *
     * @param n 整数
     * @return 在神奇字符串 s 的前 n 个数字中 1 的数目
     */
    public int magicalString(int n) {
        StringBuilder sb = new StringBuilder("122");
        for (int i = 2; sb.length() < n; ) {
            for (int j = sb.charAt(i++) - '0'; j-- > 0; ) {
                sb.append('1');
            }
            for (int j = sb.charAt(i++) - '0'; j-- > 0; ) {
                sb.append('2');
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') res++;
        }
        return res;
    }
}
