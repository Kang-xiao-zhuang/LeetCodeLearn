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

        solution003.mergeAlternately("abc", "pqr");
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


    public int totalFruit2(int[] fruits) {
     /*
    startIndex：表示窗口的起点。
    diffIndex：每当发现相邻两个水果不同，则将其指向发生不同的那个水果，当遍历发现有第三种水果的时候，用于将其作为新窗口的起点。
    pickRecords：用于记录当前窗口内选中的水果（下图没有画），默认未选中为0，选中为1；
    pickNums：用于记录当前窗口内，已经选中的水果种类数量。
    curFruit：用于记录当前选中的苹果类型，当发现curFruit与fruits[i]不同时，diffIndex = fruits[i]。
    */
        int[] pickRecords = new int[fruits.length];
        int res = 0, startIndex = 0, diffIndex = 0, pickNums = 0, curFruit = 0;
        int n = fruits.length;
        for (int i = 0; i < n; i++) {
            if (pickRecords[fruits[i]] == 0) {
                if (pickNums == 2) {
                    res = Math.max(res, i - startIndex);
                    // 设置未选择
                    pickRecords[fruits[diffIndex - 1]] = 0;
                    // 记录窗口的起始索引
                    startIndex = diffIndex;
                    pickNums--;
                }
                // 已选择水果种类+1
                pickNums++;
                // 设置为水果被选择
                pickRecords[fruits[i]] = 1;
            }
            if (curFruit != fruits[i]) {
                curFruit = fruits[i];
                // 记录水果类型变换的index
                diffIndex = i;
            }
        }
        return Math.max(res, n - startIndex);
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

    /**
     * https://leetcode.cn/problems/merge-strings-alternately/
     * 2022.10.23
     *
     * @param word1 字符串
     * @param word2 字符串
     * @return 合并后的字符串
     */
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < m || j < n) {
            if (i < m) {
                sb.append(word1.charAt(i++));
            }
            if (j < n) {
                sb.append(word2.charAt(j++));
            }
        }
        return sb.toString();
    }

    /**
     * https://leetcode.cn/problems/partition-array-into-disjoint-intervals/
     * 2022.10.24
     *
     * @param nums 数组
     * @return left 的 长度
     */
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        // 全局最大值
        int leftMax = nums[0];
        // 局部最大值
        int curMax = nums[0];
        int leftPos = 0;
        for (int i = 1; i < n - 1; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (nums[i] < leftMax) {
                leftMax = curMax;
                leftPos = i;
            }
        }
        return leftPos + 1;
    }

    public int partitionDisjoint2(int[] nums) {
        int n = nums.length;
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }

        int maxLeft = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            if (maxLeft <= minRight[i + 1]) {
                return i + 1;
            }
        }
        return n - 1;
    }

    /**
     * https://leetcode.cn/problems/sign-of-the-product-of-an-array/
     * 2022.10.27
     *
     * @param nums 整数数组
     * @return signFunc(product)
     */
    public int arraySign(int[] nums) {
        boolean negative = false;
        for (int x : nums) {
            if (x == 0) {
                return 0;
            }
            if (x < 0) {
                negative = !negative;
            }
        }
        return negative ? -1 : 1;
    }

    public int arraySign2(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                count++;
            }
        }
        return count % 2 == 0 ? 1 : -1;
    }
}
