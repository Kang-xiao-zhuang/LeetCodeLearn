package com.zhuang.daily.twotwo.november;

import java.util.*;

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
        solution001.customSortString("cba", "abcd");
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

    /**
     * https://leetcode.cn/problems/ambiguous-coordinates/
     * 2022.11.7
     *
     * @param s 二维坐标
     * @return 所有可能的原始字符串到一个列表中
     */
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length() - 2;
        List<String> res = new ArrayList<>();
        s = s.substring(1, s.length() - 1);
        for (int l = 1; l < n; ++l) {
            List<String> lt = getPos(s.substring(0, l));
            if (lt.isEmpty()) {
                continue;
            }
            List<String> rt = getPos(s.substring(l));
            if (rt.isEmpty()) {
                continue;
            }
            for (String i : lt) {
                for (String j : rt) {
                    res.add("(" + i + ", " + j + ")");
                }
            }
        }
        return res;
    }

    public List<String> getPos(String s) {
        List<String> pos = new ArrayList<>();
        if (s.charAt(0) != '0' || "0".equals(s)) {
            pos.add(s);
        }
        for (int p = 1; p < s.length(); ++p) {
            if ((p != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0') {
                continue;
            }
            pos.add(s.substring(0, p) + "." + s.substring(p));
        }
        return pos;
    }

    /**
     * https://leetcode.cn/problems/count-the-number-of-consistent-strings/
     * 2022.11.8
     *
     * @param allowed 字符串
     * @param words   字符串数组
     * @return words 数组中 一致字符串 的数目
     */
    public int countConsistentStrings(String allowed, String[] words) {
        HashMap<Character, Integer> map = new HashMap<>();
        int len = words.length;
        for (int i = 0; i < allowed.length(); i++) {
            map.put(allowed.charAt(i), 1);
        }
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (!map.containsKey(word.charAt(i))) {
                    len--;
                    break;
                }
            }
        }
        return len;
    }

    /**
     * https://leetcode.cn/problems/determine-if-string-halves-are-alike/
     * 2022.11.11
     *
     * @param s 字符串
     * @return 两个字符串 相似 的前提是它们都含有相同数目的元音
     */
    public boolean halvesAreAlike(String s) {
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2);
        ArrayList<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('I');
        list.add('O');
        list.add('U');
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < a.length(); i++) {
            if (list.contains(a.charAt(i))) {
                sum1++;
            }
        }
        for (int i = 0; i < a.length(); i++) {
            if (list.contains(b.charAt(i))) {
                sum2++;
            }
        }
        return sum1 == sum2;
    }

    public boolean halvesAreAlike2(String s) {
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2);
        String h = "aeiouAEIOU";
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < a.length(); i++) {
            if (h.indexOf(a.charAt(i)) >= 0) {
                sum1++;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (h.indexOf(b.charAt(i)) >= 0) {
                sum2++;
            }
        }
        return sum1 == sum2;
    }

    /**
     * https://leetcode.cn/problems/custom-sort-string/
     * 2022.11.13
     *
     * @param order 字符串
     * @param s     字符串
     * @return 满足这个性质的 s 的任意排列
     */
    public String customSortString(String order, String s) {
        int[] val = new int[26];
        for (int i = 0; i < order.length(); i++) {
            val[order.charAt(i) - 'a'] = i + 1;
        }
        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr, (c0, c1) -> val[c0 - 'a'] - val[c1 - 'a']);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * https://leetcode.cn/problems/maximum-units-on-a-truck/
     * 2022.11.15
     *
     * @param boxTypes  二维数组
     * @param truckSize 卡车上可以装载 箱子 的 最大数量
     * @return 卡车可以装载 单元 的 最大 总数
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int res = 0;
        for (int[] boxType : boxTypes) {
            int numberOfBoxes = boxType[0];
            int numberOfUnitsPerBox = boxType[1];
            if (numberOfBoxes < truckSize) {
                res += numberOfBoxes * numberOfUnitsPerBox;
                truckSize -= numberOfBoxes;
            } else {
                res += truckSize * numberOfUnitsPerBox;
                break;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/global-and-local-inversions/
     * 2022.11.16
     * @param nums 整数数组
     * @return 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时
     */
    public boolean isIdealPermutation(int[] nums) {
        int max = nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < max) return false;
            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }

}
