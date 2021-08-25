package com.zhuang.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Classname Solution011
 * @Description 数组学习
 * @Date 2021/8/21 21:29
 * @Author by Zhuang
 */

public class Solution011 {
    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 2, 2, 3};
        //removeDuplicates(nums);
        //removeDuplicates2(nums);
        // removeDuplicates3(nums);

        // int[] arr = {3, 2, 1, 5, 6, 4};
        // findKthLargest(arr, 2);

        // int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        // maxArea(height);
        // maxArea2(height);

        //int[] nums = {-1, 2, 1, -4};
        //threeSumClosest(nums, 1);
        //threeSumClosest2(nums, 1);

        //int[] nums = {1, 2, 3, 4};
        //numberOfArithmeticSlices(nums);
        //numberOfArithmeticSlices2(nums);
        //numberOfArithmeticSlices3(nums);
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
     *
     * @param nums 数组
     * @return int
     */
    public static int removeDuplicates(int[] nums) {
        int len = 0;
        for (int num : nums) {
            if (len < 2 || nums[len - 2] != num) {
                nums[len++] = num;
            }
        }
        System.out.println(len);
        return len;
    }

    /**
     * 双指针法
     *
     * @param nums 数组
     * @return int
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        // 定义两个指针，目标指针j，待移动指针i从1开始，计数器count
        int j = 1;
        int count = 1;
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 与前一个元素比较
            if (nums[i] == nums[i - 1]) {
                // 相同count++
                count++;
            } else {
                //否则置为1
                count = 1;
            }
            // 如果计数器小于等于2,将i处的值赋给j处
            if (count <= 2) {
                nums[j] = nums[i];
                // j自增到下个位置
                j++;
            }
        }
        System.out.println(j);
        return j;
    }

    /**
     * 双指针法官解
     *
     * @param nums 数组
     * @return int
     */
    public static int removeDuplicates3(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int slow = 2;
        int fast = 2;
        while (fast < nums.length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        System.out.println(slow);
        return slow;
    }

    /**
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     *
     * @param nums 数组
     * @param k    第K个大的元素
     * @return int
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        System.out.println(queue.poll());
        return queue.poll();
    }


    /**
     * https://leetcode-cn.com/problems/container-with-most-water/
     * 暴力法
     *
     * @param height 高度
     * @return 盛水最多的最大值
     */
    public static int maxArea(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 双指针法
     *
     * @param height 高度
     * @return 盛水最多的最大值
     */
    public static int maxArea2(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            // 计算水的容量
            int maxArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, maxArea);
            // 判断移动指针
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/3sum-closest/
     * 双指针法
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return int
     */
    public static int threeSumClosest(int[] nums, int target) {
        // 数组首先排序
        Arrays.sort(nums);
        // 初始化ans
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            // 定义头指针和尾指针
            int start = i + 1;
            int end = nums.length - 1;
            // 循环开始
            while (start < end) {
                // sum为三个值的和
                int sum = nums[start] + nums[end] + nums[i];
                // 如果 target-sum的绝对值<target-ans的绝对值 说明 sum更接近target
                // 将sum的值赋值给ans
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                // 如果大于 那么 尾指针向前移动
                if (sum > target) {
                    end--;
                    // 如果小于 那么 头指针向后移动
                } else if (sum < target) {
                    start++;
                    // 返回ans
                } else {
                    return ans;
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 暴力法
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return int
     */
    public static int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        if (target <= ans) {
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(target - sum) <= Math.abs(target - ans)) {
                        ans = sum;
                    }
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/arithmetic-slices/
     * 数学思想
     *
     * @param nums 数组
     * @return 子数组个数
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 2; ) {
            int j = i, d = nums[i + 1] - nums[i];
            while (j + 1 < n && nums[j + 1] - nums[j] == d) {
                j++;
            }
            int len = j - i + 1;
            // a1：⻓度为 len 的⼦数组数量；an：⻓度为 3 的⼦数组数量
            int a1 = 1, an = len - 3 + 1;
            // 符合条件（⻓度⼤于等于3）的⼦数组的数量为「差值数列求和」结果
            int cnt = (a1 + an) * an / 2;
            ans += cnt;
            i = j;
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 找规律
     *
     * @param nums 数组
     * @return 子数组个数
     */
    public static int numberOfArithmeticSlices2(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        int res = 0;
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i - 1] - nums[i] == nums[i - 2] - nums[i - 1]) {
                sum++;
                res += sum;
            } else {
                sum = 0;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 暴力法
     *
     * @param nums 数组
     * @return 子数组个数
     */
    public static int numberOfArithmeticSlices3(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i <= n - 3; i++) {
            for (int j = i + 2; j < n; j++) {
                // 判断第三个值-第二个值是否等于第二个值-第一个值
                if (nums[j] - nums[j - 1] == nums[j - 1] - nums[j - 2]) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        System.out.println(ans);
        return ans;
    }
}
