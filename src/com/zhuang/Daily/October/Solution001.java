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
}
