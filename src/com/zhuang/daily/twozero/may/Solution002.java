package com.zhuang.daily.twozero.may;

import java.util.HashMap;
import java.util.Map;

/**
 * description: Solution
 * date: 2022/10/28 8:45
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        solution002.findTheLongestSubstring("eleetminicoworoep");
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

    /**
     * https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/
     * 2020.5.20
     *
     * @param s 字符串
     * @return 返回满足以下条件的最长子字符串的长度
     */
    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        hash.put('a', 0);
        hash.put('e', 1);
        hash.put('i', 2);
        hash.put('o', 3);
        hash.put('u', 4);
        Map<Integer, Integer> map = new HashMap<>();
        int record = 0;
        map.put(record, -1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hash.containsKey(c)) {
                // 异或本身为 0，所以当重复出现偶数次，对应位变为 0，否则为 1
                int x = 1 << hash.get(c);
                record ^= x;
            }
            if (map.containsKey(record)) {
                ans = Math.max(ans, i - map.get(record));
            } else {
                map.put(record, i);
            }
        }
        return ans;
    }
}
