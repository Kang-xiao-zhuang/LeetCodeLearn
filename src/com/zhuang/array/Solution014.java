package com.zhuang.array;

public class Solution014 {
    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        //canCompleteCircuit(gas, cost);
        generateMatrix(3);
    }

    /**
     * https://leetcode.cn/problems/gas-station/
     *
     * @param gas  加油站
     * @param cost 汽油
     * @return 是否存在解
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sumCurrent = 0;
        int res = 0;
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            sumCurrent += gas[i] - cost[i];
            if (sumCurrent < 0) {
                res = i + 1;
                sumCurrent = 0;
            }
        }
        System.out.println(sum < 0 ? -1 : res);
        return sum < 0 ? -1 : res;
    }

    /**
     * https://leetcode.cn/problems/squares-of-a-sorted-array/
     *
     * @param nums 非递减顺序 排序的整数数组 nums
     * @return 返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序
     */
    public int[] sortedSquares(int[] nums) {
        int index = nums.length - 1;
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[index--] = nums[left] * nums[left];
                left++;
            } else {
                res[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/spiral-matrix-ii/
     *
     * @param n 正整数
     * @return int[][]
     */
    public static int[][] generateMatrix(int n) {

        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int count = 1, target = n * n;
        int[][] res = new int[n][n];
        //for循环中变量定义成i或j的细节：按照通常的思维，i代表行，j代表列
        //这样，就可以很容易区分出来变化的量应该放在[][]的第一个还是第二个
        //对于变量的边界怎么定义：
        //从左向右填充：填充的列肯定在[left,right]区间
        //从上向下填充：填充的行肯定在[top,bottom]区间
        //从右向左填充：填充的列肯定在[right,left]区间
        //从下向上填充：填充的行肯定在[bootom,top]区间
        //通过上面的总结会发现边界的起始和结束与方向是对应的
        while (count <= target) {
            //从左到右填充，相当于缩小上边界
            for (int j = left; j <= right; j++) res[top][j] = count++;
            //缩小上边界
            top++;
            //从上向下填充，相当于缩小右边界
            for (int i = top; i <= bottom; i++) res[i][right] = count++;
            //缩小右边界
            right--;
            //从右向左填充，相当于缩小下边界
            for (int j = right; j >= left; j--) res[bottom][j] = count++;
            //缩小下边界
            bottom--;
            //从下向上填充，相当于缩小左边界
            for (int i = bottom; i >= top; i--) res[i][left] = count++;
            //缩小左边界
            left++;
        }
        return res;
    }
}
