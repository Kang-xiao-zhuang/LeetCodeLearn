package com.zhuang.daily.twotwo.september;

import java.util.*;

/**
 * description: Solution002
 * date: 2022/9/16 7:55
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {

    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        solution002.maxLengthBetweenEqualCharacters("abca");
    }


    /**
     * https://leetcode.cn/problems/rectangle-area-ii/
     * 9.16
     *
     * @param rectangles 二维矩形列表
     * @return 总面积
     */
    public int rectangleArea(int[][] rectangles) {
        final int MOD = 1000000007;
        int n = rectangles.length;
        Set<Integer> set = new HashSet<>();
        for (int[] rect : rectangles) {
            // 下边界
            set.add(rect[1]);
            // 上边界
            set.add(rect[3]);
        }
        List<Integer> hbound = new ArrayList<>(set);
        Collections.sort(hbound);
        int m = hbound.size();
        // 「思路与算法部分」的 length 数组并不需要显式地存储下来
        // length[i] 可以通过 hbound[i+1] - hbound[i] 得到
        int[] seg = new int[m - 1];

        List<int[]> sweep = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            // 左边界
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        long ans = 0;
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == sweep.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < m - 1; ++x) {
                    if (left <= hbound.get(x) && hbound.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }
            int cover = 0;
            for (int k = 0; k < m - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (hbound.get(k + 1) - hbound.get(k));
                }
            }
            ans += (long) cover * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }

    /**
     * https://leetcode.cn/problems/largest-substring-between-two-equal-characters/
     * 9.17
     *
     * @param s 字符串
     * @return 两个相同字符之间的最长子字符串的长度
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] firestIndex = new int[26];
        Arrays.fill(firestIndex, -1);
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            if (firestIndex[s.charAt(i) - 'a'] < 0) {
                firestIndex[s.charAt(i) - 'a'] = i;
            } else {
                maxLen = Math.max(maxLen, i - firestIndex[s.charAt(i) - 'a'] - 1);
            }
        }
        return maxLen;
    }
}
