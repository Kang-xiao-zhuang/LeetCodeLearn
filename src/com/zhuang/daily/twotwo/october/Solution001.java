package com.zhuang.daily.twotwo.october;

import java.util.*;

/**
 * description: Solution001
 * date: 2022/10/1 8:05
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.reformatNumber("123 4-567");

        String[] cpdomains = {"9001 discuss.leetcode.com"};
        solution001.subdomainVisits(cpdomains);
    }

    /**
     * https://leetcode.cn/problems/reformat-phone-number/
     * 10.1
     *
     * @param number String
     * @return 格式化后的电话号码
     */
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            }
        }
        int n = sb.length();
        int pt = 0;
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            if (n > 4) {
                ans.append(sb.substring(pt, pt + 3)).append("-");
                pt += 3;
                n -= 3;
            } else {
                if (n == 4) {
                    ans.append(sb.substring(pt, pt + 2)).append("-").append(sb.substring(pt + 2, pt + 4));
                } else {
                    ans.append(sb.substring(pt, pt + n));
                }
                break;
            }
        }
        System.out.println("ans = " + ans.toString());
        return ans.toString();
    }

    /**
     * https://leetcode.cn/problems/swap-adjacent-in-lr-string/
     * 10.2
     *
     * @param start String
     * @param end   String
     * @return boolean
     */
    public boolean canTransform(String start, String end) {
        int n = start.length();
        // 双指针
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
     * 10.3
     *
     * @param s 二进制字符串
     * @return boolean
     */
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }

    public boolean checkOnesSegment2(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            //直接记录是否出现0 然后检查出现0是否还有1出现有就直接false
            if (a == '0' && b == '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
     * 10.4
     *
     * @param s 字符串
     * @return int
     */
    public int minAddToMakeValid(String s) {
        int score = 0;
        int ans = 0;
        for (char c : s.toCharArray()) {
            score += c == '(' ? 1 : -1;
            if (score < 0) {
                score = 0;
                ans++;
            }
        }
        return ans + score;
    }

    public int minAddToMakeValid2(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !deque.isEmpty() && deque.peek() == '(') {
                deque.pop();
            } else {
                deque.push(c);
            }
        }
        return deque.size();
    }

    /**
     * https://leetcode.cn/problems/subdomain-visit-count/
     * 10.5
     *
     * @param cpdomains 计数配对域名
     * @return 数组
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (String cpdomain : cpdomains) {
            Integer count = Integer.valueOf(Arrays.asList(cpdomain.split(" ")).get(0));
            String[] split = Arrays.asList(cpdomain.split(" ")).get(1).split("\\.");
            for (int i = 0; i < split.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(split[i]);
                for (int j = i + 1; j < split.length; j++) {
                    sb.append(".").append(split[j]);
                }
                String s = sb.toString();
                map.put(s, map.getOrDefault(s, 0) + count);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(String.valueOf(entry.getValue()) + " " + entry.getKey());
        }
        return list;
    }

    /**
     * https://leetcode.cn/problems/three-equal-parts/
     * 10.6
     *
     * @param arr 数组
     * @return 数组
     */
    public int[] threeEqualParts(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (sum == 0) {
            return new int[]{0, 2};
        }

        int partial = sum / 3;
        int first = 0, second = 0, third = 0, cur = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (cur == 0) {
                    first = i;
                } else if (cur == partial) {
                    second = i;
                } else if (cur == 2 * partial) {
                    third = i;
                }
                cur++;
            }
        }

        int len = arr.length - third;
        if (first + len <= second && second + len <= third) {
            int i = 0;
            while (third + i < arr.length) {
                if (arr[first + i] != arr[second + i] || arr[first + i] != arr[third + i]) {
                    return new int[]{-1, -1};
                }
                i++;
            }
            return new int[]{first + len - 1, second + len};
        }
        return new int[]{-1, -1};
    }

    /**
     * https://leetcode.cn/problems/maximum-ascending-subarray-sum/
     * 10.7
     *
     * @param nums 数组
     * @return nums 中一个升序子数组的最大可能元素和
     */
    public int maxAscendingSum(int[] nums) {
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i-1]) {
                temp += nums[i];
                sum = Math.max(sum, temp);
            } else {
                temp = nums[i];
            }
        }
        return sum;
    }
}
