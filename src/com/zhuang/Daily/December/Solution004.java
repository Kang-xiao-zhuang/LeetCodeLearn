package com.zhuang.Daily.December;

import java.util.*;

/**
 * @Classname Solution004
 * @Description 2021.12.24-2021.12.31每日一题
 * @Date 2021/12/24 22:16
 * @Author by dell
 */

public class Solution004 {
    public static void main(String[] args) {
        findOcurrences("alice is a good girl she is a good student", "a", "good");
    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/
     * 12.24
     *
     * @param apples 数组
     * @param days   天数
     * @return 最大数目
     */
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;
        while (time < n || !q.isEmpty()) {
            if (time < n && apples[time] > 0) {
                q.add(new int[]{time + days[time] - 1, apples[time]});
            }
            while (!q.isEmpty() && q.peek()[0] < time) {
                q.poll();
            }
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if (--cur[1] > 0 && cur[0] > time) {
                    q.add(cur);
                }
                ans++;
            }
            time++;
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/even-odd-tree/
     * 12.25
     *
     * @param root 根节点
     * @return 判断是否奇偶树
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int value = node.val;
                if (level % 2 == value % 2) {
                    return false;
                }
                if ((level % 2 == 0 && value <= prev) || (level % 2 == 1 && value >= prev)) {
                    return false;
                }
                prev = value;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/occurrences-after-bigram/
     * 12.26
     *
     * @param text   字符串
     * @param first  字符串
     * @param second 字符串
     * @return 数组
     */
    public static String[] findOcurrences(String text, String first, String second) {
        String[] ss = text.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i + 2 < ss.length; i++) {
            if (ss[i].equals(first) && ss[i + 1].equals(second)) {
                list.add(ss[i + 2]);
            }
        }
        System.out.println(Arrays.toString(list.toArray(new String[list.size()])));
        return list.toArray(new String[list.size()]);
    }

    /**
     * https://leetcode-cn.com/problems/friends-of-appropriate-ages/
     * 12.27
     *
     * @param ages age数组
     * @return 该社交媒体网站上产生的好友请求总数
     */
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < ages.length && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }
}
