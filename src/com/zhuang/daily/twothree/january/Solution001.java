package com.zhuang.daily.twothree.january;

import java.util.HashMap;

/**
 * description: Solution001
 * date: 2023/1/1 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.repeatedCharacter("abcdd");
    }

    /**
     * https://leetcode.cn/problems/first-letter-to-appear-twice/
     * 2023.1.1
     *
     * @param s 字符串
     * @return 找出并返回第一个出现 两次 的字母
     */
    public char repeatedCharacter(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char res = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2) {
                res = c;
                break;
            }
        }
        return res;
    }
}
