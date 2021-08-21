package com.zhuang.Daily.August;

import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * @Classname Solution001
 * @Description 2021.8的每日一题
 * @Date 2021/8/17 11:23
 * @Author by Zhuang
 */

public class Solution001 {

    public static void main(String[] args) {
        // String s = "PPALLP";
        //  checkRecord(s);
        //  checkRecord(2);

    //    String s = "leetcode";
     //   reverseVowels(s);

        String a = "abcdefg";
        reverseStr(a,2);
    }

    /**
     * https://leetcode-cn.com/problems/student-attendance-record-i/
     * 8.17
     *
     * @param s 字符串
     * @return 布尔
     */
    public static boolean checkRecord(String s) {
        int absent = 0, late = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                absent++;
                if (absent >= 2) {
                    return false;
                }
            }
            if (c == 'L') {
                late++;
                if (late >= 3) {
                    return false;
                }
            } else {
                late = 0;
            }
        }
        System.out.println(true);
        return true;
    }


    /**
     * https://leetcode-cn.com/problems/student-attendance-record-ii/
     * 8.18
     *
     * @param n 整数n
     * @return int
     */

    public static int checkRecord(int n) {
        int MOD = 1000000007;
        long[][] dp = new long[2][6];
        // 初始值
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][3] = 1;

        for (int i = 1; i < n; i++) {
            // 当前使用的下标
            int cur = i & 1;
            // 上一次使用的下标
            int last = (i - 1) & 1;
            dp[cur][0] = (dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][1] = dp[last][0];
            dp[cur][2] = dp[last][1];
            dp[cur][3] = (dp[last][3] + dp[last][4] + dp[last][5] + dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][4] = dp[last][3];
            dp[cur][5] = dp[last][4];
        }
        System.out.println((int) (LongStream.of(dp[(n - 1) & 1]).sum() % MOD));
        return (int) (LongStream.of(dp[(n - 1) & 1]).sum() % MOD);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
     * 8.19
     *
     * @param s 字符串
     * @return String
     */
    public static String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;

        char[] chars = s.toCharArray();
        while (left < right) {
            if (isVowel(chars[left]) && isVowel(chars[right])) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
            if (!isVowel(chars[left])) {
                left++;
            }
            if (!isVowel(chars[right])) {
                right--;
            }
        }
        System.out.println(Arrays.toString(chars));
        return new String(chars);
    }


    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }


    /**
     * https://leetcode-cn.com/problems/reverse-string-ii/
     * 8.20
     * @param s 字符串
     * @param k 前k个字符
     * @return 字符串
     */
    public static String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = s.length();
        //每次前进2k，但是只交换前k个字符
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        System.out.println(Arrays.toString(arr));
        return new String(arr);
    }

    /**
     * 辅助函数 反转字符串
     *
     * @param arr   数组
     * @param left  左指针
     * @param right 右指针
     */
    public static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            /*
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
             */
            arr[left]^=arr[right];
            arr[right]^=arr[left];
            arr[left]^=arr[right];
            left++;
            right--;
        }
    }

}
