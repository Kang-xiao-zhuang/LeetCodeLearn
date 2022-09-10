package com.zhuang.daily.twoone.December;

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

    Set<Long> set = new HashSet<>();
    int P = 131, OFFSET = 128;

    /**
     * https://leetcode-cn.com/problems/concatenated-words/
     * 12.28
     *
     * @param words 字符串数组
     * @return 集合
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        int n = words.length;
        for (int i = 0; i < n; i++) {
            long hash = 0;
            for (char c : words[i].toCharArray()) {
                hash = hash * P + (c - 'a') + OFFSET;
            }
            set.add(hash);
        }
        List<String> ans = new ArrayList<>();
        for (String s : words) {
            if (check(s)) {
                ans.add(s);
            }
        }
        return ans;
    }

    boolean check(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, -1);
        f[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (f[i] == -1) {
                continue;
            }
            long cur = 0;
            for (int j = i + 1; j <= n; j++) {
                cur = cur * P + (s.charAt(j - 1) - 'a') + OFFSET;
                if (set.contains(cur)) {
                    f[j] = Math.max(f[j], f[i] + 1);
                }
                if (f[n] > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/count-special-quadruplets/
     * 12.29
     *
     * @param nums 数组
     * @return 不同的四元组
     */
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int a = 0; a < n; ++a) {
            for (int b = a + 1; b < n; ++b) {
                for (int c = b + 1; c < n; ++c) {
                    for (int d = c + 1; d < n; ++d) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            ++ans;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/hand-of-straights/
     * 12.30
     *
     * @param hand      整数数组
     * @param groupSize 整数
     * @return boolean
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            q.add(i);
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            if (map.get(t) == 0) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int cnt = map.getOrDefault(t + i, 0);
                if (cnt == 0) {
                    return false;
                }
                map.put(t + i, cnt - 1);
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/perfect-number/
     * 12.31
     *
     * @param num 正整数
     * @return boolean
     */
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int ans = 1;
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                ans += i + num / i;
            }
        }
        return ans == num;
    }
}
