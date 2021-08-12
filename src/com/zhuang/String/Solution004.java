package com.zhuang.String;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Classname Solution004
 * @Description 字符串学习
 * @Date 2021/8/11 13:06
 * @Author by Zhuang
 */
public class Solution004 {
    public static void main(String[] args) {
        //  int[] nums = {1, 4, 3, 2};
        //   arrayPairSum(nums);
        //   int[] nums = {1, 1, 0, 1, 1, 1};
        //   findMaxConsecutiveOnes(nums);

        //   int[] nums = {2, 3, 1, 2, 4, 3};
        // minSubArrayLen(7, nums);
        //     minSubArrayLen2(7, nums);
        //      minSubArrayLen3(7, nums);

        String s = "Let's take LeetCode contest";
        reverseWords(s);
        reverseWords2(s);
        reverseWords3(s);


    }

    /**
     * https://leetcode-cn.com/problems/array-partition-i/
     *
     * @param nums 数组
     * @return int
     */
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        System.out.println(sum);
        return sum;
    }


    /**
     * https://leetcode-cn.com/problems/max-consecutive-ones/
     *
     * @param nums 数组
     * @return itn
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cur = 0;
        for (int num : nums) {
            cur = num == 0 ? 0 : (cur + 1);
            max = Math.max(max, cur);
        }
        System.out.println(max);
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     *
     * @param target 目标值
     * @param nums   数组
     * @return int
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 双指针法
     *
     * @param target 目标值
     * @param nums   数组
     * @return int
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int low = 0;
        int high = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (high < nums.length) {
            sum += nums[high++];
            while (sum >= target) {
                min = Math.min(min, high - low);
                sum -= nums[low++];
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    public static int minSubArrayLen3(int target, int[] nums) {
        int low = 0;
        int high = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (high < nums.length) {
            target -= nums[high++];
            while (target <= 0) {
                min = Math.min(min, high - low);
                target += nums[low++];
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
     * 暴力法
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        String[] help = s.split(" ");
        StringBuilder SB = new StringBuilder();

        for (String str : help) {
            StringBuilder temp = new StringBuilder();
            temp.append(str).reverse();
            SB.append(temp).append(" ");
        }
        System.out.println(SB.toString().substring(0, SB.length() - 1));
        return SB.toString().substring(0, SB.length() - 1);
    }

    /**
     * 栈的方法
     *
     * @param s 字符串
     * @return Stirng
     */
    public static String reverseWords2(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(' ');
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String reverseWords3(String s) {
        String[] a = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = a[i].length() - 1; j >= 0; j--) {
                sb.append(a[i].charAt(j));
            }
            if (i != a.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}