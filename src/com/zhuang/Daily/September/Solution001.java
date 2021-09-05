package com.zhuang.Daily.September;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Classname Solution001
 * @Description 2021.9.1-2021.9.6每日一题
 * @Date 2021/9/1 10:23
 * @Author by Zhuang
 */
public class Solution001 {
    public static void main(String[] args) {
        //String version1 = "1.01";
        //String version2 = "1.001";
        //compareVersion(version1, version2);
        //compareVersion2(version1, version2);

        //int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        //smallestK(arr, 4);
        //smallestK2(arr, 4);

        //fib(5);
        fib2(5);
    }

    /**
     * https://leetcode-cn.com/problems/compare-version-numbers/
     * 字符串分割
     * 9.1
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return int
     */
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; i++) {
            int a = 0, b = 0;
            if (i < v1.length) {
                a = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                b = Integer.parseInt(v2[i]);
            }
            if (a > b) {
                System.out.println(1);
                return 1;
            }
            if (a < b) {
                System.out.println(-1);
                return -1;
            }
        }
        System.out.println(0);
        return 0;
    }

    /**
     * 双指针法
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return int
     */
    public static int compareVersion2(String version1, String version2) {
        // 接受版本的长度
        int n = version1.length(), m = version2.length();
        for (int i = 0, j = 0; i < m || j < n; i++, j++) {
            int a = 0, b = 0;
            // 转化为数字
            while (i < n && version1.charAt(i) != '.') {
                a = 10 * a + (version1.charAt(i++) - '0');
            }
            // 转换为数字
            while (j < m && version2.charAt(j) != '.') {
                b = 10 * b + (version2.charAt(j++) - '0');
            }
            // 判断版本号是否一致
            if (a != b) {
                System.out.println(a > b ? 1 : -1);
                return a > b ? 1 : -1;
            }
        }
        System.out.println(0);
        return 0;
    }

    /**
     * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     * 9.2
     *
     * @param head 头节点
     * @param k    倒数第k个节点
     * @return 节点对象
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (k == 0) {
                head = head.next;
            } else {
                k--;
            }
        }
        return head;
    }

    /**
     * 顺序查找
     *
     * @param head 头节点
     * @param k    倒数第k个节点
     * @return 节点对象
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        int n = 0;
        ListNode node = null;

        for (node = head; node != null; node = node.next) {
            n++;
        }
        for (node = head; n > k; n--) {
            node = node.next;
        }
        return node;
    }

    /**
     * 快慢指针
     *
     * @param head 头节点
     * @param k    倒数第k个节点
     * @return 节点对象
     */
    public ListNode getKthFromEnd3(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * https://leetcode-cn.com/problems/smallest-k-lcci/
     * 9.3
     * API法
     *
     * @param arr 数组
     * @param k   最小的k个数
     * @return 数组
     */
    public static int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = Arrays.copyOfRange(arr, 0, k);
        System.out.println(Arrays.toString(res));
        return res;
    }

    /**
     * 优先队列
     *
     * @param arr 数组
     * @param k   最小的k个数
     * @return 数组
     */
    public static int[] smallestK2(int[] arr, int k) {
        // 利用优先队列的特性，先输出小的值
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.offer(i);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
     * 9.4
     * 动态规划1
     *
     * @param n 正整数
     * @return 斐波那契数列第n项
     */
    public static int fib(int n) {
        int mod = (int) 1e9 + 7;
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            // 滚动数组
            int c = a + b;
            c %= mod;
            a = b;
            b = c;
        }
        System.out.println(b);
        return b;
    }

    /**
     * 动态规划2
     *
     * @param n 正整数
     * @return 斐波那契数列第n项
     */
    public static int fib2(int n) {
        int a = 0, b = 1, sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        System.out.println(a);
        return a;
    }

    // 9.5笔记里有
}
