package com.zhuang.Daily.August;

import java.util.Arrays;

/**
 * @Classname Solution002
 * @Description 2021.8.26-2021.8.31每日一题
 * @Date 2021/8/26 8:50
 * @Author by Zhuang
 */
public class Solution002 {
    public static void main(String[] args) {
        int[] people = {3, 2, 2, 1};
        numRescueBoats(people, 3);
    }

    /**
     * https://leetcode-cn.com/problems/boats-to-save-people/
     * 8.26
     *
     * @param people 人
     * @param limit  最大重量
     * @return 最小船树
     */
    public static int numRescueBoats(int[] people, int limit) {
        int res = 0;
        // 排序
        Arrays.sort(people);
        // 双指针
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            res++;
        }
        System.out.println(res);
        return res;
    }
}
