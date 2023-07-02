package com.zhuang.LeetCodeHot100;

import java.util.*;

/**
 * @Classname LeetCodeHot100_Hash
 * @Description LeetCode 热题 哈希篇
 * @Date 2023/7/2 17:19
 * @Author by Zhuang
 */
public class LeetCodeHot100_Hash {
    public static void main(String[] args) {
        LeetCodeHot100_Hash leetCodeHot100_hash = new LeetCodeHot100_Hash();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        leetCodeHot100_hash.groupAnagrams(strs);
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
     * https://leetcode.cn/problems/group-anagrams/
     *
     * @param strs String[]
     * @return List<List < String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            // 对数组排序
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * https://leetcode.cn/problems/longest-consecutive-sequence/
     *
     * @param nums int[]
     * @return int
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longStreak = 0;
        for (Integer e : set) {
            if (!set.contains(e - 1)) {
                int curNum = e;
                int curStreak = 1;

                while (set.contains(curNum + 1)) {
                    curNum++;
                    curStreak++;
                }
                longStreak = Math.max(longStreak, curStreak);
            }
        }
        return longStreak;
    }
}
