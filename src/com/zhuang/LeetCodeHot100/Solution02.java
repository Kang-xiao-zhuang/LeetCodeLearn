package com.zhuang.LeetCodeHot100;

import java.util.*;

/**
 * @Classname Solution02
 * @Description LeetCode 热题 HOT 6-10
 * @Date 2021/9/18 14:41
 * @Author by Zhuang
 */
public class Solution02 {
    public static void main(String[] args) {
        //int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        //maxArea(height);

        //int[] nums = {-1, 0, 1, 2, -1, -4};
        //threeSum(nums);

        //letterCombinations("23");

        //int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        //lengthOfLIS(nums);
        //lengthOfLIS2(nums);

        //String s = "()[]{}";
        //isValid(s);
        //isValid2(s);
        //isValid3(s);

    }


    /**
     * https://leetcode-cn.com/problems/container-with-most-water/
     * 第11题
     *
     * @param height 高度
     * @return 最大容量
     */
    public static int maxArea(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        // 定义两个指针
        int left = 0;
        int right = len - 1;
        while (left < right) {
            // 计算水的容量
            int maxArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, maxArea);
            // 根据条件来调整指针
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     * 第15题
     *
     * @param nums 数组
     * @return 所有和为0的不重复三元组
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return res;
        }
        // 数组先排序
        Arrays.sort(nums);

        // 循环遍历数组
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            // 定义双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                // 如果等于目标值，那么就加入到集合中
                if (nums[left] + nums[right] == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    // nums[left] == nums[left - 1]时，左指针后移
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // nums[right] == nums[right + 1]时，右指针前移
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    // 和小于目标值，左指针后移
                } else if (nums[left] + nums[right] < target) {
                    left++;
                    // 和大于目标值，右指针前移
                } else {
                    right--;
                }
            }
        }
        System.out.println(res.toString());
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * 第17题
     *
     * @param digits 数字字符串
     * @return 返回能表示的字母组合
     */
    public static List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        // 判断字符串是否为空
        if (digits.length() == 0) {
            return res;
        }
        HashMap<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, map, digits, 0, new StringBuffer());
        System.out.println(res.toString());
        return res;
    }

    /**
     * @param res         结果集
     * @param map         哈希
     * @param digits      字符串
     * @param index       索引
     * @param combination 组合方式
     */
    public static void backtrack(List<String> res, Map<Character, String> map, String digits, int index, StringBuffer combination) {
        // 如果传入索引等于字符串长度
        if (index == digits.length()) {
            res.add(combination.toString());
        } else {
            // 根据索引获取字符
            char digit = digits.charAt(index);
            // 根据字符key在map中获取对应的value
            String letters = map.get(digit);
            // 遍历所对应的value字符串
            int count = letters.length();
            for (int i = 0; i < count; i++) {
                combination.append(letters.charAt(i));
                backtrack(res, map, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * 第300题
     * 双栈
     *
     * @param nums 数组
     * @return 最长递增子序列的长度
     */
    public static int lengthOfLIS(int[] nums) {
        // 定义两个栈
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        // 栈1加入数组首元素
        s1.add(nums[0]);
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 栈顶元素小于数组值，加加入到栈1
            if (s1.peek() < nums[i]) {
                s1.add(nums[i]);
                // 栈顶元素大于数组值，且栈不包含数组值
            } else if (s1.peek() > nums[i] && !s1.contains(nums[i])) {
                // 栈1弹出数组值
                while (!s1.isEmpty() && s1.peek() >= nums[i]) {
                    // 将栈1弹出的值加入到栈2
                    s2.add(s1.pop());
                }
                // 栈2弹出值
                s2.pop();
                // 继续添加下一个数组值
                s1.add(nums[i]);
                while (!s2.isEmpty()) {
                    // 栈1加入栈2弹出的值
                    s1.add(s2.pop());
                }
            }
        }
        System.out.println(s1.toString());
        System.out.println(s1.size());
        return s1.size();
    }

    /**
     * 动态规划
     *
     * @param nums 数组
     * @return 最长递增子序列的长度
     */
    public static int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 定义转移方程
        int[] dp = new int[nums.length];
        int res = 0;
        // dp数组中全部赋值1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // 判断num[j]和nums[i]的值
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * 第19题
     * 双指针法
     *
     * @param head 头节点
     * @param n    倒数第n个节点
     * @return 链表的头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 定义一个前驱节点
        ListNode pre = new ListNode(0, head);
        // 定义快慢节点
        ListNode slow = pre, fast = head;
        while (n-- > 0 && fast != null) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }

    /**
     * 栈方法
     *
     * @param head 头节点
     * @param n    倒数第n个节点
     * @return 链表的头节点
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.pop();
        pre.next = pre.next.next;
        return dummy.next;
    }

    /**
     * https://leetcode-cn.com/problems/valid-parentheses/
     * 字符串替换
     *
     * @param s 字符串
     * @return 是否为有效的括号
     */
    public static boolean isValid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s.replace("{}", "");
            s.replace("[]", "");
            s.replace("()", "");
        }
        return s.isEmpty();
    }

    /**
     * 栈方法
     *
     * @param s 字符串
     * @return 是否为有效的括号
     */
    public static boolean isValid2(String s) {
        // 定义一个栈
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 判断为左大括号，栈加入右大括号
            if (c == '{') {
                stack.push('}');
                // 判断为左小括号，栈加入右小括号
            } else if (c == '(') {
                stack.push(')');
                // 判断为左中括号，栈加入右中括号
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(stack.isEmpty());
        return stack.isEmpty();
    }

    /**
     * 哈希表
     *
     * @param s 字符串
     * @return 是否为有效的括号
     */
    public static boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && map.containsKey(c)) {
                if (stack.peek().equals(map.get(c))) {
                    stack.pop();
                } else {
                    System.out.println(false);
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        System.out.println(stack.isEmpty());
        return stack.isEmpty();
    }
}
