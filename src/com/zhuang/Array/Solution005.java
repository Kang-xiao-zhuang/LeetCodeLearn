package com.zhuang.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 数组学习
 * @Author Zhuang
 * @Date 2021/7/27 8:45
 * @Version 1.0
 **/
public class Solution005 {
    public static void main(String[] args) {
        //   String s = "loveleetcode";
        //  firstUniqChar(s);
        //firstUniqChar2(s);

        //   String ransomNote = "aa";
        //  String magazine = "aab";
        //  canConstruct(ransomNote,magazine);
        //canConstruct2(ransomNote, magazine);
        //   int[] nums = {1, 2, 1};
        //  getConcatenation(nums);

        String[] words = {"bella", "label", "roller"};
        commonChars(words);
    }

    /**
     * 哈希表
     * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
     *
     * @param s 字符串
     * @return int
     */
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    /**
     * 数组方法
     *
     * @param s 字符串
     * @return int
     */
    public static int firstUniqChar2(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            // 返回指定索引的字符-‘a’==1？
            if (arr[s.charAt(i) - 'a'] == 1) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode-cn.com/problems/ransom-note/
     * int数组
     *
     * @param ransomNote 数组1
     * @param magazine   数组2
     * @return 布尔值
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        // 记录杂志出现的字符串次数
        int temp;
        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i) - 'a';
            arr[temp]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i) - 'a';
            if (arr[temp] > 0) {
                arr[temp]--;
            } else {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(true);
        return true;
    }

    /**
     * char数组
     *
     * @param ransomNote 字符串1
     * @param magazine   字符串2
     * @return 布尔值
     */
    public static boolean canConstruct2(String ransomNote, String magazine) {
        char[] chars = new char[26];
        for (char c : magazine.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (chars[c - 'a']-- == 0) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(true);
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/concatenation-of-array/
     *
     * @param nums 数组
     * @return int数组
     */
    public static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n * 2];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
            arr[i + n] = nums[i];
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    /**
     * https://leetcode-cn.com/problems/find-common-characters/
     *
     * @param words 数组
     * @return list集合
     */
    public static List<String> commonChars(String[] words) {
        ArrayList<String> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        // 统计所有字符串里字符出现的最小频率
        int[] hash = new int[26];
        for (int i = 0; i < words[0].length(); i++) {
            hash[words[0].charAt(i) - 'a']++;
        }
        // 统计除第一个字符串外字符出现的频率
        for (int i = 0; i < words.length; i++) {
            int[] hashOtherStr = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                hashOtherStr[words[i].charAt(j) - 'a']++;
            }
            // 更新hash，保证hash里统计26个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }
        // 将hash统计的字符次数，转成输出形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) {
                //多个重复的字符
                char c = (char) (i + 'a');
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        System.out.println(result.toString());
        return result;
    }
}
