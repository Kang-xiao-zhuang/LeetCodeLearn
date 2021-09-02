package com.zhuang.Daily.September;

/**
 * @Classname Solution001
 * @Description 2021.9.1-2021.9.6每日一题
 * @Date 2021/9/1 10:23
 * @Author by Zhuang
 */
public class Solution001 {
    public static void main(String[] args) {
        String version1 = "1.01";
        String version2 = "1.001";
        compareVersion(version1, version2);
        compareVersion2(version1, version2);
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
}
