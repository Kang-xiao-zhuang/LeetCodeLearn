package com.zhuang.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Deacription 数组刷题学习
 * @Author Zhuang
 * @Date 2021/7/23 11:44
 * @Version 1.0
 **/
public class Solution002 {
    public static void main(String[] args) {
        /*
        int[] nums1={1,2,3,0,0,0};
        int[] nums2={2,5,6};
        merge(nums1,3,nums2,3);
        */
        // String s = "anagram", t = "nagaram";
        //   isAnagram(s,t);
        //  isAnagram2(s,t);
        /*
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        intersection(nums1, nums2);
         */

        int[] nums={3,2,4};
        twoSum(nums,6);
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     * <p>
     * https://leetcode-cn.com/problems/merge-sorted-array/
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m | p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词
     * <p>
     * https://leetcode-cn.com/problems/valid-anagram/
     *
     * @param s 字符串1
     * @param t 字符串2
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        System.out.println(Arrays.equals(ch1, ch2));
        return Arrays.equals(ch1, ch2);
    }

    /**
     * 解法2
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        int[] record = new int[26];
        for (char c : s.toCharArray()) {
            record[c - 'a'] += 1;
        }
        for (char c : t.toCharArray()) {
            record[c - 'a'] -= 1;
        }
        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * https://leetcode-cn.com/problems/intersection-of-two-arrays/
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 调用方法
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    /**
     * @param set1 集合1
     * @param set2 集合2
     * @return 数组
     */
    public static int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        HashSet<Integer> integerHashSet = new HashSet<Integer>();
        for (Integer item : set1) {
            if (set2.contains(item)) {
                integerHashSet.add(item);
            }
        }
        int[] intersection = new int[integerHashSet.size()];
        int index = 0;
        for (int num : integerHashSet) {
            intersection[index++] = num;
        }
        System.out.println(Arrays.toString(intersection));
        return intersection;
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * https://leetcode-cn.com/problems/two-sum/
     *
     * @param nums   数组
     * @param target 目标值
     * @return 数组
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] res=new int[]{i, j};
                    System.out.println(Arrays.toString(res));
                    return res;
                }
            }
        }
        return new int[0];
    }
}
