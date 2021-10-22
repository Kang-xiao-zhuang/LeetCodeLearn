package com.zhuang.Daily.October;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname Solution004
 * @Description 2021.10.22-2021.10.31每日一题
 * @Date 2021/10/22 8:49
 * @Author by dell
 */
public class Solution004 {
    public static void main(String[] args) {
        int[] nums={3,2,3};
        majorityElement(nums);
        majorityElement2(nums);
    }

    /**
     * https://leetcode-cn.com/problems/majority-element-ii/
     * 10.22
     * 哈希
     *
     * @param nums 数组
     * @return 其中所有出现超过 ⌊ n/3 ⌋ 次的元素
     */
    public static List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (Integer integer : map.keySet()) {
            if (map.get(integer) > nums.length / 3) {
                res.add(integer);
            }
        }
        System.out.println(res.toString());
        return res;
    }

    /**
     * 摩尔投票法
     *
     * @param nums 数组
     * @return 其中所有出现超过 ⌊ n/3 ⌋ 次的元素
     */
    public static List<Integer> majorityElement2(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;
        for (int num : nums) {
            // 如果该元素第1个元素，则计数加1
            if (vote1 > 0 && num == element1) {
                vote1++;
                // 如果该元素第1个元素，则计数加1
            } else if (vote2 > 0 && num == element2) {
                vote2++;
                // 选择第一个元素
            } else if (vote1 == 0) {
                element1 = num;
                vote1++;
                // 选择第二个元素
            } else if (vote2 == 0) {
                element2 = num;
                vote2++;
                // 如果三个元素互不相同，则相互抵消
            } else {
                vote1--;
                vote2--;
            }
        }
        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> res = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            res.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            res.add(element2);
        }
        System.out.println(res.toString());
        return res;
    }
}
