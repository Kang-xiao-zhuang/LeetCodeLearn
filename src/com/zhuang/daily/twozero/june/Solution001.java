package com.zhuang.daily.twozero.june;

import java.util.*;

/**
 * description: Solution001
 * date: 2022/11/3 16:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[] candies = {2, 3, 5, 1, 3};
        solution001.kidsWithCandies(candies, 3);
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        solution001.spiralOrder(matrix);
        solution001.dailyTemperatures(temperatures);

        solution001.isPalindrome4("A man, a plan, a canal: Panama");
    }

    /**
     * https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies/
     * 2020.6.1
     *
     * @param candies      数组
     * @param extraCandies 整数
     * @return 额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> res = new ArrayList<>();
        int max = 0;
        /*  for (int candy : candies) {
            max = Math.max(candy, max);
        }*/
        int[] temp = Arrays.copyOf(candies, candies.length);
        Arrays.sort(temp);
        max = temp[temp.length - 1];
        for (int candy : candies) {
            res.add(candy + extraCandies >= max);
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/qiu-12n-lcof/
     * 2020.6.2
     *
     * @param n 数
     * @return 结果
     */
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    /**
     * https://leetcode.cn/problems/product-of-array-except-self/
     * 2022.6.4
     *
     * @param nums 数组
     * @return 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[n];
        int[] R = new int[n];
        L[0] = 1;
        // L[i] 为索引 i 左侧所有元素的乘积
        // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        for (int i = 1; i < n; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        R[n - 1] = 1;
        // R[i] 为索引 i 右侧所有元素的乘积
        // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
        for (int i = n - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < n; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        int R = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
     * 2022.6.5
     *
     * @param matrix 矩阵
     * @return 按照从外向里以顺时针的顺序依次打印出每一个数字
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] order = new int[rows * cols];
        int index = 0;
        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                order[index] = matrix[top][i];
                index++;
            }
            for (int j = top + 1; j <= bottom; j++) {
                order[index] = matrix[j][right];
                index++;
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i > left; i--) {
                    order[index] = matrix[bottom][i];
                    index++;
                }
                for (int j = bottom; j > top; j--) {
                    order[index] = matrix[j][left];
                    index++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * https://leetcode.cn/problems/longest-consecutive-sequence/
     * 2020.6.6
     *
     * @param nums 整数数组
     * @return 找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    /**
     * https://leetcode.cn/problems/daily-temperatures/
     * 2020.6.11
     *
     * @param temperatures 整数数组
     * @return 每日温度
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int temp = stack.pop();
                ans[temp] = i - temp;
            }
            stack.add(i);
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/best-sightseeing-pair/
     * 2020.6.17
     *
     * @param values 正整数数组
     * @return 一对观光景点能取得的最高分
     */
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0;
        int mx = values[0] + 0;
        for (int j = 1; j < values.length; j++) {
            ans = Math.max(ans, mx + values[j] - j);
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/valid-palindrome/
     * 2020.6.19
     *
     * @param s 字符串
     * @return 是否为回文串
     */
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isValid(Character.toLowerCase(ch))) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        StringBuilder res = new StringBuilder(sb).reverse();
        return res.toString().equals(sb.toString());
    }

    // 判断是否为数字或字母
    private boolean isValid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    // 双指针
    public boolean isPalindrome2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isValid(Character.toLowerCase(ch))) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(sb.charAt(left)) != Character.toLowerCase(sb.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    // 正则
    public boolean isPalindrome3(String s) {
        String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        StringBuilder sb = new StringBuilder(str);
        if (s == "") {
            return true;
        } else {
            return str.equals(sb.reverse().toString());
        }
    }

    // 栈
    public boolean isPalindrome4(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stack.push(Character.toLowerCase(ch));
            }
        }
        int i = 0;
        char[] charArray = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase().toCharArray();
        while (!stack.isEmpty()) {
            if (stack.pop() != charArray[i]) {
                return false;
            }
            i++;
        }
        return true;
    }

    // 栈和队列
    public boolean isPalindrome5(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stack.push(Character.toLowerCase(ch));
                queue.offer(Character.toLowerCase(ch));
            }
        }
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (!stack.pop().equals(queue.poll())) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/add-binary/
     * 2020.6.23
     *
     * @param a 二进制字符串
     * @param b 二进制字符串
     * @return 以二进制字符串的形式返回它们的和
     */
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    public String addBinary2(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        int n = Math.max(a.length(), b.length());
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        return ans.reverse().toString();
    }
}
