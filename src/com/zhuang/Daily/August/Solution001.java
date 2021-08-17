package com.zhuang.Daily.August;

/**
 * @Classname Solution001
 * @Description 8月的每日一题
 * @Date 2021/8/17 11:23
 * @Author by Zhuang
 */
public class Solution001 {
    public static void main(String[] args) {
        String s = "PPALLP";
        checkRecord(s);
    }

    /**
     * https://leetcode-cn.com/problems/student-attendance-record-i/
     * 8.11
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
}
