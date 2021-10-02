package com.zhuang.Daily.October;

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
}
