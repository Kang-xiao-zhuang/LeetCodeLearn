package com.zhuang.daily.twotwo.november;

/**
 * description: Solution001
 * date: 2022/11/1 15:08
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/
     * 2022.11.1
     *
     * @param word1 字符串数组
     * @param word2 字符串数组
     * @return 判断两个数组表示的字符串是否相同
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return join(word1).equals(join(word2));
    }

    public String join(String[] words) {
        StringBuilder ret = new StringBuilder();
        for (String s : words) {
            ret.append(s);
        }
        return ret.toString();
    }

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (String s : word1) {
            s1.append(s);
        }
        for (String s : word2) {
            s2.append(s);
        }
        return (s1.toString()).equals(s2.toString());
    }

    /**
     * https://leetcode.cn/problems/coordinate-with-maximum-network-quality/
     * 2022.11.2
     *
     * @param towers 数组
     * @param k      整数
     * @return 信号强度 最大的 整数 坐标点 (cx, cy)
     */
    public int[] bestCoordinate(int[][] towers, int k) {
        int[][] g = new int[110][110];
        int x = 0, y = 0, val = 0;
        for (int[] t : towers) {
            int a = t[0], b = t[1], q = t[2];
            for (int i = Math.max(0, a - k); i <= a + k; i++) {
                for (int j = Math.max(0, b - k); j <= b + k; j++) {
                    double d = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    if (d > k) continue;
                    g[i][j] += Math.floor(q / (1 + d));
                    if (g[i][j] > val) {
                        x = i;
                        y = j;
                        val = g[i][j];
                    } else if (g[i][j] == val && (i < x || (i == x && j < y))) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x, y};
    }
}
