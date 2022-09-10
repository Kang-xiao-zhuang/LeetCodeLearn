package com.zhuang.daily.twoone.october;


import java.util.*;

/**
 * @Classname Solution001
 * @Description 2021.10.1-2021.10.6每日一题
 * @Date 2021/10/1 10:23
 * @Author by Zhuang
 */
public class Solution001 {
    public static void main(String[] args) {
        //toHex(26);
        //fractionToDecimal(1, 2);
        //licenseKeyFormatting("5F3Z-2E9W", 4);

        int[] nums = {3, 2, 1};
        //thirdMax(nums);
        //thirdMax2(nums);
        //thirdMax3(nums);
    }

    /**
     * https://leetcode-cn.com/problems/destination-city/
     * 10.1
     *
     * @param paths 旅行路线
     * @return 终点站
     */
    public static String destCity(List<List<String>> paths) {
        HashMap<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        String res = paths.get(0).get(1);
        while (map.containsKey(res)) {
            res = map.get(res);
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/
     * 10.1
     *
     * @param num 数字
     * @return 转换为16进制
     */
    public static String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; i--) {
            int value = (num >> (4 * i) & 0xf);
            if (sb.length() > 0 || value > 0) {
                char c = value < 10 ? (char) ('0' + value) : (char) ('a' + value - 10);
                sb.append(c);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
     * 10.3
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 字符串形式返回小数
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        long n = numerator, d = denominator;
        if (n % d == 0) {
            return String.valueOf(n / d);
        }
        StringBuffer sb = new StringBuffer();
        // 负数情况
        if (n < 0 ^ d < 0) {
            sb.append('-');
        }
        // 取绝对值 整数部分
        n = Math.abs(n);
        d = Math.abs(d);
        long integerPart = n / d;
        sb.append(integerPart);
        sb.append('.');
        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        HashMap<Long, Integer> reminderIndexMap = new HashMap<>();
        long reminder = n % d;
        int index = 0;
        while (reminder != 0 && !reminderIndexMap.containsKey(reminder)) {
            reminderIndexMap.put(reminder, index);
            reminder *= 10;
            fractionPart.append(reminder / d);
            reminder %= d;
            index++;
        }
        // 有循环节点
        if (reminder != 0) {
            int insertIndex = reminderIndexMap.get(reminder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * https://leetcode-cn.com/problems/license-key-formatting/
     * 10.4
     *
     * @param s 字符串
     * @param k 数字k
     * @return 密钥格式化
     */
    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1, cnt = 0; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                cnt++;
                sb.append(Character.toUpperCase(s.charAt(i)));
                // 每k个部分分割
                if (cnt % k == 0) {
                    sb.append("-");
                }
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }

    //10.5笔记里有

    /**
     * https://leetcode-cn.com/problems/third-maximum-number/
     * 10.5
     * 有序集合
     *
     * @param nums 数组
     * @return 数组中第三大的数
     */
    public static int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        System.out.println(set.size() == 3 ? set.first() : set.last());
        return set.size() == 3 ? set.first() : set.last();
    }

    /**
     * 排序
     *
     * @param nums 数组
     * @return 数组中第三大的数
     */
    public static int thirdMax2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        System.out.println(list.size() < 3 ? list.get(list.size() - 1) : list.get(list.size() - 3));
        return list.size() < 3 ? list.get(list.size() - 1) : list.get(list.size() - 3);
    }

    /**
     * 一次遍历
     *
     * @param nums 数组
     * @return 数组中第三大的数
     */
    public static int thirdMax3(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }
}
