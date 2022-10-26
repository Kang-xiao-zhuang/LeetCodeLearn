package com.zhuang.daily.twozero.may;

import java.util.*;

/**
 * description: Solution001
 * date: 2022/10/9 9:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.lengthOfLongestSubstring("abcabcbb");
        int[] num = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        solution001.maxSubArray(num);
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        solution001.findOrder(4, prerequisites);
    }

    /**
     * https://leetcode.cn/problems/merge-two-sorted-lists/
     * 2020.5.1
     *
     * @param list1 链表1
     * @param list2 链表2
     * @return ListNode
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 虚拟头节点
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (list1 != null && list2 != null) {
            // 链表1节点值 小于 链表2节点值
            if (list1.val <= list2.val) {
                // 前驱节点移动
                prev.next = list1;
                // 向后移动
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        return prehead.next;
    }

    /**
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     * 2020.5.2
     *
     * @param s 字符串
     * @return 找出其中不含有重复字符的 最长子串 的长度
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }


    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    /**
     * https://leetcode.cn/problems/maximum-subarray/
     * 2020.5.3
     *
     * @param nums 数组
     * @return 最大值
     */
    public int maxSubArray(int[] nums) {
        // 全局最优解
        int result = nums[0];
        // 局部最优解
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 已经连续遍历子数组的和 + 当前元素值>= 当前元素值
            if (temp + nums[i] >= nums[i]) {
                // temp=已遍历连续子数组的和+当前元素值
                temp = temp + nums[i];
            } else {
                // 小于就不要加
                temp = nums[i];
            }
            // 对比谁更大
            if (temp > result) {
                result = temp;
            }
        }
        return result;
    }

    /**
     * https://leetcode.cn/problems/jump-game-ii/
     * 2020.5.4
     *
     * @param nums 非负整数数组
     * @return 使用最少的跳跃次数到达数组的最后一个位置
     */
    public int jump(int[] nums) {
        int position = nums.length - 1;
        int step = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (nums[i] + i >= position) {
                    position = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }

    public int jump2(int[] nums) {
        // 跳跃次数
        int step = 0;
        // 局部最优解
        int curDistance = 0;
        // 全局最优解
        int maxDistance = 0;
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance, i + nums[i]);
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance >= nums.length - 1) {
                step++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i == curDistance) {
                curDistance = maxDistance;
                step++;
            }
        }
        return step;
    }

    /**
     * https://leetcode.cn/problems/subtree-of-another-tree/
     * 2020.5.7
     *
     * @param root    二叉树
     * @param subRoot 二叉树
     * @return 检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    public boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return check(root, subRoot) || dfs(root.left, subRoot) || dfs(root.right, subRoot);
    }

    private boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        }
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }


    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return subRoot == null;
        }
        if (subRoot == null) {
            return true;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.removeFirst();
            if (dfs2(node, subRoot)) {
                return true;
            }
            if (node.left != null) {
                deque.addLast(node.left);
            }
            if (node.right != null) {
                deque.addLast(node.right);
            }
        }
        return false;
    }

    public boolean dfs2(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        }
        return dfs2(root.left, subRoot.left) && dfs2(root.right, subRoot.right);
    }

    /**
     * https://leetcode.cn/problems/single-number/
     * 2020.5.14
     *
     * @param nums 非空整数数组
     * @return 找出那个只出现了一次的元素
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * https://leetcode.cn/problems/subarray-sum-equals-k/
     * 2020.5.15
     *
     * @param nums 数组
     * @param k    int
     * @return 统计并返回 该数组中和为 k 的连续子数组的个数
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 匹配整个从下标 0 开始的前缀
        int n = nums.length, preSum = 0, ans = 0;
        for (int num : nums) {
            preSum += num;
            ans += map.getOrDefault(preSum - k, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/course-schedule-ii/
     * 2020.5.17
     *
     * @param numCourses    课程
     * @param prerequisites 数组
     * @return 你为了学完所有课程所安排的学习顺序
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        // 入度表
        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            // 对于有先修课的课程，计算有几门先修课
            inDegrees[prerequisite[0]]++;
        }
        // 入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        // 入度为0就入队
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // 记录可以学完的课程
        int count = 0;
        // 可以学完的课程
        int[] res = new int[numCourses];
        // 删除入度为0的节点
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            res[count] = temp;
            count++;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == temp) {
                    inDegrees[prerequisite[0]]--;
                    if (inDegrees[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        if (count == numCourses) {
            return res;
        }
        return new int[0];
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        // 建立邻接矩阵
        int[][] graph = new int[numCourses][numCourses];
        for (int[] p : prerequisites) {
            graph[p[1]][p[0]] = 1;
        }
        // 记录访问状态的数组， 访问过设为-1 正在访问设为1 为未访问设为0
        int[] status = new int[numCourses];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, status, i, stack)) {
                // 只要存在环就返回
                return new int[0];
            }
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    private boolean dfs(int[][] graph, int[] status, int i, Deque<Integer> stack) {
        if (status[i] == 1) {
            // 当前节点在此次 dfs 中正在访问，说明存在环
            return false;
        }
        if (status[i] == -1) {
            return true;
        }
        status[i] = 1;
        for (int j = 0; j < graph.length; j++) {
            // dfs 访问当前课程的后续课程，看是否存在环
            if (graph[i][j] == 1 && !dfs(graph, status, j, stack)) {
                return false;
            }
        }
        // 标记为已访问
        status[i] = -1;
        stack.push(i);
        return true;
    }

    public int[] findOrder3(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        // HashSet 作为邻接矩阵
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }
        int[] mark = new int[numCourses]; // 标记数组
        Deque<Integer> stack = new ArrayDeque<>(); // 结果栈
        for (int i = 0; i < numCourses; i++) {
            if (!isCycle(graph, mark, i, stack)) return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    private boolean isCycle(HashSet<Integer>[] graph, int[] mark, int i, Deque<Integer> stack) {
        if (mark[i] == -1) return true;
        if (mark[i] == 1) return false;

        mark[i] = 1;
        for (int neighbor : graph[i]) {
            if (!isCycle(graph, mark, neighbor, stack)) return false;
        }
        mark[i] = -1;
        stack.push(i);
        return true;
    }
}
