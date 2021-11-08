package com.zhuang.Daily.November;

/**
 * @Classname Solution002
 * @Description 2021.11.8-2021.11.15每日一题
 * @Date 2021/11/1 20:26
 * @Author by dell
 */

public class Solution002 {
    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        getHint(secret, guess);
    }


    public static String getHint(String secret, String guess) {
        int a = 0, b = 0;
        int[] arr = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                // 小于0说明之前guess中国出现过相同的字符
                if (arr[secret.charAt(i) - '0']++ < 0) {
                    b++;
                }
                // 大于0说之前secret中出现过相同的字符
                if (arr[guess.charAt(i) - '0']-- > 0) {
                    b++;
                }
            }
        }
        System.out.println(new StringBuilder().append(a).append('A').append(b).append('B').toString());
        return new StringBuilder().append(a).append('A').append(b).append('B').toString();
    }

}
