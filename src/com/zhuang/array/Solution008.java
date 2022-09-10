package com.zhuang.array;

import java.util.*;

/**
 * @Description 数组学习
 * @Author Zhuang
 * @Date 2021/8/4 10:26
 * @Version 1.0
 **/
public class Solution008 {
    public static void main(String[] args) {
        //    int[] nums={2,3,4};
        //     twoSum(nums,6);

        int[] nums = {1, 2, 3, 1, 2, 3};
        //    containsNearbyDuplicate(nums, 2);
        // containsNearbyDuplicate2(nums, 2);
        //   containsNearbyDuplicate3(nums, 2);

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        //    nextGreaterElement(nums1, nums2);
        nextGreaterElement2(nums1, nums2);
    }

    /**
     * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     *
     * @param numbers 数组
     * @param target  目标值
     * @return 数组
     */
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int rigth = numbers.length - 1;
        while (left <= rigth) {
            if (numbers[left] + numbers[rigth] == target) {
                System.out.println(Arrays.toString(new int[]{left + 1, rigth + 1}));
                return new int[]{left + 1, rigth + 1};
            } else if (numbers[left] + numbers[rigth] > target) {
                rigth--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * https://leetcode-cn.com/problems/contains-duplicate-ii/
     *
     * @param nums 数组
     * @param k    目标值
     * @return 布尔值
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                System.out.println(true);
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                // 移除元素
                set.remove(nums[i - k]);
            }
        }
        System.out.println(false);
        return false;
    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer tmp = map.get(nums[i]);
                if (i - tmp <= k) {
                    System.out.println(true);
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        System.out.println(false);
        return false;
    }

    public static boolean containsNearbyDuplicate3(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (i + j >= nums.length) {
                    break;
                }
                if (nums[i] == nums[i + j]) {
                    System.out.println(true);
                    return true;
                }
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/next-greater-element-i/
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 数组
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            for (int j = 0; j < nums1.length; j++) {
                if (nums2[i] == nums1[j]) {
                    res[j] = -1;
                    for (int k = i + 1; k < nums2.length; k++) {
                        if (nums2[k] > nums1[j]) {
                            res[j] = nums2[k];
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 先处理 nums2，把对应关系存入哈希表
        for (int i = 0; i < len2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                map.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }

        // 遍历 nums1 得到结果集
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
}
