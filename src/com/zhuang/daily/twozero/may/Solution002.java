package com.zhuang.daily.twozero.may;

import java.util.*;

/**
 * description: Solution
 * date: 2022/10/28 8:45
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        solution002.findTheLongestSubstring("eleetminicoworoep");

        solution002.decodeString2("3[a2[c]]");
    }

    /**
     * https://leetcode.cn/problems/maximum-product-subarray/
     * 2020.5.18
     *
     * @param nums 整数数组
     * @return 返回该子数组所对应的乘积
     */
    public int maxProduct(int[] nums) {
        // 全局最大值
        int max = Integer.MIN_VALUE;
        // 局部最大值
        int curmax = 1;
        // 局部最小值
        int curmin = 1;
        for (int num : nums) {
            // 小于0 最大直接变最小
            if (num < 0) {
                int temp = curmax;
                curmax = curmin;
                curmin = temp;
            }
            curmax = Math.max(curmax * num, num);
            curmin = Math.min(curmin * num, num);

            max = Math.max(max, curmax);
        }
        return max;
    }

    /**
     * https://leetcode.cn/problems/valid-palindrome-ii/
     * 2020.5.19
     *
     * @param s 字符串
     * @return 判断 s 是否能成为回文字符串
     */
    public boolean validPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/
     * 2020.5.20
     *
     * @param s 字符串
     * @return 返回满足以下条件的最长子字符串的长度
     */
    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        hash.put('a', 0);
        hash.put('e', 1);
        hash.put('i', 2);
        hash.put('o', 3);
        hash.put('u', 4);
        Map<Integer, Integer> map = new HashMap<>();
        int record = 0;
        map.put(record, -1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hash.containsKey(c)) {
                // 异或本身为 0，所以当重复出现偶数次，对应位变为 0，否则为 1
                int x = 1 << hash.get(c);
                record ^= x;
            }
            if (map.containsKey(record)) {
                ans = Math.max(ans, i - map.get(record));
            } else {
                map.put(record, i);
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/decode-string/
     * 2022.5.28
     *
     * @param s 给定一个经过编码的字符串
     * @return 返回它解码后的字符串
     */
    public String decodeString(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c != ']') {
                stack.push(c); // 把所有的字母push进去，除了]
            } else {
                //step 1: 取出[] 内的字符串
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                String sub = sb.toString(); //[ ]内的字符串
                stack.pop(); // 去除[

                //step 2: 获取倍数数字
                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                int count = Integer.parseInt(sb.toString()); //倍数

                //step 3: 根据倍数把字母再push回去
                while (count > 0) {
                    for (char ch : sub.toCharArray()) {
                        stack.push(ch);
                    }
                    count--;
                }
            }
        }

        //把栈里面所有的字母取出来
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    /**
     * 双栈解法：
     * 准备两个栈，一个存放数字，一个存放字符串
     * 遍历字符串分4中情况
     * 一、如果是数字 将字符转成整型数字 注意数字不一定是个位 有可能是十位，百位等 所以digit = digit*10 + ch - '0';
     * 二、如果是字符 直接将字符放在临时字符串中
     * 三、如果是"[" 将临时数字和临时字符串入栈
     * 四、如果是"]" 将数字和字符串出栈 此时临时字符串res = 出栈字符串 + 出栈数字*res
     */
    public String decodeString2(String s) {
        Deque<Integer> dequeDigit = new ArrayDeque<>();
        Deque<StringBuilder> dequeString = new ArrayDeque<>();
        int digit = 0;
        StringBuilder sb = new StringBuilder();
        // 遍历分为四种情况
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                digit = digit * 10 + ch - '0';
            } else if (ch == '[') {
                dequeDigit.push(digit);
                dequeString.push(sb);
                digit = 0;
                sb = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder temp = dequeString.poll();
                int count = dequeDigit.pop();
                for (int i = 0; i < count; i++) {
                    temp.append(sb.toString());
                }
                sb = temp;
            } else {
                // else if (Character.isLetter(ch))
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
