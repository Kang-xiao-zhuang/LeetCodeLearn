package com.zhuang.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname Solution012
 * @Description 数组学习
 * @Date 2021/8/26 10:47
 * @Author by Zhuang
 */
public class Solution012 {
    public static void main(String[] args) {
        //int[] nums = {1, 0, -1, 0, -2, 2};
        //fourSum(nums, 0);

        //int[] nums = {1, 1, 0, 1, 1, 1};
        //findMaxConsecutiveOnes(nums);
        //findMaxConsecutiveOnes2(nums);

        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        //findUnsortedSubarray(nums);
        //findUnsortedSubarray2(nums);
        findUnsortedSubarray3(nums);
    }

    /**
     * https://leetcode-cn.com/problems/4sum/
     * 排序＋双指针
     *
     * @param nums   数组
     * @param target 目标值
     * @return List集合
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // 新建一个集合
        ArrayList<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 确定第一个数
            // 对第一个数去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 确实第二个数
            // 第二个数去重
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 确定第三个数和第四个数
                int k = j + 1;
                int z = n - 1;
                while (k < z) {
                    // 对第三个数去重
                    while (k > j + 1 && k < n && nums[k] == nums[k - 1]) {
                        k++;
                    }
                    if (k >= z) {
                        break;
                    }
                    // 四数之和
                    int sum = nums[i] + nums[j] + nums[k] + nums[z];
                    if (sum == target) {
                        // 符合条件就加入到集合中
                        list.add(Arrays.asList(nums[i], nums[j], nums[k], nums[z]));
                        // 移动前指针
                        k++;
                    } else if (sum > target) {
                        // 尾指针向前移动
                        z--;
                    } else if (sum < target) {
                        // 前指针向后移动
                        k++;
                    }
                }
            }
        }
        System.out.println(list.toString());
        return list;
    }

    /**
     * https://leetcode-cn.com/problems/max-consecutive-ones/
     *
     * @param nums 数组
     * @return int
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cur = 0;
        for (int num : nums) {
            cur = num == 0 ? 0 : (cur + 1);
            max = Math.max(max, cur);
        }
        System.out.println(max);
        return max;
    }

    /**
     * 双指针法
     *
     * @param nums 数组
     * @return int
     */
    public static int findMaxConsecutiveOnes2(int[] nums) {
        int ans = 0;
        // 定义两个指针
        for (int left = 0, right = 0; left < nums.length; right = left) {
            // 左指针为1
            if (nums[left] == 1) {
                // 右指针为1
                while (right + 1 < nums.length && nums[right + 1] == 1) {
                    right++;
                }
                // 更新1最多个数
                ans = Math.max(ans, right - left + 1);
                left = right + 1;
            } else {
                // 左指针向后移动
                left++;
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
     * 双指针法
     *
     * @param nums 数组
     * @return 最短子数组长度
     */
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        // 定义两个指针
        int left = 0;
        int right = n - 1;
        while (left <= right && arr[left] == nums[left]) {
            left++;
        }
        while (left <= right && arr[right] == nums[right]) {
            right--;
        }
        System.out.println(right - left + 1);
        return right - left + 1;
    }

    /**
     * 双指针法(三叶题解)未懂
     *
     * @param nums 数组
     * @return 最短子数组长度
     */
    public static int findUnsortedSubarray2(int[] nums) {
        int MIN = Integer.MIN_VALUE;
        int MAX = Integer.MAX_VALUE;
        int n = nums.length;
        // 定义两个指针
        int i = 0, j = n - 1;
        while (i < j && nums[i] <= nums[i + 1]) {
            i++;
        }
        while (i < j && nums[j] >= nums[j - 1]) {
            j--;
        }
        // 再定义两个指针
        int left = i, right = j;
        // 定义最小值，最大值
        int min = nums[i], max = nums[j];
        for (int k = left; k <= right; k++) {
            if (nums[k] < min) {
                while (i >= 0 && nums[i] > nums[k]) {
                    i--;
                }
                min = i >= 0 ? nums[i] : MIN;
            }
            if (nums[k] > max) {
                while (j < n && nums[j] <= nums[k]) {
                    j++;
                }
                max = j < n ? nums[j] : MAX;
            }
        }
        System.out.println(j == i ? 0 : (j - 1) - (i + 1) + 1);
        return j == i ? 0 : (j - 1) - (i + 1) + 1;
    }

    /**
     * 集合的方法
     *
     * @param nums 数组
     * @return 最短子数组长度
     */
    public static int findUnsortedSubarray3(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < nums.length; i++) {
            // 不相等就保存下标
            if (nums[i] != arr[i]) {
                list.add(i);
            }
        }
        if (list.size() == 0) {
            return 0;
        }
        int len = list.get(list.size() - 1) - list.get(0) + 1;
        System.out.println(len);
        return len;
    }
}
