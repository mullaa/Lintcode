/**
45. Maximum Subarray Difference
中文English
Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.

样例
Example 1:

Input:[1, 2, -3, 1]
Output:6
Explanation:
The subarray are [1,2] and [-3].So the answer is 6.
Example 2:

Input:[0,-1]
Output:1
Explanation:
The subarray are [0] and [-1].So the answer is 1.
最大子数组差
中文English
给定一个整数数组，找出两个不重叠的子数组A和B，使两个子数组和的差的绝对值|SUM(A) - SUM(B)|最大。
返回这个最大的差值。
思路：
要使两个不重叠的子数组的差的绝对值最大，就要找到最大子数组之和与最小子数组之和，答案就是二者之差。
因为是不重叠的两个子数组，所以最大子数组和最小子数组必定分别在整数数组的左边和右边。所以要分类讨论两种情况，取MAX（左大右小的差，左小右大的差）。
从左到右依次求出前n个数的最小子数组之和与最大子数组之和，然后从右到左依次求出后n个数最小子数组之和与最大子数组之和。然后进行错位相减，得到的最大值就是答案。
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two substrings
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        int n = nums.length;
        //maxArray[1][n],minArray[1][n]从左到右最大子数组和最小子数组
        //maxArray[2][n],minArray[2][n]从右到左最大子数组和最小子数组
        int[][] maxArray = new int[3][n];
        int[][] minArray = new int[3][n];
        int maxSum1, maxSum2, minSum1, minSum2;
        maxSum1 = minSum1 = maxArray[1][0] = minArray[1][0] = nums[0];
        maxSum2 = minSum2 = maxArray[2][n - 1] = minArray[2][n - 1] = nums[n - 1];
        
        for (int i = 1; i < n; i++) {
            int j = n - 1 - i;
            
            maxSum1 = Math.max(maxSum1 + nums[i], nums[i]);
            minSum1 = Math.min(minSum1 + nums[i], nums[i]);
            maxSum2 = Math.max(maxSum2 + nums[j], nums[j]);
            minSum2 = Math.min(minSum2 + nums[j], nums[j]);
            
            
            maxArray[1][i] = Math.max(maxArray[1][i - 1], maxSum1);
            minArray[1][i] = Math.min(minArray[1][i - 1], minSum1);
            
            maxArray[2][j] = Math.max(maxArray[2][j + 1], maxSum2);
            minArray[2][j] = Math.min(minArray[2][j + 1], minSum2);
        }
        
        //错位相减
        int maxsub = 0;
        for (int i = 0; i + 1 < n; i++) {
            maxsub = Math.max(maxsub, maxArray[1][i] - minArray[2][i + 1]);
        }
        for (int i = n - 1; i - 1 >= 0; i--) {
            maxsub = Math.max(maxsub, maxArray[2][i] - minArray[1][i - 1]);
        }
        return maxsub;
    }
}