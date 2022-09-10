package com.zhuang.daily.twoone.December;

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

    /**
     * https://leetcode-cn.com/problems/day-of-the-year/
     * 12.21
     *
     * @param date 字符串
     * @return 该日期是当年的第几天
     */
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] amount = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            ++amount[1];
        }

        int ans = 0;
        for (int i = 0; i < month - 1; ++i) {
            ans += amount[i];
        }
        return ans + day;
    }

    /**
     * https://leetcode-cn.com/problems/repeated-string-match/
     * 12.22
     *
     * @param a 字符串a
     * @param b 字符串b
     * @return 最小次数
     */
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) {
            sb.append(a);
        }
        sb.append(a);
        int index = sb.indexOf(b);
        if (index == -1) {
            return -1;
        }
        return index + b.length() > a.length() * ans ? ans + 1 : ans;
    }

    /**
     * https://leetcode-cn.com/problems/longest-duplicate-substring/
     * 12.23
     *
     * @param s 字符串
     * @return 重复子串
     */
    public String longestDupSubstring(String s) {
        Random random = new Random();
        // 生成两个进制
        int a1 = random.nextInt(75) + 26;
        int a2 = random.nextInt(75) + 26;
        // 生成两个模
        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int n = s.length();
        // 先对所有字符进行编码
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = s.charAt(i) - 'a';
        }
        // 二分查找的范围是[1, n-1]
        int l = 1, r = n - 1;
        int length = 0, start = -1;
        while (l <= r) {
            int m = l + (r - l + 1) / 2;
            int idx = check(arr, m, a1, a2, mod1, mod2);
            if (idx != -1) {
                // 有重复子串，移动左边界
                l = m + 1;
                length = m;
                start = idx;
            } else {
                // 无重复子串，移动右边界
                r = m - 1;
            }
        }
        return start != -1 ? s.substring(start, start + length) : "";
    }

    public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2) {
        int n = arr.length;
        long aL1 = pow(a1, m, mod1);
        long aL2 = pow(a2, m, mod2);
        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; ++i) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }
        }
        // 存储一个编码组合是否出现过
        Set<Long> seen = new HashSet<Long>();
        seen.add(h1 * mod2 + h2);
        for (int start = 1; start <= n - m; ++start) {
            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + m - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + m - 1]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }

            long num = h1 * mod2 + h2;
            // 如果重复，则返回重复串的起点
            if (!seen.add(num)) {
                return start;
            }
        }
        // 没有重复，则返回-1
        return -1;
    }

    public long pow(int a, int m, int mod) {
        long ans = 1;
        long contribute = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0) {
                    ans += mod;
                }
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0) {
                contribute += mod;
            }
            m /= 2;
        }
        return ans;
    }
}
