package com.zhuang.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname Solution003
 * @Description 二分查找学习
 * @Date 2021/8/8 14:20
 * @Created by dell
 */

public class Solution003 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        //    findClosestElements(arr, 4, 3);
        //    findClosestElements2(arr, 4, 3);
        //  isPerfectSquare(15);
        // isPerfectSquare2(81);
        // isPerfectSquare3(16);
        isPerfectSquare4(16);
        char[] letters = {'c', 'f', 'j'};
        nextGreatestLetter(letters, 'a');
        nextGreatestLetter2(letters, 'c');
        nextGreatestLetter3(letters, 'c');
    }

    /**
     * https://leetcode-cn.com/problems/find-k-closest-elements/
     *
     * @param arr 数组
     * @param k   整数k
     * @param x   整数x
     * @return 集合
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;

        int removeNums = len - k;
        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        System.out.println(res.toString());
        return res;
    }

    /**
     * 官方题解
     *
     * @param arr 数组
     * @param k   数字k
     * @param x   数字x
     * @return 集合
     */
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = ret.size();
        if (x <= ret.get(0)) {
            return ret.subList(0, k);
        } else if (ret.get(n - 1) <= x) {
            return ret.subList(n - k, n);
        } else {
            int index = Collections.binarySearch(ret, x);
            if (index < 0) {
                index = -index - 1;
            }
            int low = Math.max(0, index - k - 1);
            int high = Math.min(ret.size() - 1, index + k - 1);
            while (high - low > k - 1) {
                if ((x - ret.get(low)) <= (ret.get(high) - x)) {
                    high--;
                } else if ((x - ret.get(low)) > (ret.get(high) - x)) {
                    low++;
                } else {
                    System.out.println("unhandle case" + low + " " + high);
                }
            }
            System.out.println(ret.subList(low, high + 1).toString());
            return ret.subList(low, high + 1);
        }
    }


    /**
     * https://leetcode-cn.com/problems/valid-perfect-square/
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare(int num) {
        long r = num;
        while (r * r > num) {
            r = (r + num / r) / 2;
        }
        System.out.println(r * r == num);
        return r * r == num;
    }

    /**
     * 二分查找
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare2(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int temp = num / mid;
            if (mid * mid == num) {
                return true;
            } else if (temp < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * 二分查找
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare3(int num) {
        if (num < 2) {
            return true;
        }
        long left = 2;
        long right = num / 2;
        long guess;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            guess = mid * mid;
            if (guess == num) {
                System.out.println(true);
                return true;
            }
            if (guess < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * 类库
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare4(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    /**
     * 暴力
     *
     * @param num 数
     * @return 布尔
     */
    public static boolean isPerfectSquare5(int num) {
        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }


    /**
     * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
     *
     * @param letters 数组
     * @param target  目标值
     * @return char
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        boolean[] seen = new boolean[26];
        for (char c : letters) {
            seen[c - 'a'] = true;
        }
        while (true) {
            target++;
            if (target > 'z') {
                target = 'a';
            }
            if (seen[target - 'a']) {
                System.out.println(target);
                return target;
            }
        }
    }

    /**
     * 二分查找
     *
     * @param letters 数组
     * @param target  目标值
     * @return char
     */
    public static char nextGreatestLetter2(char[] letters, char target) {
        int left = 0;
        int right = letters.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 索引%数组长度可以得到索引值，可以通过索引获取到数组中的数
        System.out.println(letters[left % letters.length]);
        return letters[left % letters.length];
    }

    /**
     * 线性扫描
     *
     * @param letters 数组
     * @param target  目标值
     * @return char
     */
    public static char nextGreatestLetter3(char[] letters, char target) {
        for (char letter : letters) {
            if (letter > target) {
                System.out.println(letter);
                return letter;
            }
        }
        return letters[0];
    }
}
