package com.zhuang.Daily.November;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Classname Solution004
 * @Description 2021.11.27-2021.11.30每日一题
 * @Date 2021/11/27 11:24
 * @Author by dell
 */

public class Solution004 {
    public static void main(String[] args) {
        findAnagrams("cbaebabacd", "abc");
    }

    //11.27笔记里有

    /**
     * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
     * 11.28
     *
     * @param s 字符串
     * @param p 字符串
     * @return 异位词
     */
    public static List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }
        for (int i = 0; i < sLen - pLen; i++) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/
     * 11.30
     *
     * @param arr 数组
     * @param k   整数k
     * @return 第K个最小的素数分数
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        List<int[]> frac = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                frac.add(new int[]{arr[i], arr[j]});
            }
        }
        Collections.sort(frac, (x, y) -> x[0] * y[1] - y[0] * x[1]);
        return frac.get(k - 1);
    }
}
