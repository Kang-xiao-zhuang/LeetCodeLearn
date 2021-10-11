package com.zhuang.Daily.October;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.zhuang.Daily.October.Solution002.toEnglish;

/**
 * @Classname Solution002
 * @Description 2021.10.7-2021.10.14每日一题
 * @Date 2021/10/7 6:56
 * @Author by dell
 */

public class Solution002 {
    public static void main(String[] args) {
        //countSegments("Hello, my name is John");
        //String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        //findRepeatedDnaSequences(s);

        //arrangeCoins(5);
        //arrangeCoins2(5);
        //arrangeCoins3(5);

        //numberToWords(123);
        //numberToWords(123);
    }

    /**
     * https://leetcode-cn.com/problems/number-of-segments-in-a-string/
     * 10.7
     *
     * @param s 字符串
     * @return 统计字符串中的单词个数
     */
    public static int countSegments(String s) {
        int count = 0;
        for (String s1 : s.split(" ")) {
            if (!s1.equals("")) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    public static int countSegments2(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/repeated-dna-sequences/
     * 10.8
     *
     * @param s 字符串
     * @return 所有目标子串
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) {
                res.add(str);
            }
        }
        System.out.println(res.toString());
        return res;
    }

    //10.9笔记里有


    /**
     * https://leetcode-cn.com/problems/arranging-coins/
     * 10.10
     * 迭代法
     *
     * @param n 硬币个数
     * @return 可形成完整阶梯行的总行数
     */
    public static int arrangeCoins(int n) {
        int i = 1;
        while (n >= i) {
            n -= i;
            i++;
        }
        System.out.println(i - 1);
        return i - 1;
    }

    /**
     * 二分法
     *
     * @param n 硬币个数
     * @return 可形成完整阶梯行的总行数
     */
    public static int arrangeCoins2(int n) {
        long left = 1, right = n;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            if (mid * (mid + 1) / 2 <= n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return (int) left;
    }

    /**
     * 数学求根公式
     *
     * @param n 硬币个数
     * @return 可形成完整阶梯行的总行数
     */
    public static int arrangeCoins3(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }


    static String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    static String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String[] thousands = {"", "Thousand", "Million", "Billion"};

    /**
     * https://leetcode-cn.com/problems/integer-to-english-words/
     * 10.11
     * 迭代法
     *
     * @param num 非负整数
     * @return 对应的英文表示
     */
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                sb.append(toEnglish(curNum)).append(thousands[i]).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim();
    }

    /**
     * @param num 非负整数
     * @return 指定字符串
     */
    public static String toEnglish(int num) {
        StringBuffer cur = new StringBuffer();
        int hundred = num / 100;
        num %= 100;
        if (hundred != 0) {
            cur.append(singles[hundred]).append(" Hundred ");
        }
        int ten = num / 10;
        if (ten >= 2) {
            cur.append(tens[ten]).append(" ");
            num %= 10;
        }
        if (num > 0 && num < 10) {
            cur.append(singles[num]).append(" ");
        } else if (num >= 10) {
            cur.append(teens[num - 10]).append(" ");
        }
        return cur.toString();
    }

    /**
     * 递归法
     *
     * @param num 非负整数
     * @return 对应的英文表示
     */
    public static String numberToWords2(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuffer cur = new StringBuffer();
                recursion(cur, curNum);
                cur.append(thousands[i]).append(" ");
                sb.append(cur);
            }
        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim();
    }

    /**
     * 递归的方法
     *
     * @param cur StringBuffer
     * @param num 非负整数
     */
    private static void recursion(StringBuffer cur, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            cur.append(singles[num]).append(" ");
        } else if (num < 20) {
            cur.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            cur.append(tens[num / 10]).append(" ");
            recursion(cur, num % 10);
        } else {
            cur.append(singles[num / 100]).append(" Hundred ");
            recursion(cur, num % 100);
        }
    }
}
