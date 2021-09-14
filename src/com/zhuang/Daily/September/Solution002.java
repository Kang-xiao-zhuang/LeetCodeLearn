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


        //int[] chalk = {5, 1, 5};
        //chalkReplacer(chalk, 22);


        //String s = "(*))";
        //checkValidString(s);
        //checkValidString2(s);
        //int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        //numberOfBoomerangs(points);

        String s = "abpcplea";

        List<String> dictionary = Arrays.asList(new String[]{"ale", "apple", "monkey", "plea"});

        findLongestWord(s, dictionary);
        findLongestWord2(s, dictionary);
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

    /**
     * https://leetcode-cn.com/problems/valid-parenthesis-string/
     * 9.12
     * 栈方法
     *
     * @param s 字符串
     * @return 是否为有效字符串
     */
    public static boolean checkValidString(String s) {
        Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> startStack = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 遇到左括号
            if (c == '(') {
                leftStack.push(i);
                // 遇到*号
            } else if (c == '*') {
                startStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!startStack.isEmpty()) {
                    startStack.pop();
                } else {
                    System.out.println(false);
                    return false;
                }
            }
        }
        // 比较栈中的下标值
        while (!leftStack.isEmpty() && !startStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int startIndex = startStack.pop();
            if (leftIndex > startIndex) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(leftStack.isEmpty());
        return leftStack.isEmpty();
    }

    /**
     * 贪心法
     *
     * @param s 字符串
     * @return 是否为有效字符串
     */
    public static boolean checkValidString2(String s) {
        // 定义最小值和最大值变量
        int minCount = 0;
        int maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 如果遇到左括号，则最小值最大值分别加1
            if (c == '(') {
                minCount++;
                maxCount++;
                // 如果遇到右括号，则最大值和最小值分别减1
            } else if (c == ')') {
                minCount--;
                minCount = Math.max(minCount, 0);
                maxCount--;
                // 如果maxCount为负 说明不匹配 返回false
                if (maxCount < 0) {
                    System.out.println(false);
                    return false;
                }
            } else {
                // 遇到*
                minCount--;
                minCount = Math.max(minCount, 0);
                maxCount++;
            }
        }
        System.out.println(minCount == 0);
        return minCount == 0;
    }

    /**
     * https://leetcode-cn.com/problems/number-of-boomerangs/
     * 9.13
     *
     * @param points 互不相同的点
     * @return 平面上所有回旋镖的数量
     */
    public static int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                // 横坐标的差值
                int dx = points[i][0] - points[j][0];
                // 纵坐标的差值
                int dy = points[i][1] - points[j][1];
                int dis = dx * dx + dy * dy;
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (int val : map.values()) {
                res += val * (val - 1);
            }
        }
        System.out.println(res);
        return res;
    }


    /**
     * https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/
     * 9.14
     * 双指针
     *
     * @param s          字符串
     * @param dictionary 字典
     * @return 长度最长且字典序最小的字符串
     */
    public static String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String str : dictionary) {
            int i = 0, j = 0;
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                /*
                int compareTo(String anotherString)
                如果参数字符串等于此字符串，则返回值 0；
                如果此字符串小于字符串参数，则返回一个小于 0 的值；
                如果此字符串大于字符串参数，则返回一个大于 0 的值。
                 */
                if (str.length() > res.length() || (str.length() == res.length() && str.compareTo(res) < 0)) {
                    res = str;
                }
            }
        }
        System.out.println(res);
        return res;
    }


    /**
     * 排序＋双指针
     *
     * @param s          字符串
     * @param dictionary 字典
     * @return 长度最长且字典序最小的字符串
     */
    public static String findLongestWord2(String s, List<String> dictionary) {
        Collections.sort(dictionary, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });
        int n = s.length();
        for (String str : dictionary) {
            int i = 0, j = 0;
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                System.out.println(str);
                return str;
            }
        }
        return "";
    }
}
