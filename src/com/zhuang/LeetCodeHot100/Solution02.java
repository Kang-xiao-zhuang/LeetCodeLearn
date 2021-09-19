package com.zhuang.LeetCodeHot100;

import java.util.*;

/**
 * @Classname Solution02
 * @Description LeetCode 热题 HOT 6-10
 * @Date 2021/9/18 14:41
 * @Author by Zhuang
 */
public class Solution02 {
    public static void main(String[] args) {
        //int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        //maxArea(height);

        //int[] nums = {-1, 0, 1, 2, -1, -4};
        //threeSum(nums);

        letterCombinations("23");
    }


    /**
     * https://leetcode-cn.com/problems/container-with-most-water/
     * 第11题
     *
     * @param height 高度
     * @return 最大容量
     */
    public static int maxArea(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        // 定义两个指针
        int left = 0;
        int right = len - 1;
        while (left < right) {
            // 计算水的容量
            int maxArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, maxArea);
            // 根据条件来调整指针
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
     * https://leetcode-cn.com/problems/3sum/
     * 第15题
     *
     * @param nums 数组
     * @return 所有和为0的不重复三元组
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return res;
        }
        // 数组先排序
        Arrays.sort(nums);

        // 循环遍历数组
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            // 定义双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                // 如果等于目标值，那么就加入到集合中
                if (nums[left] + nums[right] == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    // nums[left] == nums[left - 1]时，左指针后移
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // nums[right] == nums[right + 1]时，右指针前移
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    // 和小于目标值，左指针后移
                } else if (nums[left] + nums[right] < target) {
                    left++;
                    // 和大于目标值，右指针前移
                } else {
                    right--;
                }
            }
        }
        System.out.println(res.toString());
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * 第17题
     *
     * @param digits 数字字符串
     * @return 返回能表示的字母组合
     */
    public static List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        // 判断字符串是否为空
        if (digits.length() == 0) {
            return res;
        }
        HashMap<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, map, digits, 0, new StringBuffer());
        System.out.println(res.toString());
        return res;
    }

    /**
     * @param res         结果集
     * @param map         哈希
     * @param digits      字符串
     * @param index       索引
     * @param combination 组合方式
     */
    public static void backtrack(List<String> res, Map<Character, String> map, String digits, int index, StringBuffer combination) {
        // 如果传入索引等于字符串长度
        if (index == digits.length()) {
            res.add(combination.toString());
        } else {
            // 根据索引获取字符
            char digit = digits.charAt(index);
            // 根据字符key在map中获取对应的value
            String letters = map.get(digit);
            // 遍历所对应的value字符串
            int count = letters.length();
            for (int i = 0; i < count; i++) {
                combination.append(letters.charAt(i));
                backtrack(res, map, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
