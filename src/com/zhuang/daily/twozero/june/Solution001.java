package com.zhuang.daily.twozero.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: Solution001
 * date: 2022/11/3 16:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[] candies = {2, 3, 5, 1, 3};
        solution001.kidsWithCandies(candies, 3);
    }

    /**
     * https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies/
     * 2020.6.1
     *
     * @param candies      数组
     * @param extraCandies 整数
     * @return 额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> res = new ArrayList<>();
        int max = 0;
        /*  for (int candy : candies) {
            max = Math.max(candy, max);
        }*/
        int[] temp = Arrays.copyOf(candies, candies.length);
        Arrays.sort(temp);
        max = temp[temp.length - 1];
        for (int candy : candies) {
            res.add(candy + extraCandies >= max);
        }
        return res;
    }
}
