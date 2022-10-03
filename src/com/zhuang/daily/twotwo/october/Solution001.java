package com.zhuang.daily.twotwo.october;

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
}
