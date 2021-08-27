package com.zhuang.Array;

import java.util.Arrays;

/**
 * @Classname Solution013
 * @Description 数组学习
 * @Date 2021/8/27 11:39
 * @Author by Zhuang
 */
public class Solution013 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4};
        //triangleNumber(nums);
        //triangleNumber2(nums);
        //triangleNumber3(nums);


        int[][] images = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        //flipAndInvertImage(images);
        //flipAndInvertImage2(images);

    }


    /**
     * https://leetcode-cn.com/problems/valid-triangle-number/
     * 暴力法
     *
     * @param nums 非负整数数组
     * @return 可以组成三角形的个数
     */
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (nums[k] + nums[j] > nums[i]) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 排序+二分法
     *
     * @param nums
     * @return
     */
    public static int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 第三个数判断范围
                int left = 0;
                int right = j - 1;
                while (left < right) {
                    int mid = left + right >> 1;
                    if (nums[mid] + nums[j] > nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                if (left == right && nums[right] + nums[j] > nums[i]) {
                    ans += j - right;
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 排序+双指针
     *
     * @param nums
     * @return
     */
    public static int triangleNumber3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }
                ans += j - k;
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/flipping-an-image/
     * 双指针法
     *
     * @param image 二维数组
     * @return 二维数组
     */
    public static int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                if (image[i][left] == image[i][right]) {
                    image[i][left] ^= 1;
                    image[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right) {
                image[i][left] ^= 1;
            }
        }
        System.out.println(Arrays.deepToString(image));
        return image;
    }

    /**
     * 复制数组
     *
     * @param image 二维数组
     * @return 二维数组
     */
    public static int[][] flipAndInvertImage2(int[][] image) {
        int n = image.length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            // 对每⼀⾏进⾏拷⻉（共三种⽅式）
            // ans[i] = a[i].clone();
            // ans[i] = Arrays.copyOf(a[i], n);
            System.arraycopy(image[i], 0, ans[i], 0, n);
            int left = 0, right = n - 1;
            // 交换实现翻转，异或实现反转
            while (left < right) {
                int temp = ans[i][right];
                ans[i][right] = ans[i][left] ^ 1;
                ans[i][left] = temp ^ 1;
                left++;
                right--;
            }
            // 中间特殊处理
            if (n % 2 != 0) {
                ans[i][left] ^= 1;
            }
        }
        System.out.println(Arrays.deepToString(ans));
        return ans;
    }
}
