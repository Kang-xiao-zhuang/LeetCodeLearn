package com.zhuang.Daily.November;

import java.util.*;

/**
 * @Classname Solution001
 * @Description 2021.11.1-2021.11.7每日一题
 * @Date 2021/11/1 20:26
 * @Author by dell
 */

public class Solution001 {
    public static void main(String[] args) {
        int[] candyType = {1, 1, 2, 2, 3, 3};
        distributeCandies(candyType);
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
}
