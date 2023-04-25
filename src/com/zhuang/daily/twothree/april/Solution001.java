package com.zhuang.daily.twothree.april;

import java.util.Arrays;
import java.util.HashMap;

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
}
