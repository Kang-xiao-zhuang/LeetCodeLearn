package com.zhuang.Daily.November;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname Solution003
 * @Description 2021.11.16-2021.11.24每日一题
 * @Date 2021/11/16 20:26
 * @Author by dell
 */
public class Solution003 {
    public static void main(String[] args) {
        //int[][] rectangles = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        //isRectangleCover(rectangles);

        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        maxProduct(words);
    }

    /**
     * https://leetcode-cn.com/problems/perfect-rectangle/
     * 11.16
     *
     * @param rectangles 数组
     * @return 是否精确覆盖了某个矩形区域
     */
    public static boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        int[][] rs = new int[n * 2][4];
        for (int i = 0, idx = 0; i < n; i++) {
            int[] re = rectangles[i];
            rs[idx++] = new int[]{re[0], re[1], re[3], 1};
            rs[idx++] = new int[]{re[2], re[1], re[3], -1};
        }
        Arrays.sort(rs, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        n *= 2;
        // 分别存储相同的横坐标下「左边的线段」和「右边的线段」 (y1, y2)
        List<int[]> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int l = 0; l < n; ) {
            int r = l;
            l1.clear();
            l2.clear();
            // 找到横坐标相同部分
            while (r < n && rs[r][0] == rs[l][0]) {
                r++;
            }
            for (int i = l; i < r; i++) {
                int[] e = rs[i];
                int[] cur = new int[]{e[1], e[2]};
                List<int[]> list = e[3] == 1 ? l1 : l2;
                if (list.isEmpty()) {
                    list.add(cur);
                } else {
                    int[] prev = list.get(list.size() - 1);
                    if (cur[0] < prev[1]) {
                        return false; // 存在重叠
                    } else if (cur[0] == prev[1]) {
                        prev[1] = cur[1]; // 首尾相连
                    } else {
                        list.add(cur);
                    }
                }
            }
            if (l > 0 && r < n) {
                // 若不是完美矩形的边缘竖边，检查是否成对出现
                if (l1.size() != l2.size()) {
                    return false;
                }
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i)[0] == l2.get(i)[0] && l1.get(i)[1] == l2.get(i)[1]) {
                        continue;
                    }
                    return false;
                }
            } else {
                // 若是完美矩形的边缘竖边，检查是否形成完整一段
                if (l1.size() + l2.size() != 1) {
                    return false;
                }
            }
            l = r;
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
     * 11.17
     *
     * @param words 字符串数组
     * @return
     */
    public static int maxProduct(String[] words) {
        int length = words.length;
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        System.out.println(maxProd);
        return maxProd;
    }
}
