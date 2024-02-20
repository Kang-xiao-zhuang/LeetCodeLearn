package com.zhuang.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution001 {

    /**
     * https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
     * LCR 164. 破解闯关密码
     *
     * @param password int[]
     * @return String
     */
    public String crackPassword(int[] password) {
        Queue<String> queue = new PriorityQueue<>((o1, o2) -> {
            //字典序列小的放在堆顶
            return (o1 + o2).compareTo(o2 + o1);
        });
        for (int num : password) {
            queue.add("" + num);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof
     *
     * @param places int[]
     * @return boolean
     */
    public boolean checkDynasty(int[] places) {
        int unknown = 0;
        Arrays.sort(places); // 数组排序
        for (int i = 0; i < 4; i++) {
            if (places[i] == 0) unknown++; // 统计未知朝代数量
            else if (places[i] == places[i + 1]) return false; // 若有重复，提前返回 false
        }
        return places[4] - places[unknown] < 5; // 最大编号朝代 - 最小编号朝代 < 5 则连续
    }

    /**
     * https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
     *
     * @param stock int[]
     * @param cnt   int
     * @return int[]
     */
    public int[] inventoryManagement(int[] stock, int cnt) {
        int[] vec = new int[cnt];
        Arrays.sort(stock);
        System.arraycopy(stock, 0, vec, 0, cnt);
        return vec;
    }
}
