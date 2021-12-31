package com.zhuang.Daily.twoone.November;

import java.util.*;

/**
 * @Classname Solution001
 * @Description 2021.11.1-2021.11.7每日一题
 * @Date 2021/11/1 20:26
 * @Author by dell
 */

public class Solution001 {
    public static void main(String[] args) {
        //int[] candyType = {1, 1, 2, 2, 3, 3};
        //distributeCandies(candyType);
        int[] arr = {1, 2, 3, 4};
        //longestSubsequence(arr, 1);


        int[][] ops = {{2, 2}, {3, 3}};
        maxCount(3,3,ops);
    }

    /**
     * https://leetcode-cn.com/problems/distribute-candies/
     * 11.1
     *
     * @param candyType 糖果种类
     * @return 吃到糖的最多种类
     */
    public static int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<Integer>();
        for (int candy : candyType) {
            set.add(candy);
        }
        System.out.println(Math.min(set.size(), candyType.length / 2));
        return Math.min(set.size(), candyType.length / 2);
    }

    /**
     * 排序
     *
     * @param candyType 糖果种类
     * @return 吃到糖的最多种类
     */
    public int distributeCandies2(int[] candyType) {
        Arrays.sort(candyType);
        int count = 1;
        for (int i = 1; i < candyType.length && count < candyType.length / 2; i++) {
            if (candyType[i] > candyType[i - 1]) {
                count++;
            }
        }
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     * 11.2 =
     *
     * @param node 节点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * https://leetcode-cn.com/problems/trapping-rain-water-ii/
     * 11.3
     *
     * @param heightMap 矩阵
     * @return 最多体积的雨水
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        int maxHeight = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxHeight = Math.max(maxHeight, heightMap[i][j]);
            }
        }
        int[][] water = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                water[i][j] = maxHeight;
            }
        }
        Queue<int[]> qu = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (water[i][j] > heightMap[i][j]) {
                        water[i][j] = heightMap[i][j];
                        qu.offer(new int[]{i, j});
                    }
                }
            }
        }
        while (!qu.isEmpty()) {
            int[] curr = qu.poll();
            int x = curr[0];
            int y = curr[1];
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i], ny = y + dirs[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (water[x][y] < water[nx][ny] && water[nx][ny] > heightMap[nx][ny]) {
                    water[nx][ny] = Math.max(water[x][y], heightMap[nx][ny]);
                    qu.offer(new int[]{nx, ny});
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += water[i][j] - heightMap[i][j];
            }
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/valid-perfect-square/
     * 11.4
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare(int num) {
        long r = num;
        while (r * r > num) {
            r = (r + num / r) / 2;
        }
        System.out.println(r * r == num);
        return r * r == num;
    }

    /**
     * 二分查找
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare2(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int temp = num / mid;
            if (mid * mid == num) {
                return true;
            } else if (temp < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * 二分查找
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare3(int num) {
        if (num < 2) {
            return true;
        }
        long left = 2;
        long right = num / 2;
        long guess;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            guess = mid * mid;
            if (guess == num) {
                System.out.println(true);
                return true;
            }
            if (guess < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/
     * 11.5
     *
     * @param arr        整数数组
     * @param difference 整数
     * @return 最长等差子序列长度
     */
    public static int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        HashMap<Integer, Integer> dp = new HashMap<>();
        for (int i : arr) {
            dp.put(i, dp.getOrDefault(i - difference, 0) + 1);
            ans = Math.max(ans, dp.get(i));
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/missing-number/
     * 11.6
     *
     * @param nums 数组
     * @return 丢失的数字
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
    }

    /**
     * https://leetcode-cn.com/problems/range-addition-ii/
     * 11.7
     *
     * @param m   正整数
     * @param n   正整数
     * @param ops m*n 的矩阵
     * @return 矩阵中含有最大整数的元素个数
     */
    public static int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        System.out.println(m * n);
        return m * n;
    }
}
