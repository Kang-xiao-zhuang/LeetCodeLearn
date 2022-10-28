package com.zhuang.daily.twozero.may;

/**
 * description: Solution
 * date: 2022/10/28 8:45
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/maximum-product-subarray/
     * 2020.5.18
     *
     * @param nums 整数数组
     * @return 返回该子数组所对应的乘积
     */
    public int maxProduct(int[] nums) {
        // 全局最大值
        int max = Integer.MIN_VALUE;
        // 局部最大值
        int curmax = 1;
        // 局部最小值
        int curmin = 1;
        for (int num : nums) {
            // 小于0 最大直接变最小
            if (num < 0) {
                int temp = curmax;
                curmax = curmin;
                curmin = temp;
            }
            curmax = Math.max(curmax * num, num);
            curmin = Math.min(curmin * num, num);

            max = Math.max(max, curmax);
        }
        return max;
    }

    /**
     * https://leetcode.cn/problems/valid-palindrome-ii/
     * 2020.5.19
     *
     * @param s 字符串
     * @return 判断 s 是否能成为回文字符串
     */
    public boolean validPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
