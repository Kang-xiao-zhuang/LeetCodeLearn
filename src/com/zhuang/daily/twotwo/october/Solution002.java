package com.zhuang.daily.twotwo.october;

import java.util.ArrayList;
import java.util.List;

/**
 * description: Solution002
 * date: 2022/10/10 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/
     * 10.10
     *
     * @param nums1 整型数组
     * @param nums2 整型数组
     * @return 使 nums1 和 nums2 严格递增 所需操作的最小次数
     */
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int a = 0, b = 1;
        for (int i = 1; i < n; i++) {
            int at = a, bt = b;
            a = b = n;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                a = Math.min(a, at);
                b = Math.min(b, bt + 1);
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                a = Math.min(a, bt);
                b = Math.min(b, at + 1);
            }
        }
        return Math.min(a, b);
    }


    /**
     * https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
     * 2022.10.11
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 布尔
     */
    public boolean areAlmostEqual(String s1, String s2) {
        char[] c1 = new char[2];
        char[] c2 = new char[2];
        int k = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (k > 1) return false;
                c1[k] = s1.charAt(i);
                c2[k] = s2.charAt(i);
                k++;
            }
        }
        return c1[0] == c2[1] && c1[1] == c2[0];
    }

    public boolean areAlmostEqual2(String s1, String s2) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff.size() >= 2) {
                    return false;
                }
                diff.add(i);
            }
        }
        if (diff.isEmpty()) {
            return true;
        }
        if (diff.size() != 2) {
            return false;
        }
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1)) && s1.charAt(diff.get(1)) == s2.charAt(diff.get(0));
    }

    /**
     * https://leetcode.cn/problems/trapping-rain-water/
     * 2020.4.3
     *
     * @param height 高度
     * @return 接多少雨水
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/edit-distance/
     * 2020.4.6
     *
     * @param word1 字符串
     * @param word2 字符串
     * @return int
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * https://leetcode.cn/problems/rotate-matrix-lcci/
     * 2020.4.7
     *
     * @param matrix 二维数组
     */
    public void rotate(int[][] matrix) {
        //获取数组长度
        int n = matrix.length;
        //定义新的数组
        int[][] matrix_new = new int[n][n];
        //两次循环
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //翻转后的元素的规律是 假如元素是第二列第一个，翻转后会是第二行最后一个！
                /*
                1 2 3    7 4 1
                4 5 6 -> 8 5 2
                7 8 9    9 6 3

             以 2 为例
             翻转前 位置 [0][1]
             翻转后 位置 [1][2]
                */
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        //遍历完成之后，再将matrix_new中的结果复制到原数组中即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }
}
