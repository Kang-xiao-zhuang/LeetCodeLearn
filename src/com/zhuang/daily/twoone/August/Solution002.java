package com.zhuang.daily.twoone.August;

import java.util.Arrays;

/**
 * @Classname Solution002
 * @Description 2021.8.26-2021.8.31每日一题
 * @Date 2021/8/26 8:50
 * @Author by Zhuang
 */
public class Solution002 {
    public static void main(String[] args) {
        //int[] people = {3, 2, 2, 1};
        //numRescueBoats(people, 3);

        //int[] nums = {1, 2, 3, 4};
        //runningSum(nums);

        //int[] arr = {10, 11, 12};
        //sumOddLengthSubarrays(arr);
        //sumOddLengthSubarrays2(arr);
        //sumOddLengthSubarrays3(arr);

        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        //corpFlightBookings(bookings, 5);
        corpFlightBookings2(bookings, 5);

    }

    /**
     * https://leetcode-cn.com/problems/boats-to-save-people/
     * 8.26
     *
     * @param people 人
     * @param limit  最大重量
     * @return 最小船树
     */
    public static int numRescueBoats(int[] people, int limit) {
        int res = 0;
        // 排序
        Arrays.sort(people);
        // 双指针
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            res++;
        }
        System.out.println(res);
        return res;
    }

    // 8.27 笔记里有

    /**
     * https://leetcode-cn.com/problems/running-sum-of-1d-array/
     * 8.28
     *
     * @param nums 数组
     * @return 动态和
     */
    public static int[] runningSum(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

    /**
     * https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays/
     * 暴力法
     * 8.29
     *
     * @param arr 正整数数组
     * @return 所有可能的奇数长度子数组的和
     */
    public static int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        // 定义两个指针 start ,length
        for (int start = 0; start < n; start++) {
            // 每次跳2个长度
            for (int length = 1; start + length <= n; length += 2) {
                // 奇数组的长度范围确定下
                int end = start + length - 1;
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                }
            }
        }
        System.out.println(sum);
        return sum;
    }

    /**
     * 暴力法优化(内存小点)
     *
     * @param arr 正整数数组
     * @return 所有可能的奇数长度子数组的和
     */
    public static int sumOddLengthSubarrays2(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j += 2) {
                int temp = i;
                while (temp <= j) {
                    sum += arr[temp];
                    temp++;
                }
            }
        }
        System.out.println(sum);
        return sum;
    }

    /**
     * 前缀和解法(未懂)
     *
     * @param arr 正整数数组
     * @return 所有可能的奇数长度子数组的和
     */
    public static int sumOddLengthSubarrays3(int[] arr) {
        int sum = 0;
        int n = arr.length;
        //定义前缀和数组
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = arr[i] + prefix[i];
        }
        // 子串起始坐标
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j += 2) {
                sum += prefix[j + 1] - prefix[i];
            }
        }
        System.out.println(sum);
        return sum;
    }

    // 8.30 笔记里有

    /**
     * https://leetcode-cn.com/problems/corporate-flight-bookings/
     * 暴力解法
     * 8.31
     *
     * @param bookings 航班预定表数组
     * @param n        长度
     * @return 数组
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            // 在子数组的第2个元素到第三个元素区间内
            for (int j = bookings[i][0] - 1; j < bookings[i][1]; j++) {
                ans[j] += bookings[i][2];
            }
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    /**
     * 差分数组
     *
     * @param bookings 航班预定表数组
     * @param n        长度
     * @return 数组
     */
    public static int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            ans[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                ans[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }
}
