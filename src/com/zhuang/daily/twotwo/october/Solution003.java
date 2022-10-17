package com.zhuang.daily.twotwo.october;

import java.util.HashMap;

/**
 * description: Solution003
 * date: 2022/10/17 8:57
 * author: Zhuang
 * version: 1.0
 */
public class Solution003 {
    public static void main(String[] args) {
        Solution003 solution003 = new Solution003();
        int[] fruits = {0, 1, 2, 2};
        solution003.totalFruit(fruits);
    }

    /**
     * https://leetcode.cn/problems/fruit-into-baskets/
     * 2022.10.17
     *
     * @param fruits 整数数组
     * @return 你可以收集的水果的最大数目
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left])-1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        System.out.println(ans);
        return ans;
    }
}
