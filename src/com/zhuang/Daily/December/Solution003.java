package com.zhuang.Daily.December;

import java.util.*;

/**
 * @Classname Solution003
 * @Description 2021.12.15-2021.12.22每日一题
 * @Date 2021/12/15 10:26
 * @Author by dell
 */
public class Solution003 {
    public static void main(String[] args) {
        numWaterBottles(9, 3);
    }

    /**
     * https://leetcode-cn.com/problems/loud-and-rich/
     * 12.15
     *
     * @param richer 数组
     * @param quiet  安静值
     * @return 整数数组
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] in = new int[n];
        for (int[] r : richer) {
            int a = r[0], b = r[1];
            List<Integer> list = map.getOrDefault(a, new ArrayList<>());
            list.add(b);
            map.put(a, list);
            in[b]++;
        }
        Deque<Integer> d = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
            if (in[i] == 0) {
                d.addLast(i);
            }
        }
        while (!d.isEmpty()) {
            int t = d.pollFirst();
            for (int u : map.getOrDefault(t, new ArrayList<>())) {
                if (quiet[ans[t]] < quiet[ans[u]]) {
                    ans[u] = ans[t];
                }
                if (--in[u] == 0) {
                    d.addLast(u);
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-visible-points/
     * 12.16
     *
     * @param points   数组
     * @param angle    整数
     * @param location 位置
     * @return 看到的点的最大数目
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int x = location.get(0), y = location.get(1);
        List<Double> list = new ArrayList<>();
        int cnt = 0;
        double pi = Math.PI, t = angle * pi / 180;
        for (List<Integer> p : points) {
            int a = p.get(0), b = p.get(1);
            if (a == x && b == y && ++cnt >= 0) {
                continue;
            }
            list.add(Math.atan2(b - y, a - x));
        }
        Collections.sort(list);
        int n = list.size(), max = 0;
        for (int i = 0; i < n; i++) {
            list.add(list.get(i) + 2 * pi);
        }
        for (int i = 0, j = 0; j < 2 * n; j++) {
            while (i < j && list.get(j) - list.get(i) > t) {
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return cnt + max;
    }

    /**
     * https://leetcode-cn.com/problems/water-bottles/
     * 12.17
     *
     * @param numBottles  酒瓶
     * @param numExchange 空酒瓶
     * @return 总数
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int bottle = numBottles, ans = numBottles;
        while (bottle >= numExchange) {
            bottle -= numExchange;
            ++ans;
            ++bottle;
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/battleships-in-a-board/
     * 12.18
     *
     * @param board 矩阵
     * @return 战舰 的数量
     */
    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/find-the-town-judge/
     * 12.19
     *
     * @param n     数量
     * @param trust 数组
     * @return 编号
     */
    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n + 1], out = new int[n + 1];
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            in[b]++;
            out[a]++;
        }
        for (int i = 1; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode-cn.com/problems/heaters/
     * 12.20
     *
     * @param houses  房屋
     * @param heaters 供暖器
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            int i = binarySearch(heaters, house);
            int j = i + 1;
            int leftDistance = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            int rightDistance = j >= heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            int curDistance = Math.min(leftDistance, rightDistance);
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    /**
     * 二分法
     *
     * @param nums   数组
     * @param target 目标值
     * @return 二分后的值
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > target) {
            return -1;
        }
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
