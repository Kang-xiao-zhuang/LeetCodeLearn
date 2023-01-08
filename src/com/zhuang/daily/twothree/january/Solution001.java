package com.zhuang.daily.twothree.january;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * description: Solution001
 * date: 2023/1/1 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.repeatedCharacter("abcdd");
    }

    /**
     * https://leetcode.cn/problems/first-letter-to-appear-twice/
     * 2023.1.1
     *
     * @param s 字符串
     * @return 找出并返回第一个出现 两次 的字母
     */
    public char repeatedCharacter(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char res = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2) {
                res = c;
                break;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/number-of-orders-in-the-backlog/
     * 2023.1.2
     *
     * @param orders 二维整数数组
     * @return 积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 109 + 7 取余的结果
     */
    public int getNumberOfBacklogOrders(int[][] orders) {
        final int MOD = 1000000007;
        PriorityQueue<int[]> buyOrders = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sellOrders = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] order : orders) {
            int price = order[0], amount = order[1], orderType = order[2];
            if (orderType == 0) {
                while (amount > 0 && !sellOrders.isEmpty() && sellOrders.peek()[0] <= price) {
                    int[] sellOrder = sellOrders.poll();
                    int sellAmount = Math.min(amount, sellOrder[1]);
                    amount -= sellAmount;
                    sellOrder[1] -= sellAmount;
                    if (sellOrder[1] > 0) {
                        sellOrders.offer(sellOrder);
                    }
                }
                if (amount > 0) {
                    buyOrders.offer(new int[]{price, amount});
                }
            } else {
                while (amount > 0 && !buyOrders.isEmpty() && buyOrders.peek()[0] >= price) {
                    int[] buyOrder = buyOrders.poll();
                    int buyAmount = Math.min(amount, buyOrder[1]);
                    amount -= buyAmount;
                    buyOrder[1] -= buyAmount;
                    if (buyOrder[1] > 0) {
                        buyOrders.offer(buyOrder);
                    }
                }
                if (amount > 0) {
                    sellOrders.offer(new int[]{price, amount});
                }
            }
        }
        int total = 0;
        for (PriorityQueue<int[]> pq : Arrays.asList(buyOrders, sellOrders)) {
            while (!pq.isEmpty()) {
                int[] order = pq.poll();
                total = (total + order[1]) % MOD;
            }
        }
        return total;
    }

    /**
     * https://leetcode.cn/problems/check-if-numbers-are-ascending-in-a-sentence/
     * 2023.1.3
     *
     * @param s 字符串
     * @return 需要检查 s 中的 全部 数字是否从左到右严格递增
     */
    public boolean areNumbersAscending(String s) {
        int pre = 0, pos = 0;
        while (pos < s.length()) {
            if (Character.isDigit(s.charAt(pos))) {
                int cur = 0;
                while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                    cur = cur * 10 + s.charAt(pos) - '0';
                    pos++;
                }
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
            } else {
                pos++;
            }
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/
     * 2023.1.4
     *
     * @param n      正整数
     * @param index  正整数
     * @param maxSum 正整数
     * @return 构造的数组中的 nums[index]
     */
    public int maxValue(int n, int index, int maxSum) {
        long l = 0, r = maxSum;
        //因为数组中所有的数均为正整数，所以减去n，剩余的表示可以填的数
        maxSum -= n;
        while (l < r) {
            //m表示index指向位置的高度
            long m = l + r + 1 >> 1;

            //计算当index的位置高度为m时，数组所有元素的和
            long count = m * m;
            //如果左边越界，就减去左边多的
            if (m > index) count -= (m - index - 1) * (m - index) / 2;
            //如果右边越界，就减去右边多的
            if (m > (n - index)) count -= (m - (n - index - 1) - 1) * (m - (n - index - 1)) / 2;

            //二分法判断
            if (count > maxSum) r = m - 1;
            else l = m;
        }
        return (int) l + 1;
    }

    /**
     * https://leetcode.cn/problems/count-integers-with-even-digit-sum/
     * 2023.1.6
     *
     * @param num 正整数
     * @return 返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目
     */
    public int countEven(int num) {
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int x = i, sum = 0;
            while (x != 0) {
                sum += x % 10;
                x /= 10;
            }
            if (sum % 2 == 0) {
                res++;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
     * 2023.1.7
     *
     * @param nums 整数数组
     * @param x    整数
     * @return 最小操作数
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();

        if (sum < x) {
            return -1;
        }

        int right = 0;
        int lsum = 0, rsum = sum;
        int ans = n + 1;

        for (int left = -1; left < n; ++left) {
            if (left != -1) {
                lsum += nums[left];
            }
            while (right < n && lsum + rsum > x) {
                rsum -= nums[right];
                ++right;
            }
            if (lsum + rsum == x) {
                ans = Math.min(ans, (left + 1) + (n - right));
            }
        }

        return ans > n ? -1 : ans;
    }

    /**
     * https://leetcode.cn/problems/counting-words-with-a-given-prefix/
     * 2023.1.8
     *
     * @param words 字符串数组
     * @param pref  字符串
     * @return words 中以 pref 作为 前缀 的字符串的数目
     */
    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                res++;
            }
        }
        return res;
    }
}
