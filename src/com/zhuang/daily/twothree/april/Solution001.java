package com.zhuang.daily.twothree.april;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution001 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/sort-the-people/
     * 2023.4.25
     *
     * @param names   字符串数组
     * @param heights 正整数组成的数组
     * @return 身高降序顺序返回对应的名字数组 names
     */
    public String[] sortPeople(String[] names, int[] heights) {

        Integer[] height = new Integer[heights.length];
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], names[i]);
            height[i] = heights[i];
        }
        Arrays.sort(height, (o1, o2) -> o2 - o1);

        String[] res = new String[heights.length];
        for (int i = 0; i < height.length; i++) {
            res[i] = map.get(height[i]);
        }
        return res;
    }

    public String[] sortPeople2(String[] names, int[] heights) {

        Integer[] height = new Integer[heights.length];
        for (int i = 0; i < heights.length; i++) {
            height[i] = i;
        }
        Arrays.sort(height, (a, b) -> heights[b] - heights[a]);

        String[] res = new String[heights.length];
        for (int i = 0; i < height.length; i++) {
            res[i] = names[height[i]];
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/longest-string-chain/
     * 2023.4.27
     *
     * @param words 单词数组
     * @return 词链的 最长可能长度
     */
    public int longestStrChain(String[] words) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int res = 0;
        for (String word : words) {
            cnt.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                if (cnt.containsKey(prev)) {
                    cnt.put(word, Math.max(cnt.get(word), cnt.get(prev) + 1));
                }
            }
            res = Math.max(res, cnt.get(word));
        }
        return res;
    }
}
