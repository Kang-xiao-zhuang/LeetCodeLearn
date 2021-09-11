package com.zhuang.Daily.September;

import java.util.*;

/**
 * @Classname Solution002
 * @Description 2021.9.7-2021.9.14每日一题
 * @Date 2021/9/7 8:45
 * @Author by Zhuang
 */
public class Solution002 {
    public static void main(String[] args) {
        //String s = "RLLLLRRRLR";
        //balancedStringSplit(s);
        //balancedStringSplit2(s);


        int[] chalk = {5, 1, 5};
        chalkReplacer(chalk, 22);
    }

    /**
     * https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/
     * 9.7
     * 计数法
     *
     * @param s 字符串
     * @return 最大数量
     */
    public static int balancedStringSplit(String s) {
        int res = 0, diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                diff++;
            } else {
                diff--;
            }
            if (diff == 0) {
                res++;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 栈法
     *
     * @param s 字符串
     * @return 最大数量
     */
    public static int balancedStringSplit2(String s) {
        int res = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // stack.peek() 返回栈顶的对象但不移除
            if (!stack.isEmpty() && stack.peek() != s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
            if (stack.isEmpty()) {
                res++;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/ipo/
     * 9.8
     *
     * @param k       最多k个
     * @param w       资本为w
     * @param profits 利润
     * @param capital 资本
     * @return 获得的最大资本
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }

        return w;
    }

    /**
     * https://leetcode-cn.com/problems/text-justification/
     * 9.9
     *
     * @param words    单词数组
     * @param maxWidth 最大宽度
     * @return List集合
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ) {
            // list 装载当前行的所有 word
            list.clear();
            list.add(words[i]);
            int cur = words[i++].length();
            while (i < n && cur + 1 + words[i].length() <= maxWidth) {
                cur += 1 + words[i].length();
                list.add(words[i++]);
            }

            // 当前行为最后一行，特殊处理为左对齐
            if (i == n) {
                StringBuilder sb = new StringBuilder(list.get(0));
                for (int k = 1; k < list.size(); k++) {
                    sb.append(" ").append(list.get(k));
                }
                while (sb.length() < maxWidth) sb.append(" ");
                ans.add(sb.toString());
                break;
            }

            // 如果当前行只有一个 word，特殊处理为左对齐
            int cnt = list.size();
            if (cnt == 1) {
                String str = list.get(0);
                while (str.length() != maxWidth) str += " ";
                ans.add(str);
                continue;
            }

            /**
             * 其余为一般情况
             * wordWidth : 当前行单词总长度;
             * spaceWidth : 当前行空格总长度;
             * spaceItem : 往下取整后的单位空格长度
             */
            int wordWidth = cur - (cnt - 1);
            int spaceWidth = maxWidth - wordWidth;
            int spaceItemWidth = spaceWidth / (cnt - 1);
            String spaceItem = "";
            for (int k = 0; k < spaceItemWidth; k++) spaceItem += " ";
            StringBuilder sb = new StringBuilder();
            for (int k = 0, sum = 0; k < cnt; k++) {
                String item = list.get(k);
                sb.append(item);
                if (k == cnt - 1) break;
                sb.append(spaceItem);
                sum += spaceItemWidth;
                // 剩余的间隙数量（可填入空格的次数）
                int remain = cnt - k - 1 - 1;
                // 剩余间隙数量 * 最小单位空格长度 + 当前空格长度 < 单词总长度，则在当前间隙多补充一个空格
                if (remain * spaceItemWidth + sum < spaceWidth) {
                    sb.append(" ");
                    sum++;
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/
     * 9.10
     *
     * @param chalk 粉笔数组
     * @param k     整数k
     * @return 补充粉笔的学生编号
     */
    public static int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        // 注意溢出
        long sum = 0;
        for (int num : chalk) {
            // 计算数组中的粉笔总和
            sum += num;
        }
        // 对k取模
        k %= sum;
        int res = -1;
        for (int i = 0; i < len; i++) {
            if (chalk[i] > k) {
                res = i;
                break;
            }
            k -= chalk[i];
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
     * 9.11
     *
     * @param n 正整数 n
     * @return 不包含 连续的1 的个数
     */
    public int findIntegers(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                n -= val;
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }
        return res;
    }
}
