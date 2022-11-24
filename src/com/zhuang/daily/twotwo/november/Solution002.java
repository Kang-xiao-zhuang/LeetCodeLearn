package com.zhuang.daily.twotwo.november;

import java.util.ArrayList;
import java.util.List;

/**
 * description: Solution002
 * date: 2022/11/17 8:43
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        solution002.numMatchingSubseq(s, words);

        solution002.champagneTower(100000009, 33, 17);
        int[] nums = {2, 1, 4, 3};
        solution002.numSubarrayBoundedMax(nums, 2, 3);
    }

    /**
     * https://leetcode.cn/problems/number-of-matching-subsequences/
     * 2022.11.17
     *
     * @param s     字符串
     * @param words 字符串数组
     * @return 返回  words[i] 中是s的子序列的单词个数
     */
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        int res = words.length;
        for (String w : words) {
            if (w.length() > s.length()) {
                --res;
                continue;
            }
            int p = -1;
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                if (pos[c - 'a'].isEmpty() || pos[c - 'a'].get(pos[c - 'a'].size() - 1) <= p) {
                    --res;
                    break;
                }
                p = binarySearch(pos[c - 'a'], p);
            }
        }
        return res;
    }

    public int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.get(left);
    }

    /**
     * https://leetcode.cn/problems/find-the-highest-altitude/
     * 2022.11.19
     *
     * @param gain 整数数组
     * @return 返回最高点的海拔
     */
    public int largestAltitude(int[] gain) {
        int sum = 0;
        int max = 0;
        for (int i : gain) {
            sum += i;
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * https://leetcode.cn/problems/champagne-tower/
     * 2022.11.20
     *
     * @param poured      倾倒香槟总杯数
     * @param query_row   行数
     * @param query_glass 杯子的位置数
     * @return 第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（ i 和 j 都从0开始
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] row = {poured};
        for (int i = 1; i <= query_row; i++) {
            double[] nextRow = new double[i + 1];
            for (int j = 0; j < i; j++) {
                double volume = row[j];
                if (volume > 1) {
                    nextRow[j] += (volume - 1) / 2;
                    nextRow[j + 1] += (volume - 1) / 2;
                }
            }
            row = nextRow;
        }
        return Math.min(1, row[query_glass]);
    }

    /**
     * https://leetcode.cn/problems/maximum-number-of-balls-in-a-box/
     * 2022.11.23
     *
     * @param lowLimit  整数
     * @param highLimit 整数
     * @return 返回其中任一盒子的小球数量
     */
    public int countBalls(int lowLimit, int highLimit) {
        int result = 0;
        int[] resultMap = new int[46];
        for (int i = lowLimit; i <= highLimit; i++) {
            int num = i;
            int index = 0;
            while (num > 0) {
                index += num % 10;
                num = num / 10;
            }
            resultMap[index] += 1;
        }
        for (int r : resultMap) result = Math.max(result, r);
        return result;
    }

    /**
     * https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum/
     * 2022.11.24
     *
     * @param nums  整数数组
     * @param left  整数
     * @param right 整数
     * @return 返回满足条件的子数组的个数
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res = 0;
        int last2 = -1;
        int last1 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                last1 = i;
            } else if (nums[i] > right) {
                last2 = i;
                last1 = -1;
            }
            if (last1 != -1) {
                res += last1 - last2;
            }
        }
        return res;
    }
}
