package com.zhuang.Daily.September;

/**
 * @Classname Solution001
 * @Description 2021.9.1-2021.9.6每日一题
 * @Date 2021/9/1 10:23
 * @Author by Zhuang
 */
public class Solution001 {
    public static void main(String[] args) {
        String version1 = "1.01";
        String version2 = "1.001";
        compareVersion(version1, version2);
        compareVersion2(version1, version2);
    }

    /**
     * https://leetcode-cn.com/problems/compare-version-numbers/
     * 字符串分割
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return int
     */
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; i++) {
            int a = 0, b = 0;
            if (i < v1.length) {
                a = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                b = Integer.parseInt(v2[i]);
            }
            if (a > b) {
                System.out.println(1);
                return 1;
            }
            if (a < b) {
                System.out.println(-1);
                return -1;
            }
        }
        System.out.println(0);
        return 0;
    }

    /**
     * 双指针法
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return int
     */
    public static int compareVersion2(String version1, String version2) {
        // 接受版本的长度
        int n = version1.length(), m = version2.length();
        for (int i = 0, j = 0; i < m || j < n; i++, j++) {
            int a = 0, b = 0;
            // 转化为数字
            while (i < n && version1.charAt(i) != '.') {
                a = 10 * a + (version1.charAt(i++) - '0');
            }
            // 转换为数字
            while (j < m && version2.charAt(j) != '.') {
                b = 10 * b + (version2.charAt(j++) - '0');
            }
            // 判断版本号是否一致
            if (a != b) {
                System.out.println(a > b ? 1 : -1);
                return a > b ? 1 : -1;
            }
        }
        System.out.println(0);
        return 0;
    }
}
