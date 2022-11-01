package com.zhuang.daily.twotwo.november;

/**
 * description: Solution001
 * date: 2022/11/1 15:08
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/
     * 2022.11.1
     *
     * @param word1 字符串数组
     * @param word2 字符串数组
     * @return 判断两个数组表示的字符串是否相同
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return join(word1).equals(join(word2));
    }

    public String join(String[] words) {
        StringBuilder ret = new StringBuilder();
        for (String s : words) {
            ret.append(s);
        }
        return ret.toString();
    }

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (String s : word1) {
            s1.append(s);
        }
        for (String s : word2) {
            s2.append(s);
        }
        return (s1.toString()).equals(s2.toString());
    }
}
