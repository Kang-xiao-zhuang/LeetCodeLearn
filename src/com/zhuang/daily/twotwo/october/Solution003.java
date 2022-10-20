package com.zhuang.daily.twotwo.october;

import java.util.*;

/**
 * description: Solution003
 * date: 2022/10/17 8:57
 * author: Zhuang
 * version: 1.0
 */
public class Solution003 {
    public static void main(String[] args) {
        Solution003 solution003 = new Solution003();
        int[] fruits = {0, 1, 2, 2};
        solution003.totalFruit(fruits);
        int[] students = {1, 1, 0, 0};
        int[] sandwiches = {0, 1, 0, 1};
        solution003.countStudents2(students, sandwiches);
    }

    /**
     * https://leetcode.cn/problems/fruit-into-baskets/
     * 2022.10.17
     *
     * @param fruits 整数数组
     * @return 你可以收集的水果的最大数目
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/
     * 2022.10.19
     *
     * @param students   整数数组
     * @param sandwiches 整数数组
     * @return 返回无法吃午餐的学生数量
     */
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queueStu = new LinkedList<>();
        Deque<Integer> stackSanwich = new ArrayDeque<>();

        for (int student : students) {
            queueStu.offer(student);
        }

        for (int sandwich : sandwiches) {
            stackSanwich.offer(sandwich);
        }
        int dislikeCount = 0;
        while (!queueStu.isEmpty() && !stackSanwich.isEmpty()) {
            if (dislikeCount == stackSanwich.size()) {
                break;
            }
            int tempStu = queueStu.poll();
            // 首个学生比较三明治的顶部
            if (tempStu == stackSanwich.peek()) {
                dislikeCount = 0;
                // 弹出
                stackSanwich.pop();
            } else {
                // 不同 加入到队列尾部
                queueStu.offer(tempStu);
                dislikeCount++;
            }
        }
        return stackSanwich.isEmpty() ? 0 : stackSanwich.size();
    }

    public int countStudents2(int[] students, int[] sandwiches) {
        // 统计0 和 1 数量
        int countZero = 0;
        int countOne = 0;
        for (int student : students) {
            if (student == 0) {
                countZero++;
            } else {
                countOne++;
            }
        }
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (countZero == 0) {
                    return countOne;
                }
                countZero--;
            } else {
                if (countOne == 0) {
                    return countZero;
                }
                countOne--;
            }
        }
        return 0;
    }

    /**
     * https://leetcode.cn/problems/k-th-symbol-in-grammar/
     * 2022.10.20
     *
     * @param n n 行
     * @param k 第 k 个字符
     * @return 返回第 n 行中第 k 个字符
     */
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) & 1;
    }
}