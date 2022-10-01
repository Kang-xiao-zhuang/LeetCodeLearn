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
}
