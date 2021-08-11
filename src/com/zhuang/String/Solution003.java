package com.zhuang.String;

/**
 * @Classname Solution003
 * @Description 字符串学习
 * @Date 2021/8/9 8:54
 * @Author by Zhuang
 */
public class Solution003 {

    public static void main(String[] args) {
        //   String s = "cbbd";
        //    longestPalindrome(s);
        String s = "the sky is blue";
        String s2 = "  hello world  ";
        //    reverseWords(s2);

        String haystack = "hello";
        String needle = "ll";
        //  strStr(haystack, needle);
        //   strStr2(haystack, needle);
        //  strStr3(haystack, needle);
        strStr4(haystack, needle);
    }

    /**
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     *
     * @param s 字符串
     * @return String
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;

        char[] charArrary = s.toCharArray();

        // 枚举所有长度严格大于1的子串
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArrary, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        System.out.println(s.substring(begin, begin + maxLen));
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 判断是否为回文串
     *
     * @param charArray 数组
     * @param left      左指针
     * @param right     右指针
     * @return 布尔值
     */
    private static boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-words-in-a-string/
     *
     * @param s 字符串
     * @return String
     */
    public static String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverseString(sb, 0, sb.length() - 1);
        reverseEachWord(sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 移除空格的方法
     *
     * @param s 字符串
     * @return StringBuilder
     */
    public static StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') {
            start++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    /**
     * 反转指定区间内的字符串[start,end]
     *
     * @param sb
     * @param start
     * @param end
     */
    public static void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     * 反转各个单词
     *
     * @param sb StringBuilder
     */
    public static void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }


    /**
     * https://leetcode-cn.com/problems/implement-strstr/
     *
     * @param haystack 字符串
     * @param needle   字符串
     * @return int
     */
    public static int strStr(String haystack, String needle) {
        System.out.println(haystack.indexOf(needle));
        return haystack.indexOf(needle);
    }

    /**
     * 暴力匹配
     *
     * @param haystack 字符串
     * @param needle   字符串
     * @return int
     */
    public static int strStr2(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i);
                return i;
            }
        }
        System.out.println(-1);
        return -1;
    }

    /**
     * 转化为数组的方法
     *
     * @param haystack 字符串
     * @param needle   字符串
     * @return int
     */
    public static int strStr3(String haystack, String needle) {
        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();
        for (int i = 0; i <= ch1.length - ch2.length; i++) {
            int j;
            for (j = 0; j < ch2.length; j++) {
                if (ch1[i + j] != ch2[j]) {
                    break;
                }
            }
            if (j == ch2.length) {
                System.out.println(i);
                return i;
            }
        }
        System.out.println(-1);
        return -1;
    }


    /**
     * 双指针法
     *
     * @param haystack 字符串
     * @param needle   字符串
     * @return int
     */
    public static int strStr4(String haystack, String needle) {

        int left = 0;
        int right = 0;
        int index = 0;
        while (right < haystack.length() && index < needle.length()) {
            if (haystack.charAt(right) != needle.charAt(index)) {
                left++;
                right = left;
                index = 0;
            } else {
                right++;
                index++;
            }
        }
        return index == needle.length() ? left : -1;
    }

}
