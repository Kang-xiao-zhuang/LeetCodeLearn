package com.zhuang.string;

/**
 * @Description 字符串学习
 * @Author Zhuang
 * @Date 2021/8/2 10:05
 * @Version 1.0
 **/
public class Solution002 {
    public static void main(String[] args) {
        //String[] strs = {"flower", "flow", "flight"};
        //String[] strs2 = {"dog", "racecar", "car"};
        //  longestCommonPrefix(strs);
        //  longestCommonPrefix(strs2);

        //convertToTitle(28);
        //titleToNumber("ZY");
    }

    /**
     * https://leetcode-cn.com/problems/longest-common-prefix/
     *
     * @param strs 字符串
     * @return 字符串
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        // 把数组中第一个字符串的值赋给s
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(s)) {
                s = s.substring(0, s.length() - 1);
                i--;
            }
        }
        System.out.println(s);
        return s;
    }

    /**
     * https://leetcode-cn.com/problems/excel-sheet-column-title/
     *
     * @param columnNumber 整数
     * @return 对应列名称
     */
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * https://leetcode-cn.com/problems/excel-sheet-column-number/
     *
     * @param columnTitle 字符串
     * @return 列名称对应的序列号
     */
    public static int titleToNumber(String columnTitle) {
        char[] cs = columnTitle.toCharArray();
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = ans * 26 + (cs[i] - 'A' + 1);
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/ugly-number/
     *
     * @param n 整数
     * @return 是否为丑数
     */
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
