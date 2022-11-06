package com.zhuang.daily.twotwo.november;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * description: Solution001
 * date: 2022/11/1 15:08
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.parseBoolExpr("|(&(t,f,t),!(t))");
    }

    /**
     * https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/
     * 2022.11.1
     *
     * @param word1 字符串数组
     * @param word2 字符串数组
     * @return 判断两个数组表示的字符串是否相同
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return join(word1).equals(join(word2));
    }

    public String join(String[] words) {
        StringBuilder ret = new StringBuilder();
        for (String s : words) {
            ret.append(s);
        }
        return ret.toString();
    }

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (String s : word1) {
            s1.append(s);
        }
        for (String s : word2) {
            s2.append(s);
        }
        return (s1.toString()).equals(s2.toString());
    }

    /**
     * https://leetcode.cn/problems/coordinate-with-maximum-network-quality/
     * 2022.11.2
     *
     * @param towers 数组
     * @param k      整数
     * @return 信号强度 最大的 整数 坐标点 (cx, cy)
     */
    public int[] bestCoordinate(int[][] towers, int k) {
        int[][] g = new int[110][110];
        int x = 0, y = 0, val = 0;
        for (int[] t : towers) {
            int a = t[0], b = t[1], q = t[2];
            for (int i = Math.max(0, a - k); i <= a + k; i++) {
                for (int j = Math.max(0, b - k); j <= b + k; j++) {
                    double d = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    if (d > k) continue;
                    g[i][j] += Math.floor(q / (1 + d));
                    if (g[i][j] > val) {
                        x = i;
                        y = j;
                        val = g[i][j];
                    } else if (g[i][j] == val && (i < x || (i == x && j < y))) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x, y};
    }

    /**
     * https://leetcode.cn/problems/maximum-repeating-substring/
     * 2022/11/3
     *
     * @param sequence 字符串
     * @param word     子字符串
     * @return 返回 最大重复值 k
     */
    public int maxRepeating(String sequence, String word) {
        int count = 0;
        String tmp = word;
        while (sequence.contains(word)) {
            word += tmp;
            count++;
        }
        return count;
    }

    /**
     * https://leetcode.cn/problems/reach-a-number/
     * 2022.11.4
     *
     * @param target 终点
     * @return 整数
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            k++;
            target -= k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }

    /**
     * https://leetcode.cn/problems/parsing-a-boolean-expression/
     * 2022.11.5
     *
     * @param expression 布尔表达式
     * @return 该式的运算结果
     */
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == ',') {
                continue;
            } else if (c != ')') {
                stack.push(c);
            } else {
                // 遇到')'开始执行下列代码
                int t = 0, f = 0;
                while (stack.peek() != '(') {
                    char val = stack.pop();
                    if (val == 't') {
                        t++;
                    } else {
                        f++;
                    }
                }
                // 弹出'('
                stack.pop();
                char op = stack.pop();
                switch (op) {
                    case '!':
                        stack.push(f == 1 ? 't' : 'f');
                        break;
                    case '&':
                        stack.push(f == 0 ? 't' : 'f');
                        break;
                    case '|':
                        stack.push(t > 0 ? 't' : 'f');
                        break;
                    default:
                }
            }
        }
        return stack.pop() == 't';
    }

    /**
     * https://leetcode.cn/problems/goal-parser-interpretation/
     * 2022.11.6
     *
     * @param command 解释字符串
     * @return Goal 解析器 对 command 的解释结果
     */
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                sb.append("G");
            } else if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    sb.append("o");
                } else {
                    sb.append("al");
                }
            }
        }
        return sb.toString();
    }


    public String interpret2(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            switch (command.charAt(i)) {
                case 'G':
                    sb.append("G");
                    break;
                case '(':
                    if (command.charAt(i + 1) == ')') {
                        sb.append("o");
                    } else {
                        i += 2;
                        sb.append("al");
                    }
                    break;
                default:
            }
        }
        return sb.toString();
    }
}
