/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private boolean res;
    public boolean isBalanced(TreeNode root) {
        res = true;
        dfs(root);
        return res; 
    }
    private int dfs(TreeNode root) { 
        if (root == null)
            return 0;
        int lh = dfs(root.left);
        int rh = dfs(root.right);
        if (Math.abs(rh - lh) > 1) res = false;
        return 1 + Math.max(lh, rh);
    }
}