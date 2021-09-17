package com.zhuang.LeetCodeHot100;

import java.util.HashMap;

/**
 * @Classname Solution01
 * @Description LeetCode 热题 HOT 1-5
 * @Date 2021/9/15 17:19
 * @Author by Zhuang
 */
public class Solution01 {
    public static void main(String[] args) {
        //String s = "abcabcbb";
        //lengthOfLongestSubstring(s);

        String s = "babad";
        longestPalindrome(s);
    }

    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 第1题
     * 暴力法
     *
     * @param nums   整数数组
     * @param target 整数目标值
     * @return 数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 第1题
     * 哈希表
     *
     * @param nums   整数数组
     * @param target 整数目标值
     * @return 数组下标
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * https://leetcode-cn.com/problems/add-two-numbers/
     * 第2题
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //虚拟节点 也叫哨兵节点
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        int sum = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            sum = a + b + sum;
            // 满10进1
            temp.next = new ListNode(sum % 10);
            sum /= 10;
            // 节点后移
            temp = temp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (sum > 0) {
            temp.next = new ListNode(sum);
        }
        return dummy.next;
    }

    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 第3题
     *
     * @param s 字符串
     * @return 最长子串的长度
     */
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int ans = 0;
        for (int start = 0, end = 0; end < n; end++) {
            // 获取到字符
            char right = s.charAt(end);
            map.put(right, map.getOrDefault(right, 0) + 1);
            // 如果map中大于1，说明有重复
            while (map.get(right) > 1) {
                char left = s.charAt(start);
                // 去掉重复的字符个数减1
                map.put(left, map.get(left) - 1);
                start++;
            }
            // 更新字符串的长度
            ans = Math.max(end - start + 1, ans);
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     * 第5题
     *
     * @param s 字符串
     * @return 最长的回文子串
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;

        char[] charArrary = s.toCharArray();

        // 枚举所有长度严格大于1的子串
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArrary, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        System.out.println(s.substring(begin, begin + maxLen));
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 判断是否为回文串
     *
     * @param charArray 数组
     * @param left      左指针
     * @param right     右指针
     * @return 布尔值
     */
    private static boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
