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
}
