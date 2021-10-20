package com.zhuang.Daily.October;

import java.util.*;

/**
 * @Classname Solution003
 * @Description 2021.10.14-2021.10.21每日一题
 * @Date 2021/10/14 19:43
 * @Author by dell
 */

public class Solution003 {
    public static void main(String[] args) {
        //countAndSay(4);
        //countAndSay(4);

        //addOperators("123", 6);
        //findComplement(5);

    }

    /**
     * https://leetcode-cn.com/problems/count-and-say/
     * 循环法
     * 10.15
     *
     * @param n 正整数
     * @return 字符串
     */
    public static String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        for (int i = 1; i < n; i++) {
            // 记录当前的字符串
            StringBuilder s = new StringBuilder();
            // 记录每个数字的开始索引
            int start = 0;
            for (int j = 1; j < sb.length(); j++) {
                // 当数字发生改变时执行
                if (sb.charAt(j) != sb.charAt(start)) {
                    s.append(j - start).append(sb.charAt(start));
                    start = j;
                }
            }
            // 字符串最后一个数字
            s.append(sb.length() - start).append(sb.charAt(start));
            sb = s;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 递归法
     *
     * @param n 正整数
     * @return 字符串
     */
    public static String countAndSay2(int n) {
        // 递归终止条件
        if (n == 1) {
            return "1";
        }
        // 获取到上一层的字符串
        String s = countAndSay2(n - 1);
        StringBuilder result = new StringBuilder();
        // 记录每个数字的开始索引
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            // 当数字发生改变时执行
            if (s.charAt(i) != s.charAt(start)) {
                result.append(i - start).append(s.charAt(start));
                start = i;
            }
        }
        // 字符串最后一个数字
        result.append(s.length() - start).append(s.charAt(start));
        return result.toString();
    }

    /**
     * https://leetcode-cn.com/problems/expression-add-operators/
     * 10.16
     *
     * @param num    数组
     * @param target 目标整数
     * @return 目标值表达式
     */
    public static List<String> addOperators(String num, int target) {
        /**
         * 这个方法有问题，不能通过所有测试用例，以后解决，做次记录 2021年10月16日08:44:43
         */
        List<String> res = new ArrayList<>();
        backtrack(num, target, res, "", 0, 0, 0);
        System.out.println(res.toString());
        return res;
    }

    /**
     * @param num    数组
     * @param target 目标值
     * @param res    集合
     * @param path   路径
     * @param start  开始索引
     * @param value  当前的值
     * @param pre    之前值
     */
    private static void backtrack(String num, int target, List<String> res, String path, int start, long value, long pre) {
        if (start == num.length()) {
            if (target == value) {
                res.add(path);
            }
            return;
        }
        for (int i = start; i < num.length(); i++) {
            // 数字不能以0开头
            if (num.charAt(start) == '0' && i > start) {
                break;
            }
            long cur = Long.parseLong(num.substring(start, i + 1));
            // 选取第一个数不加符号
            if (start == 0) {
                backtrack(num, target, res, path + cur, i + 1, cur, cur);
            } else {
                // 加当前值
                backtrack(num, target, res, path + "+" + cur, i + 1, value + cur, cur);
                // 减当前值
                backtrack(num, target, res, path + "-" + cur, i + 1, value - cur, cur);
                /*
                乘法不同，当前表达式的值就是先减去之前的值，然后加上，当前值和边上的操作数相乘   value-prev+prev*cur
                传入参数 prev*cur
                 */
                backtrack(num, target, res, path + "*" + cur, i + 1, value - pre + pre * cur, pre * cur);
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
     * 10.17
     * 中序遍历
     *
     * @param root 根节点
     * @param k    倒数第k个
     * @return 第 k 个最小元素
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    /**
     * https://leetcode-cn.com/problems/number-complement/
     * 10.18
     * 位运算
     *
     * @param num 正整数
     * @return 补数
     */
    public static int findComplement(int num) {
        int highbit = 0;
        for (int i = 1; i <= 30; i++) {
            if (num >= 1 << i) {
                highbit = i;
            } else {
                break;
            }
        }
        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        System.out.println(mask);
        System.out.println(num ^ mask);
        return num ^ mask;
    }

    /**
     * WordDictionary类
     * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
     * 10.19
     */
    static class WordDictionary {
        Map<Integer, Set<String>> map;

        public WordDictionary() {
            map = new HashMap<>();
        }

        public void addWord(String word) {
            Set<String> set = map.getOrDefault(word.length(), new HashSet<>());
            set.add(word);
            map.put(word.length(), set);
        }

        public boolean search(String word) {
            Set<String> set = map.get(word.length());
            if (set == null) {
                return false;
            }
            if (set.contains(word)) {
                return true;
            }
            ArrayList<Integer> list = new ArrayList<>();
            // 找到所有不为.的字母下标
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != '.') {
                    list.add(i);
                }
            }
            if (list.size() == 0) {
                return true;
            }
            for (String s : set) {
                boolean flag = true;
                for (Integer index : list) {
                    // 说明字符串不匹配
                    if (s.charAt(index) != word.charAt(index)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/
     * 10.20
     *
     * @param nums 数组
     * @return
     */
    public int minMoves(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i] - nums[0];
        }
        System.out.println(res);
        return res;
    }
}
