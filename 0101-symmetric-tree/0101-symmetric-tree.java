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
    private boolean flag;
    public boolean isSymmetric(TreeNode root) {
        flag = true;
        if (root.left == null && root.right == null || root == null) return true;
        solve(root, root);
        return flag;
    }
    private void solve(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return;
        if (root1 == null && root2 != null) {
            flag = false;
            return;
        }
        if (root2 == null && root1 != null) {
            flag = false;
            return;
        }
        if (root1.val != root2.val) {
            flag = false;
            return;
        }

        solve(root1.left, root2.right);
        solve(root1.right, root2.left);
    }
}