/**
94. Binary Tree Maximum Path Sum
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

样例
Example 1:
	Input:  For the following binary tree（only one node）:
	2
	Output：2
	
Example 2:
	Input:  For the following binary tree:

      1
     / \
    2   3
		
	Output: 6
    给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束（路径和为两个节点之间所在路径上的节点权值之和）
*/
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // write your code here
        helper(root);
        return max;
    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        int curr_max = Math.max(Math.max(root.val + left, root.val + right), root.val);
        max = Math.max(max, Math.max(curr_max, right + left + root.val));
        return curr_max;
    }
}