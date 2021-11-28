package com.zhuang.Daily.November;

import java.util.ArrayList;
import java.util.Arrays;
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
}
