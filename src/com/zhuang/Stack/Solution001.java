package com.zhuang.Stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Description 栈的学习
 * @Author Zhuang
 * @Date 2021/7/30 15:12
 * @Version 1.0
 **/
public class Solution001 {
    public static void main(String[] args) {
        String s = "()[]{}";
        String s2 = "([)]";
        //  isValid(s);
        //  isValid(s2);
        isValid2(s);
    }

    /**
     * https://leetcode-cn.com/problems/valid-parentheses/
     *
     * @param s 字符串
     * @return 布尔
     */
    public static boolean isValid(String s) {
        // 判断字符串的长度
        if (s.length() % 2 == 1) {
            return false;
        }
        // 定义一个栈
        Stack<Character> stack = new Stack<>();
        // 定义一个Map
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        // 遍历字符串转为字符
        for (Character c : s.toCharArray()) {
            // 如果遇到右括号且栈不为空
            if (!stack.isEmpty() && map.containsKey(c)) {
                // 如果栈的顶部等于map中的key对应的value
                if (stack.peek().equals(map.get(c))) {
                    // 相同则抵消，出栈
                    stack.pop();
                } else {
                    System.out.println(false);
                    return false;
                }
            } else {
                // 遇到左括号，直接入栈
                stack.push(c);
            }
        }
        System.out.println(stack.isEmpty());
        // 看左右是否抵消完
        return stack.isEmpty();
    }

    /**
     * if else 更快
     *
     * @param s 字符串
     * @return 布尔值
     */
    public static boolean isValid2(String s) {
        // 定义一个栈
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(stack.isEmpty());
        return stack.isEmpty();
    }
}
