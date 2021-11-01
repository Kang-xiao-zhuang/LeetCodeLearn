package com.zhuang.Daily.November;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Classname Solution001
 * @Description 2021.11.1-2021.11.7每日一题
 * @Date 2021/11/1 20:26
 * @Author by dell
 */

public class Solution001 {
    public static void main(String[] args) {
        int[] candyType = {1, 1, 2, 2, 3, 3};
        distributeCandies(candyType);
    }

    /**
     * https://leetcode-cn.com/problems/distribute-candies/
     * 11.1
     *
     * @param candyType 糖果种类
     * @return 吃到糖的最多种类
     */
    public static int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<Integer>();
        for (int candy : candyType) {
            set.add(candy);
        }
        System.out.println(Math.min(set.size(), candyType.length / 2));
        return Math.min(set.size(), candyType.length / 2);
    }

    /**
     * 排序
     *
     * @param candyType 糖果种类
     * @return 吃到糖的最多种类
     */
    public int distributeCandies2(int[] candyType) {
        Arrays.sort(candyType);
        int count = 1;
        for (int i = 1; i < candyType.length && count < candyType.length / 2; i++) {
            if (candyType[i] > candyType[i - 1]) {
                count++;
            }
        }
        return count;
    }
}
