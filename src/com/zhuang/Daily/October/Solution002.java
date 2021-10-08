package com.zhuang.Daily.October;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname Solution002
 * @Description 2021.10.7-2021.10.7每日一题
 * @Date 2021/10/7 6:56
 * @Author by dell
 */

public class Solution002 {
    public static void main(String[] args) {
        //countSegments("Hello, my name is John");
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        findRepeatedDnaSequences(s);
    }

    /**
     * https://leetcode-cn.com/problems/number-of-segments-in-a-string/
     * 10.7
     *
     * @param s 字符串
     * @return 统计字符串中的单词个数
     */
    public static int countSegments(String s) {
        int count = 0;
        for (String s1 : s.split(" ")) {
            if (!s1.equals("")) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    public static int countSegments2(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/repeated-dna-sequences/
     * 10.8
     *
     * @param s 字符串
     * @return 所有目标子串
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) {
                res.add(str);
            }
        }
        System.out.println(res.toString());
        return res;
    }
}
