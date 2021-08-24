package com.zhuang.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Solution006
 * @Description 数组学习
 * @Date 2021/8/17 11:46
 * @Author by Zhuang
 */
public class Solution006 {

    public static void main(String[] args) {
        int x = 123;
        //   reverse(123);

        int[] nums = {2, 0, 2, 1, 1, 0};
        //    sortColors(nums);
        //   sortColors2(nums);

        String s = "abcabcbb";
        // lengthOfLongestSubstring(s);
        lengthOfLongestSubstring2(s);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-integer/
     *
     * @param x 数
     * @return int
     */
    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            res = res * 10 + digit;
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/sort-colors/
     * 单指针
     *
     * @param nums 数组
     */
    public static void sortColors(int[] nums) {
        int n = nums.length;
        // 定义一个指针
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }
        for (int i = p; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors2(int[] nums) {
        int n = nums.length;
        int p1 = 0, p2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                ++p2;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                if (p1 < p2) {
                    temp = nums[i];
                    nums[i] = nums[p2];
                    nums[p2] = temp;
                }
                ++p1;
                ++p2;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * Map+双指针法
     *
     * @param s 字符串
     * @return 最长子串的长度
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        // 用来存储字符串 判断是否重复
        HashMap<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < n; end++) {
            // 将字符串转成字符
            char letter = s.charAt(end);
            // 如果字符重复了，start指针变换位置
            if (map.containsKey(letter)) {
                start = Math.max(map.get(letter), start);
            }
            // 更换长度
            ans = Math.max(end - start + 1, ans);
            // 存入Map集合中
            map.put(s.charAt(end), end + 1);
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * Map的另一个解法
     *
     * @param s 字符串
     * @return 最长子串的长度
     */
    public static int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int ans = 0;
        for (int start = 0, end = 0; end < n; end++) {
            char right = s.charAt(end);
            map.put(right, map.getOrDefault(right, 0) + 1);
            // 如果大于1 就说明有重复
            while (map.get(right) > 1) {
                // 将重复的字符去掉个数
                char left = s.charAt(start);
                map.put(left, map.get(left) - 1);
                start++;
            }
            // 更换长度
            ans = Math.max(end - start + 1, ans);
        }
        System.out.println(ans);
        return ans;
    }
}
