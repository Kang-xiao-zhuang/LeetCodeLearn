package com.zhuang.Daily.October;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.*;

/**
 * @Classname Solution004
 * @Description 2021.10.22-2021.10.31每日一题
 * @Date 2021/10/22 8:49
 * @Author by dell
 */
public class Solution004 {
    public static void main(String[] args) {
        //int[] nums = {3, 2, 3};
        //majorityElement(nums);
        //majorityElement2(nums);

        /*
        List<Integer> price = new ArrayList<Integer>();
        price.add(2);
        price.add(5);
        ArrayList<List<Integer>> special = new ArrayList<>();
        ArrayList<Integer> special1 = new ArrayList<>();
        ArrayList<Integer> special2 = new ArrayList<>();
        special1.add(3);
        special1.add(0);
        special1.add(5);
        special2.add(1);
        special2.add(2);
        special2.add(10);
        special.add(special1);
        special.add(special2);
        List<Integer> needs = new ArrayList<Integer>();
        needs.add(3);
        needs.add(2);
        shoppingOffers(price, special, needs);
         */

        //int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        //searchMatrix3(matrix, 5);
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

    /**
     * https://leetcode-cn.com/problems/construct-the-rectangle/
     * 10.23
     * 模拟
     *
     * @param area 面积
     * @return 矩形的数组
     */
    public int[] constructRectangle(int area) {
        int x = (int) Math.sqrt(area);
        while (area % x != 0) {
            x--;
        }
        return new int[]{area / x, x};
    }


    static Map<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();

    /**
     * https://leetcode-cn.com/problems/shopping-offers/
     * 10.24
     *
     * @param price   物品价格
     * @param special 大礼包
     * @param needs   购物清单
     * @return 足购物清单所需花费的最低价格
     */
    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();

        // 过滤不需要计算的大礼包，只保留需要计算的大礼包
        List<List<Integer>> filterSpecial = new ArrayList<List<Integer>>();
        for (List<Integer> sp : special) {
            int totalCount = 0, totalPrice = 0;
            for (int i = 0; i < n; ++i) {
                totalCount += sp.get(i);
                totalPrice += sp.get(i) * price.get(i);
            }
            if (totalCount > 0 && totalPrice > sp.get(n)) {
                filterSpecial.add(sp);
            }
        }
        return dfs(price, special, needs, filterSpecial, n);
    }

    // 记忆化搜索计算满足购物清单所需花费的最低价格
    public static int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
        if (!map.containsKey(curNeeds)) {
            int minPrice = 0;
            for (int i = 0; i < n; ++i) {
                // 不购买任何大礼包，原价购买购物清单中的所有物品
                minPrice += curNeeds.get(i) * price.get(i);
            }
            for (List<Integer> curSpecial : filterSpecial) {
                int specialPrice = curSpecial.get(n);
                List<Integer> nxtNeeds = new ArrayList<Integer>();
                for (int i = 0; i < n; ++i) {
                    // 不能购买超出购物清单指定数量的物品
                    if (curSpecial.get(i) > curNeeds.get(i)) {
                        break;
                    }
                    nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                }
                // 大礼包可以购买
                if (nxtNeeds.size() == n) {
                    minPrice = Math.min(minPrice, dfs(price, special, nxtNeeds, filterSpecial, n) + specialPrice);
                }
            }
            map.put(curNeeds, minPrice);
        }
        System.out.println(map.get(curNeeds));
        return map.get(curNeeds);
    }

    /**
     * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
     * 10.25
     * 直接遍历
     *
     * @param matrix 矩阵
     * @param target 目标
     * @return 布尔
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 二分查找
     *
     * @param matrix 矩阵
     * @param target 目标
     * @return 布尔
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = binarySearch(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 抽象BST
     *
     * @param matrix 矩阵
     * @param target 目标
     * @return 布尔
     */
    public static boolean searchMatrix3(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                System.out.println(true);
                return true;
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/next-greater-element-i/
     * 10.26
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 数组
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            for (int j = 0; j < nums1.length; j++) {
                if (nums2[i] == nums1[j]) {
                    res[j] = -1;
                    for (int k = i + 1; k < nums2.length; k++) {
                        if (nums2[k] > nums1[j]) {
                            res[j] = nums2[k];
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 先处理 nums2，把对应关系存入哈希表
        for (int i = 0; i < len2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                map.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }

        // 遍历 nums1 得到结果集
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }


    /**
     * https://leetcode-cn.com/problems/remove-invalid-parentheses/
     * 10.27
     *
     * @param s 字符串
     * @return List集合
     */
    public List<String> removeInvalidParentheses(String s) {
        ArrayList<String> ans = new ArrayList<>();
        HashSet<String> curSet = new HashSet<>();
        curSet.add(s);
        while (true) {
            for (String str : curSet) {
                if (isValid(str)) {
                    ans.add(str);
                }
            }
            if (ans.size() > 0) {
                return ans;
            }
            HashSet<String> nextSet = new HashSet<>();
            for (String str : curSet) {
                for (int i = 0; i < str.length(); i++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            curSet = nextSet;
        }
    }

    /**
     * 判断是否为有效括号
     *
     * @param str 字符串
     * @return 布尔值
     */
    public boolean isValid(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (char c : chars) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    /**
     * https://leetcode-cn.com/problems/reordered-power-of-2/
     * 10.28
     *
     * @param n 正整数
     * @return 是否为2的幂
     */
    public boolean reorderedPowerOf2(int n) {
        // 从小到大排列
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        // 遍历
        for (int i = 1; i <= 1e9; i *= 2) {
            char[] temp = String.valueOf(i).toCharArray();
            Arrays.sort(temp);
            if (Arrays.equals(chars, temp)) {
                return true;
            }
        }
        return false;
    }
}
